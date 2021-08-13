package com.eduhansol.app.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.eduhansol.app.entities.Factor;
import com.eduhansol.app.entities.FactorGroup;
import com.eduhansol.app.entities.Info;
import com.eduhansol.app.entities.Mark;
import com.eduhansol.app.entities.NormPersonality;
import com.eduhansol.app.entities.Orientation;
import com.eduhansol.app.entities.Question;
import com.eduhansol.app.entities.SubFactor;
import com.eduhansol.app.entities.TempMark1;
import com.eduhansol.app.entities.TempMark2;
import com.eduhansol.app.entities.Tester;
import com.eduhansol.app.services.FactorGroupService;
import com.eduhansol.app.services.FactorService;
import com.eduhansol.app.services.InfoService;
import com.eduhansol.app.services.MarkService;
import com.eduhansol.app.services.NormPersonalityService;
import com.eduhansol.app.services.OrientationService;
import com.eduhansol.app.services.QuestionService;
import com.eduhansol.app.services.SubFactorService;
import com.eduhansol.app.services.TempMark1Service;
import com.eduhansol.app.services.TempMark2Service;
import com.eduhansol.app.services.TesterResultService;
import com.eduhansol.app.services.TesterService;
import com.eduhansol.app.viewmodels.OrientationViewModel;
import com.eduhansol.app.viewmodels.QuestionViewModel;
import com.eduhansol.app.viewmodels.TesterLoginViewModel;
import com.eduhansol.app.configs.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final OrientationService _orientationService;
    private final TesterService _testerService;
    private final TesterResultService _testerResultService;

    private final TempMark1Service _tempMark1Service;
    private final TempMark2Service _tempMark2Service;
    private final MarkService _markService;

    private final QuestionService _questionService;
    private final SubFactorService _subFactorService;
    private final FactorService _factorService;
    private final FactorGroupService _factorGroupService;
    private final NormPersonalityService _normPersonalityService;

    private final InfoService _infoService;

    @Autowired
    public HomeController(OrientationService orientationService, TesterService testerService,
                          TesterResultService testerResultService, QuestionService questionService, TempMark1Service tempMark1Service,
                          TempMark2Service tempMark2Service, MarkService markService, SubFactorService subFactorService,
                          FactorService factorService, FactorGroupService factorGroupService,
                          NormPersonalityService normPersonalityService, InfoService infoService) {
        _orientationService = orientationService;
        _testerService = testerService;
        _testerResultService = testerResultService;

        _questionService = questionService;
        _tempMark1Service = tempMark1Service;
        _tempMark2Service = tempMark2Service;
        _markService = markService;

        _subFactorService = subFactorService;
        _factorService = factorService;
        _factorGroupService = factorGroupService;
        _normPersonalityService = normPersonalityService;
        _infoService = infoService;
    }

    /**
     * 지원자 로그인 페이지
     *
     * @return
     */
    @GetMapping("/")
    public String login(@ModelAttribute TesterLoginViewModel tester, Model model, HttpServletResponse response) {
        model.addAttribute("tester", tester);

        Info info = _infoService.findById(1);
        if (info != null)
            model.addAttribute("info", info);
        // # Cookie clear
        Cookie testerIdCookie = new Cookie(Constants.TESTER_ID, null);
        Cookie testerNameCookie = new Cookie(Constants.TESTER_NAME, null);
        Cookie normPersonalityCookie = new Cookie(Constants.NORM_PERSONALITY_ID, null);

        testerIdCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
        testerIdCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.

        testerNameCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
        testerNameCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.

        normPersonalityCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
        normPersonalityCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.

        response.addCookie(testerIdCookie);
        response.addCookie(testerNameCookie);
        response.addCookie(normPersonalityCookie);

        return "home/index";
    }

    /**
     * 지원자 로그인 전송
     *
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/")
    public String login(@ModelAttribute @Valid TesterLoginViewModel tester, BindingResult bindingResult, Model model,
                        HttpServletResponse response) throws UnsupportedEncodingException {
        if (!bindingResult.hasErrors()) {
            Tester loginTester = _testerService.get(tester.getName(), tester.getAuthKey());

            if (loginTester != null) {
                if (loginTester.getTestState() == Constants.TEST_STATE_TESTED) {
                    String name = URLEncoder.encode(loginTester.getName(), "UTF-8");
                    return "redirect:/completed?name=" + name; // 검사를 완료한 경우 완료 페이지로 이동
                }

                NormPersonality normPersonality = loginTester.getGroup().getNormPersonality();

                Cookie testerIdCookie = new Cookie(Constants.TESTER_ID, Integer.toString(loginTester.getId()));
                Cookie testerNameCookie = new Cookie(Constants.TESTER_NAME, URLEncoder.encode(loginTester.getName(), "utf-8") );
                Cookie normPersonalityCookie = new Cookie(Constants.NORM_PERSONALITY_ID,
                        Integer.toString(normPersonality.getId()));
                response.addCookie(testerIdCookie);
                response.addCookie(testerNameCookie);
                response.addCookie(normPersonalityCookie);

                List<TempMark1> tempMark1s = _tempMark1Service.getList(loginTester.getId()).stream()
                        .sorted(Comparator.comparing(TempMark1::getQuestionId).reversed()).collect(Collectors.toList());
                TempMark1 tempMark1 = null;
                if (tempMark1s.size() > 0) {
                    tempMark1 = tempMark1s.get(0);
                }

                if (loginTester.getTestState() == Constants.TEST_STATE_TESTING) {
                    if (tempMark1 == null) {
                        return "redirect:/ot8"; // 검사를 일부만 한 경우 마지막 오리엔테이션 페이지로 이동
                    } else {
                        return "redirect:/test?pageNo=" + tempMark1.getPageNo();
                    }
                }
                return "redirect:/ot1";
            }
            model.addAttribute("error", "이름 혹은 인증키가 올바르지 않습니다.");
        }
        model.addAttribute("tester", tester);

        Info info = _infoService.findById(1);
        if (info != null)
            model.addAttribute("info", info);
        return "home/index";
    }

    /**
     * 오리엔테이션 페이지
     *
     * @param pageNo
     * @return
     */
    @GetMapping("/orientation")
    public String orientation(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, Model model,
                              HttpServletRequest request, @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie) {
        Orientation orientation = _orientationService.get(Integer.parseInt(normPersonalityIdCookie), pageNo);
        if (orientation == null)
            return "redirect:/error";

        OrientationViewModel vm = new OrientationViewModel();
        vm.setPage(pageNo);
        vm.setNormPersonalityId(Integer.parseInt(normPersonalityIdCookie));
        vm.setOrientation(orientation);

        model.addAttribute("vm", vm);
        return "home/orientation";
    }

    @GetMapping("/ot1")
    public String ot1() {
        return "home/ot1";
    }

    @GetMapping("/ot2")
    public String ot2() {
        return "home/ot2";
    }

    @GetMapping("/ot3")
    public String ot3() {
        return "home/ot3";
    }

    @GetMapping("/ot4")
    public String ot4() {
        return "home/ot4";
    }

    @GetMapping("/ot5")
    public String ot5() {
        return "home/ot5";
    }

    @GetMapping("/ot6")
    public String ot6() {
        return "home/ot6";
    }

    @GetMapping("/ot7")
    public String ot7() {
        return "home/ot7";
    }

    @GetMapping("/ot8")
    public String ot8() {
        return "home/ot8";
    }

    // @PostMapping("/test")
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String test(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, Model model,
                       HttpServletRequest request, @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie,
                       @CookieValue(Constants.TESTER_ID) String testerIdCookie) {

        int normPersonalityId = Integer.parseInt(normPersonalityIdCookie);
        int testerId = Integer.parseInt(testerIdCookie);

        Tester tester = _testerService.get(testerId);
        if (tester == null) {
            return "redirect:/error";
        }

        if (tester.getTestState() == Constants.TEST_STATE_NOT_TEST) {
            tester.setTestState(Constants.TEST_STATE_TESTING);
            tester.setStartDateTime(LocalDateTime.now());
            _testerService.update(tester);
        }

        if (tester.getTestState() == Constants.TEST_STATE_TESTED) {
            return "redirect:/completed?name=" + tester.getName();
        }

        TempMark1 lastTempMark1 = _tempMark1Service.findFirstByTesterIdOrderByQuestionIdDesc(testerId);
        if (lastTempMark1 != null && pageNo < lastTempMark1.getPageNo()) {
            return "redirect:/test?pageNo=" + lastTempMark1.getPageNo();
        }
        // Optional: get last TempMark pageNo
        List<Question> questions = _questionService.getList(normPersonalityId, pageNo);
        List<TempMark1> tempMark1s = new ArrayList<>();
        List<TempMark2> tempMark2s = new ArrayList<>();

        for (Question question : questions) {
            TempMark1 tempMark1 = _tempMark1Service.get(testerId, question.getId());
            if (tempMark1 == null) {
                tempMark1 = new TempMark1();
                // tempMark1.setTesterId(tester.getId());
                // tempMark1.setQuestionId(question.getId());
                tempMark1.setMark1("");
                // tempMark1.setPageNo(question.getPageNo());
                // tempMark1.setNormPersonalityId(normPersonality.getId());
            }
            tempMark1s.add(tempMark1);

            TempMark2 tempMark2 = _tempMark2Service.getByQuestionId(tester.getId(), question.getId());

            if (tempMark2 == null) {
                tempMark2 = new TempMark2();
                tempMark2.setMark2("0");
            }
            tempMark2s.add(tempMark2);
        }

        QuestionViewModel vm = new QuestionViewModel();
        vm.setTime(tester.getTime());
        vm.setQuestions(questions);
        vm.setTempMark1s(tempMark1s);
        vm.setTempMark2s(tempMark2s);
        if (pageNo > 1) {
            vm.setPrevPageNo(pageNo - 1);
        } else {
            vm.setPrevPageNo(1);
        }
        vm.setPageNo(pageNo);
        vm.setNextPageNo(pageNo + 1);
        vm.setIsLast(questions.get(0).getIsLast());

        model.addAttribute("vm", vm);
        return "home/test";
    }

    @GetMapping("/completed")
    public String completed(String name, Model model) {
        model.addAttribute("name", name);
        return "home/completed";
    }

    @GetMapping("/end")
    public String end(Model model, HttpServletRequest request,
            @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie,
            @CookieValue(Constants.TESTER_ID) String testerIdCookie){

        try {
            int testerId = Integer.parseInt(testerIdCookie);
            int normPersonalityId = Integer.parseInt(normPersonalityIdCookie);

            Tester tester = _testerService.get(testerId);
            if (tester == null)
                throw new Exception();

            _testerResultService.deleteByTesterId(testerId);

            if (tester.getTestState() == Constants.TEST_STATE_TESTING) {
                tester.setTestState(Constants.TEST_STATE_TESTED);
                tester.setCompletedDateTime(LocalDateTime.now());
                _testerService.update(tester);
            }

            Mark oldMark = _markService.get(testerId);

            if (oldMark != null) {
                _markService.delete(testerId);
            }

            List<Question> questions = _questionService.getList(normPersonalityId);
            List<TempMark1> tempMark1s = _tempMark1Service.getList(tester.getId());
            List<TempMark2> tempMark2s = _tempMark2Service.getList(tester.getId());
            StringBuilder mark1 = new StringBuilder();
            StringBuilder mark2 = new StringBuilder();
            StringBuilder convertMark2 = new StringBuilder();

            for (Question question : questions) {
                TempMark1 tempMark1 = tempMark1s.stream().filter(i -> i.getQuestionId() == question.getQuestionId())
                        .findFirst().orElse(null);
                if (tempMark1 != null) {
                    mark1.append(tempMark1.getMark1());
                } else {
                    mark1.append("0");
                }

                TempMark2 tempMark2 = tempMark2s.stream().filter(i -> i.getQuestionId() == question.getQuestionId())
                        .findFirst().orElse(null);
                if (tempMark2 != null) {
                    mark2.append(tempMark2.getMark2());
                    convertMark2.append(ReplaceMark2(tempMark2.getMark2()));
                } else {
                    mark2.append("0");
                    convertMark2.append(ReplaceMark2("0"));
                }
            }

            Mark mark = new Mark();
            mark.setTesterId(testerId);
            mark.setMark1(mark1.toString());
            mark.setMark2(mark2.toString());
            mark.setConvertMark2(convertMark2.toString());
            _markService.post(mark);

            NormPersonality normPersonality = _normPersonalityService.get(normPersonalityId);
            List<SubFactor> subFactors = _subFactorService.getList(normPersonalityId);
            List<Factor> factors = _factorService.getList(normPersonalityId);
            List<FactorGroup> factorGroups = _factorGroupService.getList(normPersonalityId);

            com.acghr.util.Mark acgMark = new com.acghr.util.Mark();
            acgMark.setTesterId(testerId);
            acgMark.setMark1(mark1.toString());
            acgMark.setMark2(mark2.toString());
            acgMark.setConvertMark2(convertMark2.toString());

            com.acghr.util.NormPersonality acgNormPersonality = new com.acghr.util.NormPersonality();
            acgNormPersonality.setId(normPersonalityId);
            acgNormPersonality.setName(normPersonality.getName());
            acgNormPersonality.setMean(normPersonality.getMean());
            acgNormPersonality.setDev(normPersonality.getDev());
            acgNormPersonality.setConsistencyMean(normPersonality.getConsistencyMean());
            acgNormPersonality.setConsistencyDev(normPersonality.getConsistencyDev());
            acgNormPersonality.setTimeout(normPersonality.getTimeout());
            acgNormPersonality.setFailConsistencySten(normPersonality.getFailConsistencySten());
            acgNormPersonality.setFailDev(normPersonality.getFailDev());
            acgNormPersonality.setFailMark1Count(normPersonality.getFailMark1Count());
            acgNormPersonality.setFailExtremeMark1Count(normPersonality.getFailExtremeMark1Count());
            acgNormPersonality.setDelete(normPersonality.isDelete());

            List<com.acghr.util.Question> acgQuestions = new ArrayList<>();
            for (Question question : questions) {
                com.acghr.util.Question singleAcgQuestion = new com.acghr.util.Question();
                singleAcgQuestion.setId(question.getId());
                singleAcgQuestion.setQuestionId(question.getQuestionId());
                singleAcgQuestion.setQuestionSetId(question.getQuestionSetId());
                singleAcgQuestion.setQuestionChar(question.getQuestionChar());
                singleAcgQuestion.setPageNo(question.getPageNo());

                com.acghr.util.SubFactor acgSubFactor = new com.acghr.util.SubFactor();
                acgSubFactor.setId(question.getSubFactor().getId());
                acgSubFactor.setSubFactorId(question.getSubFactor().getSubFactorId());
                acgSubFactor.setName(question.getSubFactor().getName());
                acgSubFactor.setDescription(question.getSubFactor().getDescription());
                acgSubFactor.setNMean(question.getSubFactor().getNMean());
                acgSubFactor.setNDev(question.getSubFactor().getNDev());
                acgSubFactor.setIMean(question.getSubFactor().getIMean());
                acgSubFactor.setIDev(question.getSubFactor().getIDev());
                acgSubFactor.setMean(question.getSubFactor().getMean());
                acgSubFactor.setDev(question.getSubFactor().getDev());
                acgSubFactor.setNWeight(question.getSubFactor().getNWeight());
                acgSubFactor.setIWeight(question.getSubFactor().getIWeight());
                acgSubFactor.setIsDummy(question.getSubFactor().getIsDummy());
                acgSubFactor.setIsReverse(question.getSubFactor().getIsReverse());

                singleAcgQuestion.setSubFactor(acgSubFactor);
                singleAcgQuestion.setText(question.getText());
                singleAcgQuestion.setIsReverse(question.getIsReverse());
                singleAcgQuestion.setIsLast(question.getIsLast());
                singleAcgQuestion.setIsDummy(question.getIsDummy());
                acgQuestions.add(singleAcgQuestion);
            }

            List<com.acghr.util.SubFactor> acgSubFactors = new ArrayList<>();
            for (SubFactor subfactor : subFactors) {
                com.acghr.util.SubFactor singleAcgSubFactor = new com.acghr.util.SubFactor();
                singleAcgSubFactor.setId(subfactor.getId());
                singleAcgSubFactor.setSubFactorId(subfactor.getSubFactorId());
                singleAcgSubFactor.setName(subfactor.getName());
                singleAcgSubFactor.setDescription(subfactor.getDescription());
                singleAcgSubFactor.setNMean(subfactor.getNMean());
                singleAcgSubFactor.setNDev(subfactor.getNDev());
                singleAcgSubFactor.setIMean(subfactor.getIMean());
                singleAcgSubFactor.setIDev(subfactor.getIDev());
                singleAcgSubFactor.setMean(subfactor.getMean());
                singleAcgSubFactor.setDev(subfactor.getDev());
                singleAcgSubFactor.setNWeight(subfactor.getNWeight());
                singleAcgSubFactor.setIWeight(subfactor.getIWeight());
                singleAcgSubFactor.setIsDummy(subfactor.getIsDummy());
                singleAcgSubFactor.setIsReverse(subfactor.getIsReverse());
                acgSubFactors.add(singleAcgSubFactor);
            }

            List<com.acghr.util.Factor> acgFactors = new ArrayList<>();
            for (Factor factor : factors) {
                com.acghr.util.Factor singleAcgFactor = new com.acghr.util.Factor();
                singleAcgFactor.setId(factor.getId());
                singleAcgFactor.setfactorId(factor.getFactorId());
                singleAcgFactor.setName(factor.getName());
                singleAcgFactor.setDescription(factor.getDescription());
                singleAcgFactor.setMean(factor.getMean());
                singleAcgFactor.setDev(factor.getDev());
                singleAcgFactor.setWeight(factor.getWeight());
                acgFactors.add(singleAcgFactor);
            }

            List<com.acghr.util.FactorGroup> acgFactorGroups = new ArrayList<>();
            for (FactorGroup factorGroup : factorGroups) {
                com.acghr.util.FactorGroup singleAcgFactorGroup = new com.acghr.util.FactorGroup();
                singleAcgFactorGroup.setNormPersonalityId(factorGroup.getNormPersonalityId());

                com.acghr.util.Factor singleAcgFactor = new com.acghr.util.Factor();
                singleAcgFactor.setId(factorGroup.getFactor().getId());
                singleAcgFactor.setfactorId(factorGroup.getFactor().getFactorId());
                singleAcgFactor.setName(factorGroup.getFactor().getName());
                singleAcgFactor.setDescription(factorGroup.getFactor().getDescription());
                singleAcgFactor.setMean(factorGroup.getFactor().getMean());
                singleAcgFactor.setDev(factorGroup.getFactor().getDev());
                singleAcgFactor.setWeight(factorGroup.getFactor().getWeight());
                singleAcgFactorGroup.setFactor(singleAcgFactor);

                com.acghr.util.SubFactor singleAcgSubFactor = new com.acghr.util.SubFactor();
                singleAcgSubFactor.setId(factorGroup.getSubFactor().getId());
                singleAcgSubFactor.setSubFactorId(factorGroup.getSubFactor().getSubFactorId());
                singleAcgSubFactor.setName(factorGroup.getSubFactor().getName());
                singleAcgSubFactor.setDescription(factorGroup.getSubFactor().getDescription());
                singleAcgSubFactor.setNMean(factorGroup.getSubFactor().getNMean());
                singleAcgSubFactor.setNDev(factorGroup.getSubFactor().getNDev());
                singleAcgSubFactor.setIMean(factorGroup.getSubFactor().getIMean());
                singleAcgSubFactor.setIDev(factorGroup.getSubFactor().getIDev());
                singleAcgSubFactor.setMean(factorGroup.getSubFactor().getMean());
                singleAcgSubFactor.setDev(factorGroup.getSubFactor().getDev());
                singleAcgSubFactor.setNWeight(factorGroup.getSubFactor().getNWeight());
                singleAcgSubFactor.setIWeight(factorGroup.getSubFactor().getIWeight());
                singleAcgSubFactor.setIsDummy(factorGroup.getSubFactor().getIsDummy());
                singleAcgSubFactor.setIsReverse(factorGroup.getSubFactor().getIsReverse());
                singleAcgFactorGroup.setSubFactor(singleAcgSubFactor);
                acgFactorGroups.add(singleAcgFactorGroup);
            }

            com.acghr.util.PersonalityTest personalityTest = new com.acghr.util.PersonalityTest();
            com.acghr.util.TesterResult acgTesterResult = personalityTest.Scoring(acgMark, acgNormPersonality, acgQuestions, acgSubFactors, acgFactors, acgFactorGroups);
            com.eduhansol.app.entities.TesterResult testerResult = new com.eduhansol.app.entities.TesterResult();

            testerResult.setNormPersonality(normPersonality);
            testerResult.setPersonalityScore(acgTesterResult.getPersonalityScore());
            testerResult.setPersonalityTScore(acgTesterResult.getPersonalityTScore());
            testerResult.setDev(acgTesterResult.getDev());
            testerResult.setMark1Count(acgTesterResult.getMark1Count());
            testerResult.setExtremeMark1Count(acgTesterResult.getExtremeMark1Count());
            testerResult.setConsistencyScore(acgTesterResult.getConsistencyScore());
            testerResult.setConsistencyTScore(acgTesterResult.getConsistencyTScore());

            testerResult.setSubFactorN1S(acgTesterResult.getSubFactorN1S());
            testerResult.setSubFactorN2S(acgTesterResult.getSubFactorN2S());
            testerResult.setSubFactorN3S(acgTesterResult.getSubFactorN3S());
            testerResult.setSubFactorN4S(acgTesterResult.getSubFactorN4S());
            testerResult.setSubFactorN5S(acgTesterResult.getSubFactorN5S());
            testerResult.setSubFactorN6S(acgTesterResult.getSubFactorN6S());
            testerResult.setSubFactorN7S(acgTesterResult.getSubFactorN7S());
            testerResult.setSubFactorN8S(acgTesterResult.getSubFactorN8S());
            testerResult.setSubFactorN9S(acgTesterResult.getSubFactorN9S());
            testerResult.setSubFactorN10S(acgTesterResult.getSubFactorN10S());
            testerResult.setSubFactorN11S(acgTesterResult.getSubFactorN11S());
            testerResult.setSubFactorN12S(acgTesterResult.getSubFactorN12S());
            testerResult.setSubFactorN13S(acgTesterResult.getSubFactorN13S());
            testerResult.setSubFactorN14S(acgTesterResult.getSubFactorN14S());
            testerResult.setSubFactorN15S(acgTesterResult.getSubFactorN15S());
            testerResult.setSubFactorN16S(acgTesterResult.getSubFactorN16S());
            testerResult.setSubFactorN17S(acgTesterResult.getSubFactorN17S());
            testerResult.setSubFactorN18S(acgTesterResult.getSubFactorN18S());
            testerResult.setSubFactorN19S(acgTesterResult.getSubFactorN19S());
            testerResult.setSubFactorN20S(acgTesterResult.getSubFactorN20S());
            testerResult.setSubFactorN21S(acgTesterResult.getSubFactorN21S());
            testerResult.setSubFactorN22S(acgTesterResult.getSubFactorN22S());
            testerResult.setSubFactorN23S(acgTesterResult.getSubFactorN23S());
            testerResult.setSubFactorN24S(acgTesterResult.getSubFactorN24S());
            testerResult.setSubFactorN25S(acgTesterResult.getSubFactorN25S());
            testerResult.setSubFactorN26S(acgTesterResult.getSubFactorN26S());
            testerResult.setSubFactorN27S(acgTesterResult.getSubFactorN27S());
            testerResult.setSubFactorN28S(acgTesterResult.getSubFactorN28S());

            testerResult.setSubFactorI1S(acgTesterResult.getSubFactorI1S());
            testerResult.setSubFactorI2S(acgTesterResult.getSubFactorI2S());
            testerResult.setSubFactorI3S(acgTesterResult.getSubFactorI3S());
            testerResult.setSubFactorI4S(acgTesterResult.getSubFactorI4S());
            testerResult.setSubFactorI5S(acgTesterResult.getSubFactorI5S());
            testerResult.setSubFactorI6S(acgTesterResult.getSubFactorI6S());
            testerResult.setSubFactorI7S(acgTesterResult.getSubFactorI7S());
            testerResult.setSubFactorI8S(acgTesterResult.getSubFactorI8S());
            testerResult.setSubFactorI9S(acgTesterResult.getSubFactorI9S());
            testerResult.setSubFactorI10S(acgTesterResult.getSubFactorI10S());
            testerResult.setSubFactorI11S(acgTesterResult.getSubFactorI11S());
            testerResult.setSubFactorI12S(acgTesterResult.getSubFactorI12S());
            testerResult.setSubFactorI13S(acgTesterResult.getSubFactorI13S());
            testerResult.setSubFactorI14S(acgTesterResult.getSubFactorI14S());
            testerResult.setSubFactorI15S(acgTesterResult.getSubFactorI15S());
            testerResult.setSubFactorI16S(acgTesterResult.getSubFactorI16S());
            testerResult.setSubFactorI17S(acgTesterResult.getSubFactorI17S());
            testerResult.setSubFactorI18S(acgTesterResult.getSubFactorI18S());
            testerResult.setSubFactorI19S(acgTesterResult.getSubFactorI19S());
            testerResult.setSubFactorI20S(acgTesterResult.getSubFactorI20S());
            testerResult.setSubFactorI21S(acgTesterResult.getSubFactorI21S());
            testerResult.setSubFactorI22S(acgTesterResult.getSubFactorI22S());
            testerResult.setSubFactorI23S(acgTesterResult.getSubFactorI23S());
            testerResult.setSubFactorI24S(acgTesterResult.getSubFactorI24S());
            testerResult.setSubFactorI25S(acgTesterResult.getSubFactorI25S());
            testerResult.setSubFactorI26S(acgTesterResult.getSubFactorI26S());
            testerResult.setSubFactorI27S(acgTesterResult.getSubFactorI27S());
            testerResult.setSubFactorI28S(acgTesterResult.getSubFactorI28S());

            testerResult.setSubFactorN1T(acgTesterResult.getSubFactorN1T());
            testerResult.setSubFactorN2T(acgTesterResult.getSubFactorN2T());
            testerResult.setSubFactorN3T(acgTesterResult.getSubFactorN3T());
            testerResult.setSubFactorN4T(acgTesterResult.getSubFactorN4T());
            testerResult.setSubFactorN5T(acgTesterResult.getSubFactorN5T());
            testerResult.setSubFactorN6T(acgTesterResult.getSubFactorN6T());
            testerResult.setSubFactorN7T(acgTesterResult.getSubFactorN7T());
            testerResult.setSubFactorN8T(acgTesterResult.getSubFactorN8T());
            testerResult.setSubFactorN9T(acgTesterResult.getSubFactorN9T());
            testerResult.setSubFactorN10T(acgTesterResult.getSubFactorN10T());
            testerResult.setSubFactorN11T(acgTesterResult.getSubFactorN11T());
            testerResult.setSubFactorN12T(acgTesterResult.getSubFactorN12T());
            testerResult.setSubFactorN13T(acgTesterResult.getSubFactorN13T());
            testerResult.setSubFactorN14T(acgTesterResult.getSubFactorN14T());
            testerResult.setSubFactorN15T(acgTesterResult.getSubFactorN15T());
            testerResult.setSubFactorN16T(acgTesterResult.getSubFactorN16T());
            testerResult.setSubFactorN17T(acgTesterResult.getSubFactorN17T());
            testerResult.setSubFactorN18T(acgTesterResult.getSubFactorN18T());
            testerResult.setSubFactorN19T(acgTesterResult.getSubFactorN19T());
            testerResult.setSubFactorN20T(acgTesterResult.getSubFactorN20T());
            testerResult.setSubFactorN21T(acgTesterResult.getSubFactorN21T());
            testerResult.setSubFactorN22T(acgTesterResult.getSubFactorN22T());
            testerResult.setSubFactorN23T(acgTesterResult.getSubFactorN23T());
            testerResult.setSubFactorN24T(acgTesterResult.getSubFactorN24T());
            testerResult.setSubFactorN25T(acgTesterResult.getSubFactorN25T());
            testerResult.setSubFactorN26T(acgTesterResult.getSubFactorN26T());
            testerResult.setSubFactorN27T(acgTesterResult.getSubFactorN27T());
            testerResult.setSubFactorN28T(acgTesterResult.getSubFactorN28T());

            testerResult.setSubFactorI1T(acgTesterResult.getSubFactorI1T());
            testerResult.setSubFactorI2T(acgTesterResult.getSubFactorI2T());
            testerResult.setSubFactorI3T(acgTesterResult.getSubFactorI3T());
            testerResult.setSubFactorI4T(acgTesterResult.getSubFactorI4T());
            testerResult.setSubFactorI5T(acgTesterResult.getSubFactorI5T());
            testerResult.setSubFactorI6T(acgTesterResult.getSubFactorI6T());
            testerResult.setSubFactorI7T(acgTesterResult.getSubFactorI7T());
            testerResult.setSubFactorI8T(acgTesterResult.getSubFactorI8T());
            testerResult.setSubFactorI9T(acgTesterResult.getSubFactorI9T());
            testerResult.setSubFactorI10T(acgTesterResult.getSubFactorI10T());
            testerResult.setSubFactorI11T(acgTesterResult.getSubFactorI11T());
            testerResult.setSubFactorI12T(acgTesterResult.getSubFactorI12T());
            testerResult.setSubFactorI13T(acgTesterResult.getSubFactorI13T());
            testerResult.setSubFactorI14T(acgTesterResult.getSubFactorI14T());
            testerResult.setSubFactorI15T(acgTesterResult.getSubFactorI15T());
            testerResult.setSubFactorI16T(acgTesterResult.getSubFactorI16T());
            testerResult.setSubFactorI17T(acgTesterResult.getSubFactorI17T());
            testerResult.setSubFactorI18T(acgTesterResult.getSubFactorI18T());
            testerResult.setSubFactorI19T(acgTesterResult.getSubFactorI19T());
            testerResult.setSubFactorI20T(acgTesterResult.getSubFactorI20T());
            testerResult.setSubFactorI21T(acgTesterResult.getSubFactorI21T());
            testerResult.setSubFactorI22T(acgTesterResult.getSubFactorI22T());
            testerResult.setSubFactorI23T(acgTesterResult.getSubFactorI23T());
            testerResult.setSubFactorI24T(acgTesterResult.getSubFactorI24T());
            testerResult.setSubFactorI25T(acgTesterResult.getSubFactorI25T());
            testerResult.setSubFactorI26T(acgTesterResult.getSubFactorI26T());
            testerResult.setSubFactorI27T(acgTesterResult.getSubFactorI27T());
            testerResult.setSubFactorI28T(acgTesterResult.getSubFactorI28T());

            testerResult.setSubFactor1S(acgTesterResult.getSubFactor1S());
            testerResult.setSubFactor2S(acgTesterResult.getSubFactor2S());
            testerResult.setSubFactor3S(acgTesterResult.getSubFactor3S());
            testerResult.setSubFactor4S(acgTesterResult.getSubFactor4S());
            testerResult.setSubFactor5S(acgTesterResult.getSubFactor5S());
            testerResult.setSubFactor6S(acgTesterResult.getSubFactor6S());
            testerResult.setSubFactor7S(acgTesterResult.getSubFactor7S());
            testerResult.setSubFactor8S(acgTesterResult.getSubFactor8S());
            testerResult.setSubFactor9S(acgTesterResult.getSubFactor9S());
            testerResult.setSubFactor10S(acgTesterResult.getSubFactor10S());
            testerResult.setSubFactor11S(acgTesterResult.getSubFactor11S());
            testerResult.setSubFactor12S(acgTesterResult.getSubFactor12S());
            testerResult.setSubFactor13S(acgTesterResult.getSubFactor13S());
            testerResult.setSubFactor14S(acgTesterResult.getSubFactor14S());
            testerResult.setSubFactor15S(acgTesterResult.getSubFactor15S());
            testerResult.setSubFactor16S(acgTesterResult.getSubFactor16S());
            testerResult.setSubFactor17S(acgTesterResult.getSubFactor17S());
            testerResult.setSubFactor18S(acgTesterResult.getSubFactor18S());
            testerResult.setSubFactor19S(acgTesterResult.getSubFactor19S());
            testerResult.setSubFactor20S(acgTesterResult.getSubFactor20S());
            testerResult.setSubFactor21S(acgTesterResult.getSubFactor21S());
            testerResult.setSubFactor22S(acgTesterResult.getSubFactor22S());
            testerResult.setSubFactor23S(acgTesterResult.getSubFactor23S());
            testerResult.setSubFactor24S(acgTesterResult.getSubFactor24S());
            testerResult.setSubFactor25S(acgTesterResult.getSubFactor25S());
            testerResult.setSubFactor26S(acgTesterResult.getSubFactor26S());
            testerResult.setSubFactor27S(acgTesterResult.getSubFactor27S());
            testerResult.setSubFactor28S(acgTesterResult.getSubFactor28S());

            testerResult.setSubFactor1T(acgTesterResult.getSubFactor1T());
            testerResult.setSubFactor2T(acgTesterResult.getSubFactor2T());
            testerResult.setSubFactor3T(acgTesterResult.getSubFactor3T());
            testerResult.setSubFactor4T(acgTesterResult.getSubFactor4T());
            testerResult.setSubFactor5T(acgTesterResult.getSubFactor5T());
            testerResult.setSubFactor6T(acgTesterResult.getSubFactor6T());
            testerResult.setSubFactor7T(acgTesterResult.getSubFactor7T());
            testerResult.setSubFactor8T(acgTesterResult.getSubFactor8T());
            testerResult.setSubFactor9T(acgTesterResult.getSubFactor9T());
            testerResult.setSubFactor10T(acgTesterResult.getSubFactor10T());
            testerResult.setSubFactor11T(acgTesterResult.getSubFactor11T());
            testerResult.setSubFactor12T(acgTesterResult.getSubFactor12T());
            testerResult.setSubFactor13T(acgTesterResult.getSubFactor13T());
            testerResult.setSubFactor14T(acgTesterResult.getSubFactor14T());
            testerResult.setSubFactor15T(acgTesterResult.getSubFactor15T());
            testerResult.setSubFactor16T(acgTesterResult.getSubFactor16T());
            testerResult.setSubFactor17T(acgTesterResult.getSubFactor17T());
            testerResult.setSubFactor18T(acgTesterResult.getSubFactor18T());
            testerResult.setSubFactor19T(acgTesterResult.getSubFactor19T());
            testerResult.setSubFactor20T(acgTesterResult.getSubFactor20T());
            testerResult.setSubFactor21T(acgTesterResult.getSubFactor21T());
            testerResult.setSubFactor22T(acgTesterResult.getSubFactor22T());
            testerResult.setSubFactor23T(acgTesterResult.getSubFactor23T());
            testerResult.setSubFactor24T(acgTesterResult.getSubFactor24T());
            testerResult.setSubFactor25T(acgTesterResult.getSubFactor25T());
            testerResult.setSubFactor26T(acgTesterResult.getSubFactor26T());
            testerResult.setSubFactor27T(acgTesterResult.getSubFactor27T());
            testerResult.setSubFactor28T(acgTesterResult.getSubFactor28T());

            testerResult.setFactor1S(acgTesterResult.getFactor1S());
            testerResult.setFactor2S(acgTesterResult.getFactor2S());
            testerResult.setFactor3S(acgTesterResult.getFactor3S());
            testerResult.setFactor4S(acgTesterResult.getFactor4S());
            testerResult.setFactor5S(acgTesterResult.getFactor5S());
            testerResult.setFactor6S(acgTesterResult.getFactor6S());

            testerResult.setFactor1T(acgTesterResult.getFactor1T());
            testerResult.setFactor2T(acgTesterResult.getFactor2T());
            testerResult.setFactor3T(acgTesterResult.getFactor3T());
            testerResult.setFactor4T(acgTesterResult.getFactor4T());
            testerResult.setFactor5T(acgTesterResult.getFactor5T());
            testerResult.setFactor6T(acgTesterResult.getFactor6T());

            if (testerResult.getDev() <= 0.41 || testerResult.getExtremeMark1Count() >= 82 ||
                    ConvertSten(testerResult.getConsistencyTScore()) >= 10 || testerResult.getPersonalityTScore() <= 30) {
                tester.setTestResult(false);
            } else {
                tester.setTestResult(true);
            }
            testerResult.setTester(tester);
            _testerResultService.save(testerResult);

        } catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }

        return "redirect:/";
    }

    public static String ReplaceMark2(String mark) {
        if (mark.equals("1")) {
            return "0";
        } else if (mark.equals("2")) {
            return "2";
        } else {
            return "1";
        }
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
}
