package com.employeemanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class Users extends BaseEntity {

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;

    @Column(name = "USERNAME_LOGIN_TIME")
    private LocalDateTime userLoginTime;

    @Column(name = "USER_LOGOUT_TIME")
    private LocalDateTime userLogoutTime;

    @Column(name = "PASSWORD_CHANGE_DATE")
    private LocalDateTime passwordChangeDate;

    @Column(name = "USER_ACT_ENABLED")
    private Boolean isUserAccountEnabled;

    @Column(name = "INVALID_ATTEMPT_COUNT")
    private Integer invalidAttemptCount;

    @Column(name = "USER_EMAIL_VERIFIED")
    private Boolean isUserEmailVerified;

    public Users() {
    }

    public Users(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String password, String username, LocalDateTime userLoginTime, LocalDateTime userLogoutTime, LocalDateTime passwordChangeDate, Boolean isUserAccountEnabled, Integer invalidAttemptCount, Boolean isUserEmailVerified) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.password = password;
        this.username = username;
        this.userLoginTime = userLoginTime;
        this.userLogoutTime = userLogoutTime;
        this.passwordChangeDate = passwordChangeDate;
        this.isUserAccountEnabled = isUserAccountEnabled;
        this.invalidAttemptCount = invalidAttemptCount;
        this.isUserEmailVerified = isUserEmailVerified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(LocalDateTime userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public LocalDateTime getUserLogoutTime() {
        return userLogoutTime;
    }

    public void setUserLogoutTime(LocalDateTime userLogoutTime) {
        this.userLogoutTime = userLogoutTime;
    }

    public LocalDateTime getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(LocalDateTime passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    public Boolean getUserAccountEnabled() {
        return isUserAccountEnabled;
    }

    public void setUserAccountEnabled(Boolean userAccountEnabled) {
        isUserAccountEnabled = userAccountEnabled;
    }

    public Integer getInvalidAttemptCount() {
        return invalidAttemptCount;
    }

    public void setInvalidAttemptCount(Integer invalidAttemptCount) {
        this.invalidAttemptCount = invalidAttemptCount;
    }

    public Boolean getUserEmailVerified() {
        return isUserEmailVerified;
    }

    public void setUserEmailVerified(Boolean userEmailVerified) {
        isUserEmailVerified = userEmailVerified;
    }
}
