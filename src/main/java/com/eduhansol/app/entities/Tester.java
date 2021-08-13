package com.eduhansol.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "testers")
public class Tester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private Group group;

    @Column(nullable = false)
    @NotBlank(message = "지원자 ID를 입력하세요.")
    @Length(min = 9, max = 10, message = "지원자 ID는 10자리 숫자만 가능합니다. 지원자 번호 앞에 \"0\"채워서 10자리로 만들어주세요.")
    private String applyId;

    @Column(nullable = false)
    @NotBlank(message = "이름을 입력하세요.")
    @Length(min = 1, max = 100, message = "지원자 이름은 최대 100자리까지 입력 가능합니다.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "생년월일을 입력하세요.")
    @Length(min = 8, max = 8, message = "생년월일은 YYYYMMDD 형식으로 8자리 입력만 가능합니다.")
    private String birthday;

    @Column(nullable = false)
    @NotBlank(message = "휴대폰을 입력하세요.")
    @Length(min = 10, max = 13, message="휴대폰 번호는 최소 10자리 이상 13자리 이하 입력만 가능합니다.")
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean gender;

    @Column(nullable = false)
    private String authKey;

    @Column(nullable = false)
    private int testState;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private boolean testResult;

    @Column(nullable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Column(nullable = true)
    private LocalDateTime startDateTime;

    @Column(nullable = true)
    private LocalDateTime completedDateTime;

    @Column(nullable = false)
    private boolean isDelete;

    @Column(nullable = false)
    private boolean isRetry;

    public boolean isIsRetry() {
        return this.isRetry;
    }

    public boolean getIsRetry() {
        return this.isRetry;
    }

    public void setIsRetry(boolean isRetry) {
        this.isRetry = isRetry;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public int getTestState() {
        return this.testState;
    }

    public void setTestState(int testState) {
        this.testState = testState;
    }

    public LocalDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getCompletedDateTime() {
        return this.completedDateTime;
    }

    public void setCompletedDateTime(LocalDateTime completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    public boolean isIsDelete() {
        return this.isDelete;
    }

    public boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    public String getApplyId() {
        return this.applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public boolean getTestResult() {
        return this.testResult;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }
}
