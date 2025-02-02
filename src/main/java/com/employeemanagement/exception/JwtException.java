package com.employeemanagement.exception;

public class JwtException extends RuntimeException {

    private String errorCode;

    public JwtException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
