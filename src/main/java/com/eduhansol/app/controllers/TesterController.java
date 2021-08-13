package com.eduhansol.app.controllers;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Factor;
import com.eduhansol.app.entities.FactorGroup;
import com.eduhansol.app.entities.Group;
import com.eduhansol.app.entities.Mark;
import com.eduhansol.app.entities.Narrative;
import com.eduhansol.app.entities.NormPersonality;
import com.eduhansol.app.entities.Question;
import com.eduhansol.app.entities.SubFactor;
import com.eduhansol.app.entities.Tester;
import com.eduhansol.app.entities.TesterResult;
import com.eduhansol.app.services.FactorGroupService;
import com.eduhansol.app.services.FactorService;
import com.eduhansol.app.services.GroupService;
import com.eduhansol.app.services.MarkService;
import com.eduhansol.app.services.NarrativeService;
import com.eduhansol.app.services.NormPersonalityService;
import com.eduhansol.app.services.QuestionService;
import com.eduhansol.app.services.SubFactorService;
import com.eduhansol.app.services.TesterResultService;
import com.eduhansol.app.services.TesterService;
import com.eduhansol.app.viewmodels.SubFactorScoreViewModel;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TesterController {

    private final TesterService _testerService;
    private final TesterResultService _testerResultService;
    private final GroupService _groupService;
    private final NarrativeService _narrativeService;

    private final MarkService _markService;

    private final QuestionService _questionService;
    private final SubFactorService _subFactorService;
    private final FactorService _factorService;
    private final FactorGroupService _factorGroupService;
    private final NormPersonalityService _normPersonalityService;

    public TesterController(TesterService testerService, TesterResultService testerResultService,
            GroupService groupService, NarrativeService narrativeService, MarkService markService,
            QuestionService questionService, SubFactorService subFactorService, FactorService factorService,
            FactorGroupService factorGroupService, NormPersonalityService normPersonalityService) {
        _testerService = testerService;
        _testerResultService = testerResultService;
        _groupService = groupService;
        _narrativeService = narrativeService;
        _markService = markService;
        _questionService = questionService;
        _subFactorService = subFactorService;
        _factorService = factorService;
        _factorGroupService = factorGroupService;
        _normPersonalityService = normPersonalityService;
    }

    @GetMapping("/tester/index")
    public String index(int groupId, Model model, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "row", defaultValue = Constants.ROW_STR) int row) {
        Pageable pageable = PageRequest.of(pageNo - 1, row, Direction.DESC, "id");
        List<Tester> list = _testerService.getList(groupId, pageable).getContent();
        int count = _testerService.getCount(groupId);

        int index = count - (pageNo - 1) * row;
        int totalPageNo = (count + (row - 1)) / row;
        if(totalPageNo < pageNo){
            return "redirect:/tester/index?pageNo=1&row=" + row;
        }

        int startPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            startPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT - Constants.PAGE_COUNT + 1;
        } else {
            startPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT + 1;
        }

        int endPageNo = startPageNo + Constants.PAGE_COUNT - 1;
        if (endPageNo > totalPageNo)
            endPageNo = totalPageNo;

        int previusPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            previusPageNo = (pageNo / Constants.PAGE_COUNT - 1) * Constants.PAGE_COUNT;
        } else {
            previusPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT;
        }

        int nextPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            nextPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT + 1;
        } else {
            nextPageNo = (pageNo / Constants.PAGE_COUNT + 1) * Constants.PAGE_COUNT + 1;
        }

        model.addAttribute("list", list);
        model.addAttribute("groupId", groupId);
        model.addAttribute("index", index);
        model.addAttribute("pageCount", Constants.PAGE_COUNT);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPageNo", totalPageNo);
        model.addAttribute("startPageNo", startPageNo);
        model.addAttribute("endPageNo", endPageNo);
        model.addAttribute("previusPageNo", previusPageNo);
        model.addAttribute("nextPageNo", nextPageNo);
        model.addAttribute("row", row);
        return "tester/index";
    }

    @GetMapping("/tester/post")
    public String post(int groupId, Model model) {
        Tester tester = new Tester();
        Group group = new Group();
        group.setId(groupId);
        tester.setGroup(group);
        model.addAttribute("tester", tester);
        model.addAttribute("groupId", groupId);
        return "tester/post";
    }

    @PostMapping("/tester/post")
    public String post(@ModelAttribute @Valid Tester tester, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {

            if (tester.getApplyId().length() == 9) {
                tester.setApplyId("0" + tester.getApplyId());
            }

            String tempPhone = tester.getPhone().replace("-", "");

            if (tempPhone.length() == 10) {
                tester.setPhone(
                        tempPhone.substring(0, 3) + "-" + tempPhone.substring(3, 6) + "-" + tempPhone.substring(6, 10));
            } else if (tempPhone.length() == 11) {
                tester.setPhone(
                        tempPhone.substring(0, 3) + "-" + tempPhone.substring(3, 7) + "-" + tempPhone.substring(7, 11));
            }

            // 지원자 ID 중복 여부 체크
            int existApplyIdTesterCount = _testerService.getCount(tester.getApplyId());
            if (existApplyIdTesterCount > 0) {
                if (!tester.getIsRetry()) {
                    model.addAttribute("errorMessage", "이미 등록된 지원자 ID입니다.");
                    model.addAttribute("tester", tester);
                    model.addAttribute("groupId", tester.getGroup().getId());
                    return "tester/post";
                }
            }

            if (tester.getEmail() == null || tester.getEmail().isEmpty())
                tester.setEmail("");

            Group group = _groupService.get(tester.getGroup().getId());
            if (group != null) {
                tester.setTime(group.getNormPersonality().getTimeout());
            } else {
                tester.setTime(1800);
            }

            tester.setAuthKey(RandomAuthKeyGenerator());
            tester.setTestState(0);
            tester.setStartDateTime(LocalDateTime.now());
            tester.setCompletedDateTime(LocalDateTime.now());
            tester.setCreatedDateTime(LocalDateTime.now());

            tester.setIsDelete(false);
            tester.setTestResult(false);
            _testerService.post(tester);

            int groupId = tester.getGroup().getId();
            return "redirect:/tester/index?groupId=" + groupId;
        }

        model.addAttribute("tester", tester);
        model.addAttribute("groupId", tester.getGroup().getId());
        return "tester/post";
    }

    @GetMapping("/tester/update")
    public String update(int id, Model model) {
        Tester tester = _testerService.get(id);
        tester.setId(id);
        model.addAttribute("tester", tester);
        model.addAttribute("groupId", tester.getGroup().getId());
        return "tester/update";
    }

    @PostMapping("/tester/update")
    public String update(@ModelAttribute @Valid Tester entity, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {

            if (entity.getApplyId().length() == 9) {
                entity.setApplyId("0" + entity.getApplyId());
            }

            String tempPhone = entity.getPhone().replace("-", "");

            if (tempPhone.length() == 10) {
                entity.setPhone(
                        tempPhone.substring(0, 3) + "-" + tempPhone.substring(3, 6) + "-" + tempPhone.substring(6, 10));
            } else if (tempPhone.length() == 11) {
                entity.setPhone(
                        tempPhone.substring(0, 3) + "-" + tempPhone.substring(3, 7) + "-" + tempPhone.substring(7, 11));
            }

            // 지원자 ID 중복 여부 체크
            int existApplyIdTesterCount = _testerService.getCount(entity.getApplyId());
            if (existApplyIdTesterCount > 1) {
                model.addAttribute("errorMessage", "이미 등록된 지원자 ID입니다.");
                model.addAttribute("tester", entity);
                return "tester/post";
            }

            Tester tester = _testerService.get(entity.getId());
            tester.setApplyId(entity.getApplyId());
            tester.setName(entity.getName());
            tester.setBirthday(entity.getBirthday());
            tester.setPhone(entity.getPhone());

            _testerService.update(tester);

            int groupId = tester.getGroup().getId();
            return "redirect:/tester/index?groupId=" + groupId;
        }
        model.addAttribute("tester", entity);
        return "tester/update";
    }

    @PostMapping("/tester/delete")
    public String delete(int id) {
        Tester tester = _testerService.get(id);
        if (tester == null)
            return "error";

        _testerResultService.deleteByTesterId(id);
        _testerService.delete(id);
        return "redirect:/tester/index?groupId=" + tester.getGroup().getId();
    }

    @GetMapping("/tester/downloadPersonalReport")
    public void downloadPersonalReport(int testerId, HttpServletResponse response, Model model) throws Exception {
        Tester tester = _testerService.get(testerId);
        if (tester == null || tester.getTestState() != Constants.TEST_STATE_TESTED)
            return;

        Group group = _groupService.get(tester.getGroup().getId());

        if (group == null)
            return;

        TesterResult testerResult = _testerResultService.findByTesterid(testerId);
        if (testerResult == null)
            return;

        List<SubFactorScoreViewModel> subFactorScoreViewModels = new ArrayList<>();
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(1, testerResult.getSubFactor1T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(2, testerResult.getSubFactor2T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(3, testerResult.getSubFactor3T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(4, testerResult.getSubFactor4T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(5, testerResult.getSubFactor5T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(6, testerResult.getSubFactor6T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(7, testerResult.getSubFactor7T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(8, testerResult.getSubFactor8T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(9, testerResult.getSubFactor9T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(10, testerResult.getSubFactor10T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(11, testerResult.getSubFactor11T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(12, testerResult.getSubFactor12T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(13, testerResult.getSubFactor13T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(14, testerResult.getSubFactor14T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(15, testerResult.getSubFactor15T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(16, testerResult.getSubFactor16T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(17, testerResult.getSubFactor17T()));
        subFactorScoreViewModels.add(new SubFactorScoreViewModel(18, testerResult.getSubFactor18T()));

        // 점수 높은 순으로 정렬
        subFactorScoreViewModels = subFactorScoreViewModels.stream()
                .sorted(Comparator.comparingDouble(SubFactorScoreViewModel::getTScore).reversed())
                .collect(Collectors.toList());

        String fileName = group.getId() + "_" + group.getName() + "_" + tester.getName() + "_" + tester.getAuthKey();

        Resource template = new ClassPathResource("personal_report.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(template.getInputStream());
        XSSFSheet sheet = null;
        XSSFRow row = null;
        XSSFCell cell = null;

        XSSFCellStyle alignCenterStyle = workbook.createCellStyle();
        alignCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        alignCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle backgroundGray = workbook.createCellStyle();
        backgroundGray.setFillForegroundColor(new XSSFColor(new byte[] { (byte) 208, (byte) 206, (byte) 206 }, null));
        backgroundGray.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundGray.setAlignment(HorizontalAlignment.CENTER);
        backgroundGray.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle backgroundPink = workbook.createCellStyle();
        backgroundPink.setFillForegroundColor(new XSSFColor(new byte[] { (byte) 252, (byte) 228, (byte) 214 }, null));
        backgroundPink.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundPink.setAlignment(HorizontalAlignment.CENTER);
        backgroundPink.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle backgroundYellow = workbook.createCellStyle();
        backgroundYellow.setFillForegroundColor(new XSSFColor(new byte[] { (byte) 255, (byte) 242, (byte) 204 }, null));
        backgroundYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundYellow.setAlignment(HorizontalAlignment.CENTER);
        backgroundYellow.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle backgroundBlue = workbook.createCellStyle();
        XSSFFont whiteFont = workbook.createFont();
        whiteFont.setColor(new XSSFColor(new byte[] { (byte) 255, (byte) 255, (byte) 255 }, null));
        backgroundBlue.setFillForegroundColor(new XSSFColor(new byte[] { (byte) 32, (byte) 56, (byte) 100 }, null));
        backgroundBlue.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundBlue.setAlignment(HorizontalAlignment.CENTER);
        backgroundBlue.setVerticalAlignment(VerticalAlignment.CENTER);
        backgroundBlue.setFont(whiteFont);

        sheet = workbook.getSheetAt(0);

        // 날짜
        row = sheet.getRow(6);
        cell = row.getCell(53);
        cell.setCellValue(tester.getCompletedDateTime().toLocalDate().toString());

        // 지원자 ID
        row = sheet.getRow(11);
        cell = row.getCell(7);
        cell.setCellValue(tester.getApplyId());

        // 부서명
        row = sheet.getRow(11);
        cell = row.getCell(30);
        cell.setCellValue(tester.getGroup().getAdmin().getDepartment().getName());

        // 이름
        row = sheet.getRow(11);
        cell = row.getCell(56);
        cell.setCellValue(tester.getName());

        // 응답성실성
        row = sheet.getRow(29);
        cell = row.getCell(35);
        if (testerResult.getDev() <= 0.41) {
            cell.setCellValue("Fail");
            cell.setCellStyle(backgroundPink);
        } else {
            cell.setCellValue("Pass");
            cell.setCellStyle(backgroundGray);
        }

        // 바람직성
        row = sheet.getRow(36);
        cell = row.getCell(35);
        if (testerResult.getExtremeMark1Count() >= 82) {
            cell.setCellValue("Fail");
            cell.setCellStyle(backgroundPink);
        } else {
            cell.setCellValue("Pass");
            cell.setCellStyle(backgroundGray);
        }

        // 응답 일관성
        row = sheet.getRow(43);
        cell = row.getCell(35);
        if (ConvertSten(testerResult.getConsistencyTScore()) >= 10) {
            cell.setCellValue("Fail");
            cell.setCellStyle(backgroundPink);
        } else {
            cell.setCellValue("Pass");
            cell.setCellStyle(backgroundGray);
        }

        // 응답 신뢰성 Pass/Fail
        row = sheet.getRow(28);
        cell = row.getCell(63);
        if (testerResult.getDev() <= 0.41 || testerResult.getExtremeMark1Count() >= 82
                || ConvertSten(testerResult.getConsistencyTScore()) >= 10) {
            cell.setCellValue("Fail");
            cell.setCellStyle(backgroundPink);
        } else {
            cell.setCellValue("Pass");
            cell.setCellStyle(backgroundGray);
        }

        // 적합 / 부적합 표시
        row = sheet.getRow(19);
        cell = row.getCell(61);
        if (testerResult.getDev() <= 0.41 || testerResult.getExtremeMark1Count() >= 82
                || ConvertSten(testerResult.getConsistencyTScore()) >= 10
                || testerResult.getPersonalityTScore() <= 30) {
            cell.setCellValue("부적합");
            cell.setCellStyle(backgroundPink);
        } else {
            cell.setCellValue("적합");
            cell.setCellStyle(backgroundBlue);
        }

        // 인성 종합
        row = sheet.getRow(34);
        cell = row.getCell(2);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getPersonalityTScore()));

        // 인성종합 등급
        row = sheet.getRow(34);
        cell = row.getCell(10);
        cell.setCellValue(ConvertRank(testerResult.getPersonalityTScore()));

        // 인성종합 Pass/Fail
        row = sheet.getRow(34);
        cell = row.getCell(18);
        if (testerResult.getPersonalityTScore() <= 30) {
            cell.setCellValue("Fail");
        } else {
            cell.setCellValue("Pass");
        }

        // 지도교사 요구역량 - 성실성
        row = sheet.getRow(65);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor1T()));

        // 지도교사 요구역량 - 성실성(등급)
        row = sheet.getRow(65);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor1T()));

        // 지도교사 요구역량 - 성실성(T)
        row = sheet.getRow(69);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor1T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // 지도교사 요구역량 - 사회성
        row = sheet.getRow(72);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor2T()));

        // 지도교사 요구역량 - 사회성(등급)
        row = sheet.getRow(72);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor2T()));

        // 지도교사 요구역량 - 사회성(T)
        row = sheet.getRow(76);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor2T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // 지도교사 요구역량 - 자기개발
        row = sheet.getRow(79);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor3T()));

        // 지도교사 요구역량 - 자기계발(등급)
        row = sheet.getRow(79);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor3T()));

        // 지도교사 요구역량 - 자기계발(T)
        row = sheet.getRow(83);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor3T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // 지도교사 요구역량 - 자기조절
        row = sheet.getRow(86);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor4T()));

        // 지도교사 요구역량 - 자기계발(등급)
        row = sheet.getRow(86);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor4T()));

        // 지도교사 요구역량 - 자기계발(T)
        row = sheet.getRow(90);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor4T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // 지도교사 요구역량 - 성취지향
        row = sheet.getRow(93);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor5T()));

        // 지도교사 요구역량 - 성취지향(등급)
        row = sheet.getRow(93);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor5T()));

        // 지도교사 요구역량 - 성취지향(T)
        row = sheet.getRow(97);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor5T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // 지도교사 요구역량 - 고객지향
        row = sheet.getRow(100);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getFactor6T()));

        // 지도교사 요구역량 - 고객지향(등급)
        row = sheet.getRow(100);
        cell = row.getCell(37);
        cell.setCellValue(ConvertRank(testerResult.getFactor6T()));

        // 지도교사 요구역량 - 고객지향(T)
        row = sheet.getRow(104);
        cell = row.getCell(37);
        cell.setCellValue(Math.round(ConvertMaxMinTScore(testerResult.getFactor6T()) * 10) / 10.0);

        // -----------------------------------------------------------------

        // Scripts 장점/약점
        String strengthScript = "";
        String weaknessScript = "";

        SubFactorScoreViewModel narrativeSubFactor1 = subFactorScoreViewModels.get(0);
        SubFactorScoreViewModel narrativeSubFactor2 = subFactorScoreViewModels.get(1);
        SubFactorScoreViewModel narrativeSubFactor3 = subFactorScoreViewModels.get(16);
        SubFactorScoreViewModel narrativeSubFactor4 = subFactorScoreViewModels.get(17);
        List<Narrative> narratives = _narrativeService.getList(narrativeSubFactor1.getSubFactorId());

        for (Narrative narrative : narratives) {
            if (narrative.getMinSten() <= ConvertSten(narrativeSubFactor1.getTScore())
                    && ConvertSten(narrativeSubFactor1.getTScore()) <= narrative.getMaxSten()) {
                strengthScript = narrative.getScript();
                break;
            }
        }

        narratives = _narrativeService.getList(narrativeSubFactor2.getSubFactorId());

        for (Narrative narrative : narratives) {
            if (narrative.getMinSten() <= ConvertSten(narrativeSubFactor2.getTScore())
                    && ConvertSten(narrativeSubFactor2.getTScore()) <= narrative.getMaxSten()) {
                strengthScript += " 또한, " + narrative.getScript();
                break;
            }
        }

        narratives = _narrativeService.getList(narrativeSubFactor3.getSubFactorId());

        for (Narrative narrative : narratives) {
            if (narrative.getMinSten() <= ConvertSten(narrativeSubFactor3.getTScore())
                    && ConvertSten(narrativeSubFactor3.getTScore()) <= narrative.getMaxSten()) {
                weaknessScript = narrative.getScript();
                break;
            }
        }

        narratives = _narrativeService.getList(narrativeSubFactor4.getSubFactorId());

        for (Narrative narrative : narratives) {
            if (narrative.getMinSten() <= ConvertSten(narrativeSubFactor4.getTScore())
                    && ConvertSten(narrativeSubFactor4.getTScore()) <= narrative.getMaxSten()) {
                weaknessScript += " 또한, " + narrative.getScript();
                break;
            }
        }

        row = sheet.getRow(115);
        cell = row.getCell(2);
        cell.setCellValue(strengthScript + "\n반면, " + weaknessScript);

        // -----------------------------------------------------------------
        // # 2 Page
        // -----------------------------------------------------------------

        // 날짜
        row = sheet.getRow(141);
        cell = row.getCell(53);
        cell.setCellValue(tester.getCompletedDateTime().toLocalDate().toString());

        // -----------------------------------------------------------------

        // 지원자 ID
        row = sheet.getRow(146);
        cell = row.getCell(7);
        cell.setCellValue(tester.getApplyId());

        // 부서명
        row = sheet.getRow(146);
        cell = row.getCell(30);
        cell.setCellValue(tester.getGroup().getAdmin().getDepartment().getName());

        // 이름
        row = sheet.getRow(146);
        cell = row.getCell(56);
        cell.setCellValue(tester.getName());

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 계획성
        row = sheet.getRow(160);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor1T()));

        row = sheet.getRow(160);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor1T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 공감성
        row = sheet.getRow(164);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor2T()));

        row = sheet.getRow(164);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor2T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 규범준수 3
        row = sheet.getRow(168);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor3T()));

        row = sheet.getRow(168);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor3T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 긍정성 4
        row = sheet.getRow(172);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor4T()));

        row = sheet.getRow(172);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor4T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 도전성 5
        row = sheet.getRow(176);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor5T()));

        row = sheet.getRow(176);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor5T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 사교성 7
        row = sheet.getRow(180);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor7T()));

        row = sheet.getRow(180);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor7T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 서비스지향 8
        row = sheet.getRow(184);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor8T()));

        row = sheet.getRow(184);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor8T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 설득성 9
        row = sheet.getRow(188);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor9T()));

        row = sheet.getRow(188);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor9T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 실행성 11
        row = sheet.getRow(192);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor11T()));

        row = sheet.getRow(192);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor11T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 적응성 12
        row = sheet.getRow(196);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor12T()));

        row = sheet.getRow(196);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor12T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 정서조절 13
        row = sheet.getRow(200);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor13T()));

        row = sheet.getRow(200);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor13T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 주도성 15
        row = sheet.getRow(204);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor15T()));

        row = sheet.getRow(204);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor15T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 책임감 16
        row = sheet.getRow(208);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor16T()));

        row = sheet.getRow(208);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor16T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 학습추구 17
        row = sheet.getRow(212);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor17T()));

        row = sheet.getRow(212);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor17T()));

        // -----------------------------------------------------------------

        // 성격척도 세부점수 - 협동성 18
        row = sheet.getRow(216);
        cell = row.getCell(70);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor18T()));

        row = sheet.getRow(216);
        cell = row.getCell(38);
        cell.setCellValue(ConvertRank(testerResult.getSubFactor18T()));

        // -----------------------------------------------------------------

        // 잠재위험요인 - 공격성 19
        row = sheet.getRow(230);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor19T()));

        row = sheet.getRow(230);
        cell = row.getCell(12);
        if (testerResult.getSubFactor19T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 불안/우울 20
        row = sheet.getRow(235);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor20T()));

        row = sheet.getRow(235);
        cell = row.getCell(12);
        if (testerResult.getSubFactor20T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 의존성 22
        row = sheet.getRow(240);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor22T()));

        row = sheet.getRow(240);
        cell = row.getCell(12);
        if (testerResult.getSubFactor22T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 자아취약성 23
        row = sheet.getRow(245);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor23T()));

        row = sheet.getRow(245);
        cell = row.getCell(12);
        if (testerResult.getSubFactor23T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 충동성 24
        row = sheet.getRow(250);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor24T()));

        row = sheet.getRow(250);
        cell = row.getCell(12);
        if (testerResult.getSubFactor24T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 편집증 25
        row = sheet.getRow(255);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor25T()));

        row = sheet.getRow(255);
        cell = row.getCell(12);
        if (testerResult.getSubFactor25T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        // 잠재위험요인 - 성 위험요인 21
        row = sheet.getRow(260);
        cell = row.getCell(8);
        cell.setCellValue(ConvertMaxMinTScore(testerResult.getSubFactor21T()));

        row = sheet.getRow(260);
        cell = row.getCell(12);
        if (testerResult.getSubFactor21T() > 65) {
            cell.setCellValue("주의");
            cell.setCellStyle(backgroundYellow);
        } else {
            cell.setCellValue("-");
        }

        // -----------------------------------------------------------------

        response.setContentType("Application/Msexcel");
        response.setHeader("Content-Disposition",
                "ATTachment; Filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");

        OutputStream fileOut = response.getOutputStream();
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();

        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/tester/rescoring")
    public void reScoring(int testerId) {
        try {
            Tester tester = _testerService.get(testerId);

            Mark mark = _markService.get(testerId);

            // TesterResult existTesterResult =
            // _testerResultService.findByTesterid(testerId);
            // if(existTesterResult != null){
            _testerResultService.deleteByTesterId(testerId);
            // }

            int normPersonalityId = tester.getGroup().getNormPersonality().getId();

            NormPersonality normPersonality = _normPersonalityService.get(normPersonalityId);
            List<Question> questions = _questionService.getList(normPersonalityId);
            List<SubFactor> subFactors = _subFactorService.getList(normPersonalityId);
            List<Factor> factors = _factorService.getList(normPersonalityId);
            List<FactorGroup> factorGroups = _factorGroupService.getList(normPersonalityId);

            double[] subFactorNormativeScoreArray = new double[subFactors.size() + 1];
            double[] subFactorIpsativeScoreArray = new double[subFactors.size() + 1];

            double[] subFactorNormativeTScoreArray = new double[subFactors.size() + 1];
            double[] subFactorIpsativeTScoreArray = new double[subFactors.size() + 1];
            double[] subFactorScoreArray = new double[subFactors.size() + 1];
            double[] subFactorTScoreArray = new double[subFactors.size() + 1];

            double[] factorScoreArray = new double[factors.size() + 1];
            double[] factorTScoreArray = new double[factors.size() + 1];

            int[] ipsativeNoMarkCount = new int[subFactors.size() + 1]; // 무응답 응답
            int[] ipsativeFarMarkCount = new int[subFactors.size() + 1]; // 멀다 응답
            int[] ipsativeNearMarkCount = new int[subFactors.size() + 1]; // 가깝다 응답
            int[] subFactorCase = new int[subFactors.size() + 1];

            double[] devMarks = new double[questions.size()];
            int devMarksCount = 0;
            double variance = 0.0;

            int mark1Count = 0;
            int extremeMark1Count = 0;
            double consistencyScore = 0;
            double personalityScore = 0;

            for (Question question : questions) {
                String singleMark1 = mark.getMark1().toString().substring(question.getQuestionId() - 1,
                        question.getQuestionId());
                String singleMark2 = mark.getConvertMark2().toString().substring(question.getQuestionId() - 1,
                        question.getQuestionId());
                String singlePureMark2 = mark.getMark2().toString().substring(question.getQuestionId() - 1,
                        question.getQuestionId());

                if (singleMark1 != "0") {
                    if (question.getIsReverse() == false) {
                        if (singleMark1.equals("5")) {
                            extremeMark1Count++;
                        }
                    }
                    mark1Count++;
                }

                if (question.getIsReverse() == false) {
                    devMarks[question.getQuestionId() - 1] = Double.parseDouble(singleMark1);
                    devMarksCount++;
                }

                subFactorNormativeScoreArray[question.getSubFactor().getSubFactorId()] += Double
                        .parseDouble(singleMark1);
                subFactorIpsativeScoreArray[question.getSubFactor().getSubFactorId()] += Double
                        .parseDouble(singleMark2);

                switch (singlePureMark2) {
                    case "0":
                        ipsativeNoMarkCount[question.getSubFactor().getSubFactorId()] += 1;
                        break;

                    case "1":
                        ipsativeFarMarkCount[question.getSubFactor().getSubFactorId()] += 1;
                        break;

                    case "2":
                        ipsativeNearMarkCount[question.getSubFactor().getSubFactorId()] += 1;
                        break;
                }
            }

            // 하위척도 고유 번호가 아닌 절대 번호로 수정하기
            // 상위척도도 동일하게 절대 번호로 수정하기.
            for (SubFactor subFactor : subFactors) {
                subFactorNormativeTScoreArray[subFactor.getSubFactorId()] = Math
                        .round(((subFactorNormativeScoreArray[subFactor.getSubFactorId()] - subFactor.getNMean())
                                / subFactor.getNDev() * 10 + 50) * 100)
                        / 100.0;
                subFactorIpsativeTScoreArray[subFactor.getSubFactorId()] = Math
                        .round(((subFactorIpsativeScoreArray[subFactor.getSubFactorId()] - subFactor.getIMean())
                                / subFactor.getIDev() * 10 + 50) * 100)
                        / 100.0;

                subFactorScoreArray[subFactor
                        .getSubFactorId()] = subFactorNormativeTScoreArray[subFactor.getSubFactorId()]
                                * subFactor.getNWeight()
                                + subFactorIpsativeTScoreArray[subFactor.getSubFactorId()] * subFactor.getIWeight();

                subFactorTScoreArray[subFactor.getSubFactorId()] = Math
                        .round(((subFactorScoreArray[subFactor.getSubFactorId()] - subFactor.getMean())
                                / subFactor.getDev() * 10 + 50) * 100)
                        / 100.0;

                // # 응답일관성 계산 2단계
                // '멀, 무, 가 카운트 대소비교 참고자료
                // 'IF (I1가 > I1무 AND I1가 > I1멀) SC1=1.
                if (ipsativeNearMarkCount[subFactor.getSubFactorId()] > ipsativeNoMarkCount[subFactor.getSubFactorId()]
                        && ipsativeNearMarkCount[subFactor.getSubFactorId()] > ipsativeFarMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 1;
                }
                // 'IF (I1무 > I1가 AND I1무 > I1멀) SC1=3.
                else if (ipsativeNoMarkCount[subFactor.getSubFactorId()] > ipsativeNearMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNoMarkCount[subFactor.getSubFactorId()] > ipsativeFarMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 3;
                }
                // 'IF (I1멀 > I1가 AND I1멀 > I1무) SC1=2.
                else if (ipsativeFarMarkCount[subFactor.getSubFactorId()] > ipsativeNearMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeFarMarkCount[subFactor.getSubFactorId()] > ipsativeNoMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 2;
                }
                // 'IF (I1가 = I1무 AND I1가 > I1멀) SC1=1.
                else if (ipsativeNearMarkCount[subFactor.getSubFactorId()] == ipsativeNoMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNearMarkCount[subFactor.getSubFactorId()] > ipsativeFarMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 1;
                }
                // 'IF (I1가 = I1무 AND I1가 < I1멀) SC1=2.
                else if (ipsativeNearMarkCount[subFactor.getSubFactorId()] == ipsativeNoMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNearMarkCount[subFactor.getSubFactorId()] < ipsativeFarMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 2;
                }
                // 'IF (I1무 = I1멀 AND I1무 > I1가) SC1=2.
                else if (ipsativeNoMarkCount[subFactor.getSubFactorId()] == ipsativeFarMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNoMarkCount[subFactor.getSubFactorId()] > ipsativeNearMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 2;
                }
                // 'IF (I1무 = I1멀 AND I1무 < I1가) SC1=1.
                else if (ipsativeNoMarkCount[subFactor.getSubFactorId()] == ipsativeFarMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNoMarkCount[subFactor.getSubFactorId()] < ipsativeNearMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 1;
                }
                // 'IF (I1가 = I1무 AND I1무 = I1멀) SC1=1.
                else if (ipsativeNearMarkCount[subFactor.getSubFactorId()] == ipsativeNoMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNoMarkCount[subFactor.getSubFactorId()] == ipsativeFarMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 1;
                }
                // 'IF (I1가 = I1멀 AND I1가 > I1무) SC1=1.
                else if (ipsativeNearMarkCount[subFactor.getSubFactorId()] == ipsativeFarMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNearMarkCount[subFactor.getSubFactorId()] > ipsativeNoMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 1;
                }
                // 'IF (I1가 = I1멀 AND I1가 < I1무) SC1=3.
                else if (ipsativeNearMarkCount[subFactor.getSubFactorId()] == ipsativeFarMarkCount[subFactor
                        .getSubFactorId()]
                        && ipsativeNearMarkCount[subFactor.getSubFactorId()] < ipsativeNoMarkCount[subFactor
                                .getSubFactorId()]) {
                    subFactorCase[subFactor.getSubFactorId()] = 3;
                }

            }

            for (SubFactor subFactor : subFactors) {
                if (subFactor.getIsReverse() == false && subFactor.getIsDummy() == false
                        && subFactor.getSubFactorId() != 6 && subFactor.getSubFactorId() != 10
                        && subFactor.getSubFactorId() != 14) {
                    switch (subFactorCase[subFactor.getSubFactorId()]) {
                        case 1:
                            consistencyScore += (ipsativeNearMarkCount[subFactor.getSubFactorId()] * 2)
                                    + (ipsativeFarMarkCount[subFactor.getSubFactorId()] * -2)
                                    + (ipsativeNoMarkCount[subFactor.getSubFactorId()] * -1);
                            break;

                        case 2:
                            consistencyScore += (ipsativeNearMarkCount[subFactor.getSubFactorId()] * -2)
                                    + (ipsativeFarMarkCount[subFactor.getSubFactorId()] * 2)
                                    + (ipsativeNoMarkCount[subFactor.getSubFactorId()] * -1);
                            break;

                        case 3:
                            consistencyScore += (ipsativeNearMarkCount[subFactor.getSubFactorId()] * -1)
                                    + (ipsativeFarMarkCount[subFactor.getSubFactorId()] * -1)
                                    + (ipsativeNoMarkCount[subFactor.getSubFactorId()] * 1);
                            break;
                    }
                }
            }

            for (Factor factor : factors) {
                List<FactorGroup> factorSubFactors = factorGroups.stream()
                        .filter(i -> i.getFactor().getId() == factor.getFactorId()).collect(Collectors.toList());

                for (FactorGroup factorGroup : factorSubFactors) {
                    factorScoreArray[factor.getFactorId()] += subFactorTScoreArray[factorGroup.getSubFactor()
                            .getSubFactorId()]; // 상위 요소 원점수
                    // 구하기
                }
                factorTScoreArray[factor.getFactorId()] = Math.round(
                        ((factorScoreArray[factor.getFactorId()] - factor.getMean()) / factor.getDev() * 10 + 50) * 100)
                        / 100.0; // 상위 요소 T 점수 구하기
                personalityScore += factorTScoreArray[factor.getFactorId()];
            }

            double personalityTScore = Math
                    .round(((personalityScore - normPersonality.getMean()) / normPersonality.getDev() * 10 + 50) * 100)
                    / 100.0;
            double devMean = average(devMarks, devMarksCount);
            int notDummyQuestionCount = 0;

            for (Question question : questions) {
                String singleMark1 = mark.getMark1().toString().substring(question.getQuestionId() - 1,
                        question.getQuestionId());

                if (question.getIsReverse() == false) {

                    variance += Math.pow(Double.parseDouble(singleMark1) - devMean, 2);
                    notDummyQuestionCount++;
                }
            }

            double dev = Math.sqrt(variance / (notDummyQuestionCount - 1));
            double consistencyTScore = Math.round(((consistencyScore - normPersonality.getConsistencyMean())
                    / normPersonality.getConsistencyDev() * 10 + 50) * 100) / 100.0;

            if (dev <= 0.41 || extremeMark1Count >= 82 || ConvertSten(consistencyTScore) >= 10
                    || personalityTScore <= 30) {
                tester.setTestResult(false);
                _testerService.update(tester);
            } else {
                tester.setTestResult(true);
                _testerService.update(tester);
            }

            TesterResult testerResult = new TesterResult();
            testerResult.setTester(tester);
            testerResult.setNormPersonality(normPersonality);
            testerResult.setPersonalityScore(personalityScore);
            testerResult.setPersonalityTScore(personalityTScore);
            testerResult.setDev(dev);
            testerResult.setConsistencyScore(consistencyScore);
            testerResult.setConsistencyTScore(consistencyTScore);
            testerResult.setMark1Count(mark1Count);
            testerResult.setExtremeMark1Count(extremeMark1Count);

            testerResult.setSubFactorN1S(subFactorNormativeScoreArray[1]);
            testerResult.setSubFactorN2S(subFactorNormativeScoreArray[2]);
            testerResult.setSubFactorN3S(subFactorNormativeScoreArray[3]);
            testerResult.setSubFactorN4S(subFactorNormativeScoreArray[4]);
            testerResult.setSubFactorN5S(subFactorNormativeScoreArray[5]);
            testerResult.setSubFactorN6S(subFactorNormativeScoreArray[6]);
            testerResult.setSubFactorN7S(subFactorNormativeScoreArray[7]);
            testerResult.setSubFactorN8S(subFactorNormativeScoreArray[8]);
            testerResult.setSubFactorN9S(subFactorNormativeScoreArray[9]);
            testerResult.setSubFactorN10S(subFactorNormativeScoreArray[10]);
            testerResult.setSubFactorN11S(subFactorNormativeScoreArray[11]);
            testerResult.setSubFactorN12S(subFactorNormativeScoreArray[12]);
            testerResult.setSubFactorN13S(subFactorNormativeScoreArray[13]);
            testerResult.setSubFactorN14S(subFactorNormativeScoreArray[14]);
            testerResult.setSubFactorN15S(subFactorNormativeScoreArray[15]);
            testerResult.setSubFactorN16S(subFactorNormativeScoreArray[16]);
            testerResult.setSubFactorN17S(subFactorNormativeScoreArray[17]);
            testerResult.setSubFactorN18S(subFactorNormativeScoreArray[18]);
            testerResult.setSubFactorN19S(subFactorNormativeScoreArray[19]);
            testerResult.setSubFactorN20S(subFactorNormativeScoreArray[20]);
            testerResult.setSubFactorN21S(subFactorNormativeScoreArray[21]);
            testerResult.setSubFactorN22S(subFactorNormativeScoreArray[22]);
            testerResult.setSubFactorN23S(subFactorNormativeScoreArray[23]);
            testerResult.setSubFactorN24S(subFactorNormativeScoreArray[24]);
            testerResult.setSubFactorN25S(subFactorNormativeScoreArray[25]);
            testerResult.setSubFactorN26S(subFactorNormativeScoreArray[26]);
            testerResult.setSubFactorN27S(subFactorNormativeScoreArray[27]);
            testerResult.setSubFactorN28S(subFactorNormativeScoreArray[28]);

            testerResult.setSubFactorI1S(subFactorIpsativeScoreArray[1]);
            testerResult.setSubFactorI2S(subFactorIpsativeScoreArray[2]);
            testerResult.setSubFactorI3S(subFactorIpsativeScoreArray[3]);
            testerResult.setSubFactorI4S(subFactorIpsativeScoreArray[4]);
            testerResult.setSubFactorI5S(subFactorIpsativeScoreArray[5]);
            testerResult.setSubFactorI6S(subFactorIpsativeScoreArray[6]);
            testerResult.setSubFactorI7S(subFactorIpsativeScoreArray[7]);
            testerResult.setSubFactorI8S(subFactorIpsativeScoreArray[8]);
            testerResult.setSubFactorI9S(subFactorIpsativeScoreArray[9]);
            testerResult.setSubFactorI10S(subFactorIpsativeScoreArray[10]);
            testerResult.setSubFactorI11S(subFactorIpsativeScoreArray[11]);
            testerResult.setSubFactorI12S(subFactorIpsativeScoreArray[12]);
            testerResult.setSubFactorI13S(subFactorIpsativeScoreArray[13]);
            testerResult.setSubFactorI14S(subFactorIpsativeScoreArray[14]);
            testerResult.setSubFactorI15S(subFactorIpsativeScoreArray[15]);
            testerResult.setSubFactorI16S(subFactorIpsativeScoreArray[16]);
            testerResult.setSubFactorI17S(subFactorIpsativeScoreArray[17]);
            testerResult.setSubFactorI18S(subFactorIpsativeScoreArray[18]);
            testerResult.setSubFactorI19S(subFactorIpsativeScoreArray[19]);
            testerResult.setSubFactorI20S(subFactorIpsativeScoreArray[20]);
            testerResult.setSubFactorI21S(subFactorIpsativeScoreArray[21]);
            testerResult.setSubFactorI22S(subFactorIpsativeScoreArray[22]);
            testerResult.setSubFactorI23S(subFactorIpsativeScoreArray[23]);
            testerResult.setSubFactorI24S(subFactorIpsativeScoreArray[24]);
            testerResult.setSubFactorI25S(subFactorIpsativeScoreArray[25]);
            testerResult.setSubFactorI26S(subFactorIpsativeScoreArray[26]);
            testerResult.setSubFactorI27S(subFactorIpsativeScoreArray[27]);
            testerResult.setSubFactorI28S(subFactorIpsativeScoreArray[28]);

            testerResult.setSubFactorN1T(subFactorNormativeTScoreArray[1]);
            testerResult.setSubFactorN2T(subFactorNormativeTScoreArray[2]);
            testerResult.setSubFactorN3T(subFactorNormativeTScoreArray[3]);
            testerResult.setSubFactorN4T(subFactorNormativeTScoreArray[4]);
            testerResult.setSubFactorN5T(subFactorNormativeTScoreArray[5]);
            testerResult.setSubFactorN6T(subFactorNormativeTScoreArray[6]);
            testerResult.setSubFactorN7T(subFactorNormativeTScoreArray[7]);
            testerResult.setSubFactorN8T(subFactorNormativeTScoreArray[8]);
            testerResult.setSubFactorN9T(subFactorNormativeTScoreArray[9]);
            testerResult.setSubFactorN10T(subFactorNormativeTScoreArray[10]);
            testerResult.setSubFactorN11T(subFactorNormativeTScoreArray[11]);
            testerResult.setSubFactorN12T(subFactorNormativeTScoreArray[12]);
            testerResult.setSubFactorN13T(subFactorNormativeTScoreArray[13]);
            testerResult.setSubFactorN14T(subFactorNormativeTScoreArray[14]);
            testerResult.setSubFactorN15T(subFactorNormativeTScoreArray[15]);
            testerResult.setSubFactorN16T(subFactorNormativeTScoreArray[16]);
            testerResult.setSubFactorN17T(subFactorNormativeTScoreArray[17]);
            testerResult.setSubFactorN18T(subFactorNormativeTScoreArray[18]);
            testerResult.setSubFactorN19T(subFactorNormativeTScoreArray[19]);
            testerResult.setSubFactorN20T(subFactorNormativeTScoreArray[20]);
            testerResult.setSubFactorN21T(subFactorNormativeTScoreArray[21]);
            testerResult.setSubFactorN22T(subFactorNormativeTScoreArray[22]);
            testerResult.setSubFactorN23T(subFactorNormativeTScoreArray[23]);
            testerResult.setSubFactorN24T(subFactorNormativeTScoreArray[24]);
            testerResult.setSubFactorN25T(subFactorNormativeTScoreArray[25]);
            testerResult.setSubFactorN26T(subFactorNormativeTScoreArray[26]);
            testerResult.setSubFactorN27T(subFactorNormativeTScoreArray[27]);
            testerResult.setSubFactorN28T(subFactorNormativeTScoreArray[28]);

            testerResult.setSubFactorI1T(subFactorIpsativeTScoreArray[1]);
            testerResult.setSubFactorI2T(subFactorIpsativeTScoreArray[2]);
            testerResult.setSubFactorI3T(subFactorIpsativeTScoreArray[3]);
            testerResult.setSubFactorI4T(subFactorIpsativeTScoreArray[4]);
            testerResult.setSubFactorI5T(subFactorIpsativeTScoreArray[5]);
            testerResult.setSubFactorI6T(subFactorIpsativeTScoreArray[6]);
            testerResult.setSubFactorI7T(subFactorIpsativeTScoreArray[7]);
            testerResult.setSubFactorI8T(subFactorIpsativeTScoreArray[8]);
            testerResult.setSubFactorI9T(subFactorIpsativeTScoreArray[9]);
            testerResult.setSubFactorI10T(subFactorIpsativeTScoreArray[10]);
            testerResult.setSubFactorI11T(subFactorIpsativeTScoreArray[11]);
            testerResult.setSubFactorI12T(subFactorIpsativeTScoreArray[12]);
            testerResult.setSubFactorI13T(subFactorIpsativeTScoreArray[13]);
            testerResult.setSubFactorI14T(subFactorIpsativeTScoreArray[14]);
            testerResult.setSubFactorI15T(subFactorIpsativeTScoreArray[15]);
            testerResult.setSubFactorI16T(subFactorIpsativeTScoreArray[16]);
            testerResult.setSubFactorI17T(subFactorIpsativeTScoreArray[17]);
            testerResult.setSubFactorI18T(subFactorIpsativeTScoreArray[18]);
            testerResult.setSubFactorI19T(subFactorIpsativeTScoreArray[19]);
            testerResult.setSubFactorI20T(subFactorIpsativeTScoreArray[20]);
            testerResult.setSubFactorI21T(subFactorIpsativeTScoreArray[21]);
            testerResult.setSubFactorI22T(subFactorIpsativeTScoreArray[22]);
            testerResult.setSubFactorI23T(subFactorIpsativeTScoreArray[23]);
            testerResult.setSubFactorI24T(subFactorIpsativeTScoreArray[24]);
            testerResult.setSubFactorI25T(subFactorIpsativeTScoreArray[25]);
            testerResult.setSubFactorI26T(subFactorIpsativeTScoreArray[26]);
            testerResult.setSubFactorI27T(subFactorIpsativeTScoreArray[27]);
            testerResult.setSubFactorI28T(subFactorIpsativeTScoreArray[28]);

            testerResult.setSubFactor1S(subFactorScoreArray[1]);
            testerResult.setSubFactor2S(subFactorScoreArray[2]);
            testerResult.setSubFactor3S(subFactorScoreArray[3]);
            testerResult.setSubFactor4S(subFactorScoreArray[4]);
            testerResult.setSubFactor5S(subFactorScoreArray[5]);
            testerResult.setSubFactor6S(subFactorScoreArray[6]);
            testerResult.setSubFactor7S(subFactorScoreArray[7]);
            testerResult.setSubFactor8S(subFactorScoreArray[8]);
            testerResult.setSubFactor9S(subFactorScoreArray[9]);
            testerResult.setSubFactor10S(subFactorScoreArray[10]);
            testerResult.setSubFactor11S(subFactorScoreArray[11]);
            testerResult.setSubFactor12S(subFactorScoreArray[12]);
            testerResult.setSubFactor13S(subFactorScoreArray[13]);
            testerResult.setSubFactor14S(subFactorScoreArray[14]);
            testerResult.setSubFactor15S(subFactorScoreArray[15]);
            testerResult.setSubFactor16S(subFactorScoreArray[16]);
            testerResult.setSubFactor17S(subFactorScoreArray[17]);
            testerResult.setSubFactor18S(subFactorScoreArray[18]);
            testerResult.setSubFactor19S(subFactorScoreArray[19]);
            testerResult.setSubFactor20S(subFactorScoreArray[20]);
            testerResult.setSubFactor21S(subFactorScoreArray[21]);
            testerResult.setSubFactor22S(subFactorScoreArray[22]);
            testerResult.setSubFactor23S(subFactorScoreArray[23]);
            testerResult.setSubFactor24S(subFactorScoreArray[24]);
            testerResult.setSubFactor25S(subFactorScoreArray[25]);
            testerResult.setSubFactor26S(subFactorScoreArray[26]);
            testerResult.setSubFactor27S(subFactorScoreArray[27]);
            testerResult.setSubFactor28S(subFactorScoreArray[28]);

            testerResult.setSubFactor1T(subFactorTScoreArray[1]);
            testerResult.setSubFactor2T(subFactorTScoreArray[2]);
            testerResult.setSubFactor3T(subFactorTScoreArray[3]);
            testerResult.setSubFactor4T(subFactorTScoreArray[4]);
            testerResult.setSubFactor5T(subFactorTScoreArray[5]);
            testerResult.setSubFactor6T(subFactorTScoreArray[6]);
            testerResult.setSubFactor7T(subFactorTScoreArray[7]);
            testerResult.setSubFactor8T(subFactorTScoreArray[8]);
            testerResult.setSubFactor9T(subFactorTScoreArray[9]);
            testerResult.setSubFactor10T(subFactorTScoreArray[10]);
            testerResult.setSubFactor11T(subFactorTScoreArray[11]);
            testerResult.setSubFactor12T(subFactorTScoreArray[12]);
            testerResult.setSubFactor13T(subFactorTScoreArray[13]);
            testerResult.setSubFactor14T(subFactorTScoreArray[14]);
            testerResult.setSubFactor15T(subFactorTScoreArray[15]);
            testerResult.setSubFactor16T(subFactorTScoreArray[16]);
            testerResult.setSubFactor17T(subFactorTScoreArray[17]);
            testerResult.setSubFactor18T(subFactorTScoreArray[18]);
            testerResult.setSubFactor19T(subFactorTScoreArray[19]);
            testerResult.setSubFactor20T(subFactorTScoreArray[20]);
            testerResult.setSubFactor21T(subFactorTScoreArray[21]);
            testerResult.setSubFactor22T(subFactorTScoreArray[22]);
            testerResult.setSubFactor23T(subFactorTScoreArray[23]);
            testerResult.setSubFactor24T(subFactorTScoreArray[24]);
            testerResult.setSubFactor25T(subFactorTScoreArray[25]);
            testerResult.setSubFactor26T(subFactorTScoreArray[26]);
            testerResult.setSubFactor27T(subFactorTScoreArray[27]);
            testerResult.setSubFactor28T(subFactorTScoreArray[28]);

            testerResult.setFactor1S(factorScoreArray[1]);
            testerResult.setFactor2S(factorScoreArray[2]);
            testerResult.setFactor3S(factorScoreArray[3]);
            testerResult.setFactor4S(factorScoreArray[4]);
            testerResult.setFactor5S(factorScoreArray[5]);
            testerResult.setFactor6S(factorScoreArray[6]);

            testerResult.setFactor1T(factorTScoreArray[1]);
            testerResult.setFactor2T(factorTScoreArray[2]);
            testerResult.setFactor3T(factorTScoreArray[3]);
            testerResult.setFactor4T(factorTScoreArray[4]);
            testerResult.setFactor5T(factorTScoreArray[5]);
            testerResult.setFactor6T(factorTScoreArray[6]);

            _testerResultService.save(testerResult);
        } catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }

    private static String RandomAuthKeyGenerator() {
        Random random = new Random();
        int key = random.nextInt((9999999 - 1000000) + 1) + 1000000;
        return Integer.toString(key);
    }

    private static int ConvertSten(double tScore) {
        if (tScore > 70)
            return 1;
        else if (tScore > 65)
            return 2;
        else if (tScore > 60)
            return 3;
        else if (tScore > 55)
            return 4;
        else if (tScore > 50)
            return 5;
        else if (tScore > 45)
            return 6;
        else if (tScore > 40)
            return 7;
        else if (tScore > 35)
            return 8;
        else if (tScore > 30)
            return 9;
        else
            return 10;
    }

    private static String ConvertRank(double tScore) {
        if (tScore > 70) {
            return "S";
        } else if (tScore > 60) {
            return "A";
        } else if (tScore > 40) {
            return "B";
        } else if (tScore > 30) {
            return "C";
        } else {
            return "D";
        }
    }

    private static double average(double[] array, int length) {
        double sum = 0.0;

        for (int i = 0; i < array.length; i++)
            sum += array[i];

        return sum / length;

    }

    private static double ConvertMaxMinTScore(double tScore) {
        if (tScore <= 16.1) {
            return 16.1;
        } else if (tScore >= 83.9) {
            return 83.9;
        }
        return tScore;
    }
}
