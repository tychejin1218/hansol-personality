package com.eduhansol.app.controllers;

import javax.servlet.http.HttpServletRequest;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Question;
import com.eduhansol.app.entities.TempMark2;
import com.eduhansol.app.services.QuestionService;
import com.eduhansol.app.services.TempMark2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempMark2Controller {
    private final TempMark2Service _tempMark2Service;
    private final QuestionService _questionService;

    @Autowired
    public TempMark2Controller(TempMark2Service tempMark2Service, QuestionService questionService) {
        _tempMark2Service = tempMark2Service;
        _questionService = questionService;
    }

    @PostMapping("/api/TempMark2")
    public boolean Post(int questionSetId, int questionId, String markValue, HttpServletRequest request,
            @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie,
            @CookieValue(Constants.TESTER_ID) String testerIdCookie) {

        try {
            int testerId = Integer.parseInt(testerIdCookie);
            int normPersonalityId = Integer.parseInt(normPersonalityIdCookie);

            Question question = _questionService.get(questionId);

            TempMark2 tempMark2 = _tempMark2Service.get(testerId, questionSetId, markValue);

            if (tempMark2 != null) {
                // _tempMark2Service.update(testerId, questionSetId, question.getId(), markValue);
                _tempMark2Service.delete(testerId, questionSetId, markValue);
            }

            String reverseMarkValue = "";

            if (markValue.equals("1")) {
                reverseMarkValue = "2";
            } else if (markValue.equals("2")) {
                reverseMarkValue = "1";
            }

            TempMark2 existTempMark2 = _tempMark2Service.getByQuestionId(testerId, questionId, reverseMarkValue);

            if (existTempMark2 != null) {
                _tempMark2Service.deleteByQuestionId(testerId, questionId);
            }

            TempMark2 newTempMark2 = new TempMark2();
            newTempMark2.setTesterId(testerId);
            newTempMark2.setNormPersonalityId(normPersonalityId);
            newTempMark2.setQuestionSetId(questionSetId);
            newTempMark2.setQuestionId(question.getId());
            newTempMark2.setPageNo(question.getPageNo());
            newTempMark2.setMark2(markValue);

            if (_tempMark2Service.post(newTempMark2) != null) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return false;
    }
}