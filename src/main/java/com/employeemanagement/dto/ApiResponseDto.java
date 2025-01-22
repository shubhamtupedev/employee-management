package com.employeemanagement.dto;

public class ApiResponseDto<T> {

    private T response;

    public ApiResponseDto() {
    }

    public ApiResponseDto(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
