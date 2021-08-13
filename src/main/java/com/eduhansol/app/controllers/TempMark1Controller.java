package com.eduhansol.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.TempMark1;
import com.eduhansol.app.services.TempMark1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempMark1Controller {
    private final TempMark1Service _tempMark1Service;

    @Autowired
    public TempMark1Controller(TempMark1Service tempMark1Service) {
        _tempMark1Service = tempMark1Service;
    }

    @PostMapping("/api/TempMark1")
    public boolean Post(@RequestBody TempMark1 model, HttpServletRequest request,
            @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie,
            @CookieValue(Constants.TESTER_ID) String testerIdCookie) {

        int testerId = Integer.parseInt(testerIdCookie);

        TempMark1 tempMark1 = _tempMark1Service.get(testerId, model.getQuestionId());
        TempMark1 result = null;

        if (tempMark1 != null) {
            tempMark1.setMark1(model.getMark1());
            
            result = _tempMark1Service.update(tempMark1);
        } else {
            model.setTesterId(testerId);
            model.setNormPersonalityId(Integer.parseInt(normPersonalityIdCookie));
            result = _tempMark1Service.post(model);
        }
        return result != null;
    }

    @PostMapping("/api/TempMark1s")
    public boolean Post(@RequestBody List<TempMark1> models, HttpServletRequest request,
            @CookieValue(Constants.NORM_PERSONALITY_ID) String normPersonalityIdCookie,
            @CookieValue(Constants.TESTER_ID) String testerIdCookie) {
        int testerId = Integer.parseInt(testerIdCookie);
        int count = 0;

        for (TempMark1 model : models) {
            TempMark1 tempMark1 = _tempMark1Service.get(testerId, model.getQuestionId());
            TempMark1 result = null;
            if (tempMark1 == null) {
                model.setTesterId(testerId);
                model.setNormPersonalityId(Integer.parseInt(normPersonalityIdCookie));
                result = _tempMark1Service.post(model);
            } else {
                tempMark1.setMark1(model.getMark1());
                result = _tempMark1Service.update(model);
            }

            if (result != null)
                count++;
        }

        if (models.size() == count) {
            return true;
        }
        return false;
    }
}