package com.employeemanagement.dto;

import java.time.LocalDate;

public class ErrorDetailsDto {

    private LocalDate timestamp;
    private String message;
    private String errorCode;
    private String path;

    public ErrorDetailsDto() {
    }

    public ErrorDetailsDto(LocalDate timestamp, String message, String errorCode, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
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
}
