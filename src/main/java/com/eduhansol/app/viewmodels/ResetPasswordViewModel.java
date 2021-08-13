package com.eduhansol.app.viewmodels;

import javax.validation.constraints.NotBlank;

public class ResetPasswordViewModel{

    @NotBlank(message = "현재 비밀번호를 입력하세요.")
    private String currentPassword;

    @NotBlank(message = "새 비밀번호를 입력하세요.")
    private String newPassword;

    @NotBlank(message = "새 비밀번호 확인를 입력하세요.")
    private String newPasswordConfirm;

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return this.newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}