package com.eduhansol.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "admins")
public class Admin {

    public Admin() {
        super();
    }

    public Admin(String email, String password, int roleId, int departmentId) {
        this.email = email;
        this.password = password;
        this.name = "";
        this.phone = "";
        this.tel = "";
        this.accessFailCount = 0;
        this.isLock = false;
        this.isDelete = false;

        Role role = new Role();
        role.setId(roleId);
        this.role = role;

        Department department = new Department();
        department.setId(departmentId);
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "ID를 입력하세요.")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int accessFailCount;

    @Column(nullable = false)
    private boolean isLock;

    @Column(nullable = false)
    private boolean isFirstLogin;

    @Column(nullable = false)
    private boolean isDelete;

    @Column(nullable = false)
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    private Role role;

    // @ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
    @ManyToOne
    private Department department;

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccessFailCount() {
        return accessFailCount;
    }

    public void setAccessFailCount(int accessFailCount) {
        this.accessFailCount = accessFailCount;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public boolean getIsFirstLogin() {
        return this.isFirstLogin;
    }

    public void setIsFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
