package com.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "EMPLOYEE_ATTENDANCE")
public class Attendance extends BaseEntity {

    @Column(name = "EMP_ID", nullable = false)
    private Long empId;

    @Column(name = "LOGIN_TIME")
    private LocalDateTime loginTime;

    @Column(name = "LOGOUT_TIME")
    private LocalDateTime logoutTime;

    @Column(name = "IS_ON_LEAVE")
    private Boolean isOnLeave;

    @Column(name = "IS_LOGIN_DONE")
    private Boolean isLoginDone;

    @Column(name = "IS_LOGOUT_DONE")
    private Boolean isLogoutDone;

    @Column(name = "LOCAL_DATE")
    private LocalDate localDate;

    public Attendance() {
    }

    public Attendance(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, Long empId, LocalDateTime loginTime, LocalDateTime logoutTime, Boolean isOnLeave, Boolean isLoginDone, Boolean isLogoutDone, LocalDate localDate) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.empId = empId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.isOnLeave = isOnLeave;
        this.isLoginDone = isLoginDone;
        this.isLogoutDone = isLogoutDone;
        this.localDate = localDate;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Boolean getOnLeave() {
        return isOnLeave;
    }

    public void setOnLeave(Boolean onLeave) {
        isOnLeave = onLeave;
    }

    public Boolean getLoginDone() {
        return isLoginDone;
    }

    public void setLoginDone(Boolean loginDone) {
        isLoginDone = loginDone;
    }

    public Boolean getLogoutDone() {
        return isLogoutDone;
    }

    public void setLogoutDone(Boolean logoutDone) {
        isLogoutDone = logoutDone;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
