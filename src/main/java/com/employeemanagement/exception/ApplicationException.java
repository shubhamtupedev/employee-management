package com.employeemanagement.exception;

public class ApplicationException extends RuntimeException {

    private String errorCode;

    public ApplicationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
