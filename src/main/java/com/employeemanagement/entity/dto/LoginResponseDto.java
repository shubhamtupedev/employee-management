package com.employeemanagement.entity.dto;

public class LoginResponseDto {

    private String message;
    private String jwtToken;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String message, String jwtToken) {
        this.message = message;
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
