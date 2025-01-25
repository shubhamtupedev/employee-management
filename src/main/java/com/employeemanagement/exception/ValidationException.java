package com.employeemanagement.exception;

import java.util.HashMap;
import java.util.List;

public class ValidationException extends RuntimeException {

    private String errorCode;
    private HashMap<String, List<String>> errors;

    public ValidationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidationException(String message, HashMap<String, List<String>> errors) {
        super(message);
        this.errors = errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HashMap<String, List<String>> getErrors() {
        return errors;
    }
}
