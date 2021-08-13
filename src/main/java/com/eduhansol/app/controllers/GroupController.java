package com.eduhansol.app.controllers;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Admin;
import com.eduhansol.app.entities.Group;
import com.eduhansol.app.entities.NormPersonality;
import com.eduhansol.app.entities.Tester;
import com.eduhansol.app.entities.TesterResult;
import com.eduhansol.app.services.AdminService;
import com.eduhansol.app.services.GroupService;
import com.eduhansol.app.services.NormPersonalityService;
import com.eduhansol.app.services.TesterResultService;
import com.eduhansol.app.services.TesterService;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController {

    private final GroupService _groupService;
    private final AdminService _adminService;
    private final NormPersonalityService _normPersonalityService;
    private final TesterService _testerService;
    private final TesterResultService _testerResultService;

    @Autowired
    public GroupController(GroupService groupService, AdminService adminService,
            NormPersonalityService normPersonalityService, TesterService testerService,
            TesterResultService testerResultService) {
        _groupService = groupService;
        _adminService = adminService;
        _normPersonalityService = normPersonalityService;
        _testerService = testerService;
        _testerResultService = testerResultService;
    }

    @GetMapping("/group/index")
    public String index(Model model, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "row", defaultValue = Constants.ROW_STR) int row) {
        List<Group> list = new ArrayList<>();
        int count = _groupService.getCount();
        String adminName = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = _adminService.getLogin(adminName);
        if (admin == null)
            return "redirect:/error";

        if (admin.getIsFirstLogin()) {
            return "redirect:/admin/resetPassword";
        }

        Pageable pageable = PageRequest.of(pageNo - 1, row, Direction.DESC, "id");

        if (admin.getRole().getName().equals(Constants.ROLE_ADMIN)) {
            list = _groupService.getList(pageable).getContent();
            count = _groupService.getCount();

        } else if (admin.getRole().getName().equals(Constants.ROLE_HQ)) {
            list = _groupService.getListByHqId(admin.getDepartment().getRegion().getHq().getId(), pageable)
                    .getContent();
            count = _groupService.getCountByHqId(admin.getDepartment().getRegion().getHq().getId());

        } else if (admin.getRole().getName().equals(Constants.ROLE_REGION)) {
            list = _groupService.getListByRegionId(admin.getDepartment().getRegion().getId(), pageable).getContent();
            count = _groupService.getCountByRegionId(admin.getDepartment().getRegion().getId());

        } else if (admin.getRole().getName().equals(Constants.ROLE_DPT)) {
            list = _groupService.getListByAdminId(admin.getId(), pageable).getContent();
            count = _groupService.getCountByAdminId(admin.getId());

        } else {
            return "redirect:/error";
        }

        int index = count - (pageNo - 1) * row;
        int totalPageNo = (count + (row - 1)) / row;
        if(totalPageNo < pageNo){
            return "redirect:/group/index?pageNo=1&row=" + row;
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
        model.addAttribute("index", index);

        model.addAttribute("pageCount", Constants.PAGE_COUNT);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPageNo", totalPageNo);
        model.addAttribute("startPageNo", startPageNo);
        model.addAttribute("endPageNo", endPageNo);
        model.addAttribute("previusPageNo", previusPageNo);
        model.addAttribute("nextPageNo", nextPageNo);
        model.addAttribute("row", row);
        return "group/index";
    }

    @GetMapping("/group/post")
    public String post(@ModelAttribute Group group, Model model) {
        List<NormPersonality> normPersonalities = _normPersonalityService.getList();
        model.addAttribute("group", group);
        model.addAttribute("normPersonalities", normPersonalities);
        return "group/post";
    }

    @PostMapping("/group/post")
    public String post(@ModelAttribute @Valid Group group, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();

            Admin admin = _adminService.getLogin(username);
            NormPersonality normPersonality = new NormPersonality();

            Group lastGroup = _groupService.findFirstByOrderByIdDesc();
            if (lastGroup == null) {
                normPersonality = _normPersonalityService.get(1);
            } else {
                switch (lastGroup.getNormPersonality().getId()) {
                    case 1:
                        normPersonality = _normPersonalityService.get(2);
                        break;
                    case 2:
                        normPersonality = _normPersonalityService.get(3);
                        break;
                    case 3:
                        normPersonality = _normPersonalityService.get(1);
                        break;
                }
            }

            group.setNormPersonality(normPersonality);
            group.setAdmin(admin);
            group.setStartDateTime(LocalDateTime.now());
            group.setEndDateTime(LocalDateTime.now());

            Group result = _groupService.post(group);
            if (result != null)
                return "redirect:/group/index";
        }
        List<NormPersonality> normPersonalities = _normPersonalityService.getList();
        model.addAttribute("group", group);
        model.addAttribute("normPersonalities", normPersonalities);
        return "group/post";
    }

    @GetMapping("/group/update")
    public String update(int id, Model model) {
        Group group = _groupService.get(id);
        List<NormPersonality> normPersonalities = _normPersonalityService.getList();
        model.addAttribute("group", group);
        model.addAttribute("normPersonalities", normPersonalities);
        return "group/update";
    }

    @PostMapping("/group/update")
    public String update(@ModelAttribute @Valid Group group, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Group dbGroup = _groupService.get(group.getId());
            dbGroup.setName(group.getName());

            _groupService.update(dbGroup);
            return "redirect:/group/index";
        }
        List<NormPersonality> normPersonalities = _normPersonalityService.getList();
        model.addAttribute("group", group);
        model.addAttribute("normPersonalities", normPersonalities);
        return "group/update";
    }

    @PostMapping("/group/delete")
    public String delete(int id) {
        int count = _testerService.getCount(id);
        if (count > 0)
            return "redirect:/group/index";
        _groupService.delete(id);
        return "redirect:/group/index";
    }

    @GetMapping("/group/downloadHrReport")
    public void downloadHrReport(int groupId, HttpServletResponse response, Model model) throws Exception {
        Group group = _groupService.get(groupId);

        if (group == null)
            return;

        List<Tester> testers = _testerService.getList(groupId);
        List<TesterResult> testerResults = new ArrayList<>();

        for (Tester tester : testers) {
            if (tester.getTestState() == Constants.TEST_STATE_TESTED) {
                TesterResult testerResult = _testerResultService.findByTesterid(tester.getId());

                if (testerResult != null) {
                    testerResults.add(testerResult);
                }
            }
        }

        Resource template = new ClassPathResource("hr_report.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(template.getInputStream());
        XSSFSheet sheet = null;
        XSSFRow row = null;
        XSSFCell cell = null;

        XSSFCellStyle fontRedStyle = workbook.createCellStyle();
        XSSFFont fontRed = workbook.createFont();
        fontRed.setColor(new XSSFColor(new byte[] { (byte) 255, (byte) 0, (byte) 0 }, null));
        fontRedStyle.setFont(fontRed);

        XSSFCellStyle backgroundFleshTintStyle = workbook.createCellStyle();
        backgroundFleshTintStyle
                .setFillForegroundColor(new XSSFColor(new byte[] { (byte) 255, (byte) 199, (byte) 206 }, null));
        backgroundFleshTintStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundFleshTintStyle.setAlignment(HorizontalAlignment.CENTER);
        backgroundFleshTintStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont fontSize10 = workbook.createFont();
        fontSize10.setFontHeight(10.0);
        backgroundFleshTintStyle.setFont(fontSize10);

        XSSFCellStyle backgroundLightBlueStyle = workbook.createCellStyle();
        backgroundLightBlueStyle
                .setFillForegroundColor(new XSSFColor(new byte[] { (byte) 218, (byte) 238, (byte) 243 }, null));
        backgroundLightBlueStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        backgroundLightBlueStyle.setAlignment(HorizontalAlignment.CENTER);
        backgroundLightBlueStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        backgroundLightBlueStyle.setFont(fontSize10);

        sheet = workbook.getSheetAt(0);
        int rowIndex = 24; // Start Index => 25
        int testerIndex = 1;
        int columnIndex = 0;

        for (TesterResult result : testerResults) {
            // copyRow(workbook, sheet, rowIndex+1, rowIndex+2);
            sheet.copyRows(rowIndex + 1, rowIndex + 2, rowIndex + 2, new CellCopyPolicy());
            row = sheet.getRow(rowIndex);
            cell = row.getCell(columnIndex++);
            cell.setCellValue(testerIndex);

            // SAP ID
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getGroup().getAdmin().getEmail());

            // 지원자 ID
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getApplyId());

            // 지원자 이름
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getName());

            // 지원자 생년월일
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getBirthday());

            // 지원자 성별
            cell = row.getCell(columnIndex++);
            if (result.getTester().getGender()) {
                cell.setCellValue("남");
            } else {
                cell.setCellValue("여");
            }

            // 지원자 휴대폰
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getPhone());

            // 적합/부적합
            cell = row.getCell(columnIndex++);
            if (result.getPersonalityTScore() <= 30 || result.getDev() <= 0.41 || result.getExtremeMark1Count() >= 82
                    || ConvertSten(result.getConsistencyTScore()) >= 10) {
                cell.setCellValue("부적합");
                cell.setCellStyle(backgroundLightBlueStyle);
            } else {
                cell.setCellValue("적합");
            }

            // 시작 시간
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getStartDateTime());

            // 완료 시간
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getCompletedDateTime());

            // 응답신뢰성 Pass/Fail
            cell = row.getCell(columnIndex++);
            if (result.getDev() <= 0.41 || result.getExtremeMark1Count() >= 82
                    || ConvertSten(result.getConsistencyTScore()) >= 10) {
                cell.setCellValue("Fail");
            } else {
                cell.setCellValue("Pass");
            }

            // 응답성실성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getDev());

            // 바람직성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getExtremeMark1Count());

            // 응답일관성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getConsistencyTScore()));

            // 인성검사 종합점수
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getPersonalityTScore())));

            // 지도교사 요구역량 - 성실성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor1T()) * 10) / 10.0);

            // 지도교사 요구역량 - 사회성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor2T()) * 10) / 10.0);

            // 지도교사 요구역량 - 자기개발
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor3T()) * 10) / 10.0);

            // 지도교사 요구역량 - 자기조절
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor4T()) * 10) / 10.0);

            // 지도교사 요구역량 - 성취지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor5T()) * 10) / 10.0);

            // 지도교사 요구역량 - 고객지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(Math.round(ConvertMaxMinTScore(result.getFactor6T()) * 10) / 10.0);

            // 성격척도 - 계획성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor1T()));

            // 성격척도 - 공감성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor2T()));

            // 성격척도 - 규범준수
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor3T()));

            // 성격척도 - 긍정성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor4T()));

            // 성격척도 - 도전성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor5T()));

            // 성격척도 - 사교성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor7T()));

            // 성격척도 - 서비스지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor8T()));

            // 성격척도 - 설득성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor9T()));

            // 성격척도 - 실행성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor11T()));

            // 성격척도 - 적응성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor12T()));

            // 성격척도 - 정서조절
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor13T()));

            // 성격척도 - 주도성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor15T()));

            // 성격척도 - 책임감
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor16T()));

            // 성격척도 - 학습추구
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor17T()));

            // 성격척도 - 협동성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertMaxMinTScore(result.getSubFactor18T()));

            // 잠재위험요인 - 공격성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor19T())));
            if (result.getSubFactor19T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 불안/우울
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor20T())));
            if (result.getSubFactor20T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 의존성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor22T())));
            if (result.getSubFactor22T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 자아취약성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor23T())));
            if (result.getSubFactor23T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 충동성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor24T())));
            if (result.getSubFactor24T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 편집증
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor25T())));
            if (result.getSubFactor25T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 성 위험요인
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", ConvertMaxMinTScore(result.getSubFactor21T())));
            if (result.getSubFactor21T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // --------------------------------------------------------------------------
            // 잠재위험요인 - 공격성(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN19S()));

            // 잠재위험요인 - 불안/우울(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN20S()));

            // 잠재위험요인 - 의존성(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN22S()));

            // 잠재위험요인 - 자아취약성(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN23S()));

            // 잠재위험요인 - 충동성(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN24S()));

            // 잠재위험요인 - 편집증(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN25S()));

            // 잠재위험요인 - 성 위험요인(원점수총점)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.0f", result.getSubFactorN21S()));

            // --------------------------------------------------------------------------

            // 잠재위험요인 - 공격성(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN19S() / 7));

            // 잠재위험요인 - 불안/우울(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN20S() / 7));

            // 잠재위험요인 - 의존성(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN22S() / 7));

            // 잠재위험요인 - 자아취약성(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN23S() / 7));

            // 잠재위험요인 - 충동성(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN24S() / 7));

            // 잠재위험요인 - 편집증(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN25S() / 7));

            // 잠재위험요인 - 성 위험요인(원점수평균)
            cell = row.getCell(columnIndex++);
            cell.setCellValue(String.format("%.1f", result.getSubFactorN21S() / 6));

            rowIndex++;
            testerIndex++;
            columnIndex = 0;
        }

        sheet = workbook.getSheetAt(1);
        rowIndex = 24; // Start Index => 25
        testerIndex = 1;
        columnIndex = 0;

        for (TesterResult result : testerResults) {
            sheet.copyRows(rowIndex + 1, rowIndex + 2, rowIndex + 2, new CellCopyPolicy());

            row = sheet.getRow(rowIndex);
            cell = row.getCell(columnIndex++);
            cell.setCellValue(testerIndex);

            // SAP ID
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getGroup().getAdmin().getEmail());

            // 지원자 ID
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getApplyId());

            // 지원자 이름
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getName());

            // 지원자 생년월일
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getBirthday());

            // 지원자 성별
            cell = row.getCell(columnIndex++);
            if (result.getTester().getGender()) {
                cell.setCellValue("남");
            } else {
                cell.setCellValue("여");
            }

            // 지원자 휴대폰
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getPhone());

            // 적합/부적합
            cell = row.getCell(columnIndex++);
            if (result.getPersonalityTScore() <= 30 || result.getDev() <= 0.41 || result.getExtremeMark1Count() >= 82
                    || ConvertSten(result.getConsistencyTScore()) >= 10) {
                cell.setCellValue("부적합");
                cell.setCellStyle(backgroundLightBlueStyle);
            } else {
                cell.setCellValue("적합");
            }

            // 시작 시간
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getStartDateTime());

            // 종료 시간
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getTester().getCompletedDateTime());

            // 응답신뢰성 Pass/Fail
            cell = row.getCell(columnIndex++);
            if (result.getDev() <= 0.41 || result.getExtremeMark1Count() >= 82
                    || ConvertSten(result.getConsistencyTScore()) >= 10) {
                cell.setCellValue("Fail");
            } else {
                cell.setCellValue("Pass");
            }

            // 응답성실성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getDev());

            // 바람직성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(result.getExtremeMark1Count());

            // 응답일관성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getConsistencyTScore()));

            // 인성검사 종합점수
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getPersonalityTScore()));

            // 지도교사 요구역량 - 성실성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor1T()));

            // 지도교사 요구역량 - 사회성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor2T()));

            // 지도교사 요구역량 - 자기개발
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor3T()));

            // 지도교사 요구역량 - 자기조절
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor4T()));

            // 지도교사 요구역량 - 성취지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor5T()));

            // 지도교사 요구역량 - 고객지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getFactor6T()));

            // 성격척도 - 계획성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor1T()));

            // 성격척도 - 공감성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor2T()));

            // 성격척도 - 규범준수
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor3T()));

            // 성격척도 - 긍정성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor4T()));

            // 성격척도 - 도전성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor5T()));

            // 성격척도 - 사교성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor7T()));

            // 성격척도 - 서비스지향
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor8T()));

            // 성격척도 - 설득성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor9T()));

            // 성격척도 - 실행성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor11T()));

            // 성격척도 - 적응성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor12T()));

            // 성격척도 - 정서조절
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor13T()));

            // 성격척도 - 주도성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor15T()));

            // 성격척도 - 책임감
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor16T()));

            // 성격척도 - 학습추구
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor17T()));

            // 성격척도 - 협동성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor18T()));

            // 잠재위험요인 - 공격성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor19T()));
            if (result.getSubFactor19T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 불안/우울
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor20T()));
            if (result.getSubFactor20T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 의존성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor22T()));
            if (result.getSubFactor22T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 자아취약성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor23T()));
            if (result.getSubFactor23T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 충동성
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor24T()));
            if (result.getSubFactor24T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 편집증
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor25T()));
            if (result.getSubFactor25T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            // 잠재위험요인 - 성 위험요인
            cell = row.getCell(columnIndex++);
            cell.setCellValue(ConvertSten(result.getSubFactor21T()));
            if (result.getSubFactor21T() > 65) {
                cell.setCellStyle(backgroundFleshTintStyle);
            }

            rowIndex++;
            testerIndex++;
            columnIndex = 0;
        }

        String fileName = group.getId() + "_" + group.getName() + "_HR";
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

    private static double ConvertMaxMinTScore(double tScore) {
        if (tScore <= 16.1) {
            return 16.1;
        } else if (tScore >= 83.9) {
            return 83.9;
        }
        return tScore;
    }
}