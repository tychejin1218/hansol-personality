package com.eduhansol.app.controllers.Api;

import com.eduhansol.app.services.TesterService;
import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Tester;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTimeController {
    private final TesterService _testerService;

    public ApiTimeController(TesterService testerService) {
        _testerService = testerService;
    }

    @PostMapping("/api/updateTime")    
    public void updateTime(int time, @CookieValue(Constants.TESTER_ID) String testerIdCookie){
        int testerId = Integer.parseInt(testerIdCookie);
        Tester tester = _testerService.get(testerId);
        if(tester != null){
            tester.setTime(time);
            _testerService.update(tester);
        }
    }
}