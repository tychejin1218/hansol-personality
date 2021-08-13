package com.eduhansol.app.viewmodels;

import javax.validation.constraints.NotBlank;

public class TesterLoginViewModel {

    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    @NotBlank(message = "인증키를 입력하세요.")
    private String authKey;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
