package com.employeemanagement.dto;

import com.employeemanagement.common.NotEmptyGroupValidation;
import com.employeemanagement.common.OtherGroupValidation;
import com.employeemanagement.common.PatternGroupValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDto extends BaseDto {

    @Size(min = 3, max = 50, message = "Employee first name must be between 3 and 50 characters.", groups = OtherGroupValidation.class)
    @NotBlank(message = "Employee first name cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Employee first name must contain only alphabets and spaces.", groups = PatternGroupValidation.class)
    private String firstName;

    @Size(min = 3, max = 50, message = "Employee last name must be between 3 and 50 characters.", groups = OtherGroupValidation.class)
    @NotBlank(message = "Employee last name cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Employee last name must contain only alphabets and spaces.", groups = PatternGroupValidation.class)
    private String lastName;

    @NotBlank(message = "Employee email cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Employee email must be a valid email address.", groups = PatternGroupValidation.class)
    private String email;

    @NotBlank(message = "Employee personal email cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Employee personal email must be a valid email address.", groups = PatternGroupValidation.class)
    private String personalEmail;

    @NotBlank(message = "Employee phone number cannot be blank.", groups = NotEmptyGroupValidation.class)
    @Pattern(regexp = "^[0-9]{10}$", message = "Employee phone number must be a valid 10-digit number.", groups = PatternGroupValidation.class)
    private String phoneNumber;

    @NotNull(message = "Employee date of birth cannot be blank.", groups = NotEmptyGroupValidation.class)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Employee gender cannot be blank.", groups = NotEmptyGroupValidation.class)
    private String gender;

    @NotBlank(message = "Employee code be blank.", groups = NotEmptyGroupValidation.class)
    private String employeeCode;

    @NotNull(message = "Employee date of joining cannot be blank.", groups = NotEmptyGroupValidation.class)
    private LocalDate dateOfJoining;

    @NotBlank(message = "Employee type cannot be blank.", groups = NotEmptyGroupValidation.class)
    private String employmentType;

    @NotBlank(message = "Employee designation cannot be blank.", groups = NotEmptyGroupValidation.class)
    private String designation;

    @NotBlank(message = "Employee salary grade cannot be blank.", groups = NotEmptyGroupValidation.class)
    private String salaryGrade;

    private String status;

    private AddressDto address;

    private DepartmentDto department;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String firstName, String lastName, String email, String personalEmail, String phoneNumber, LocalDate dateOfBirth, String gender, String employeeCode, LocalDate dateOfJoining, String employmentType, String designation, String salaryGrade, String status, AddressDto address, DepartmentDto department) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.employeeCode = employeeCode;
        this.dateOfJoining = dateOfJoining;
        this.employmentType = employmentType;
        this.designation = designation;
        this.salaryGrade = salaryGrade;
        this.status = status;
        this.address = address;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(String salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }
}
