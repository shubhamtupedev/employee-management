package com.employeemanagement.dto;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ErrorDetailsDto {

    private LocalDateTime timestamp;
    private String message;
    private String errorCode;
    private String path;
    HashMap<String, String> error;

    public ErrorDetailsDto() {
    }

    public ErrorDetailsDto(LocalDateTime timestamp, String message, String errorCode, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
    }

    public ErrorDetailsDto(LocalDateTime timestamp, String errorCode, String path, HashMap<String, String> error) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.path = path;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }
}
