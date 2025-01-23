package com.employeemanagement.dto;

import com.employeemanagement.common.NotEmptyGroupValidation;
import com.employeemanagement.common.OtherGroupValidation;
import com.employeemanagement.common.PatternGroupValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class EmployeeDto implements Serializable {
    private Long id;

    @Size(min = 3, max = 50, message = "Employee name must be between 3 and 50 characters.", groups = OtherGroupValidation.class)
    @NotBlank(message = "Employee name cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Employee name must contain only alphabets and spaces.", groups = PatternGroupValidation.class)
    private String name;

    @NotBlank(message = "Employee email cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Employee email must be a valid email address.", groups = PatternGroupValidation.class)
    private String email;

    @NotBlank(message = "Employee phone number cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[0-9]{10}$", message = "Employee phone number must be a valid 10-digit number.", groups = PatternGroupValidation.class)
    private String phoneNumber;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
