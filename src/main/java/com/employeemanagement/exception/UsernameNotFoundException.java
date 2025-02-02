package com.employeemanagement.exception;

public class UsernameNotFoundException extends RuntimeException {

    private String errorCode;

    public UsernameNotFoundException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
