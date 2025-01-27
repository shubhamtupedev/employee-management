package com.employeemanagement.dto;

import java.time.LocalDateTime;

public class DepartmentDto extends BaseDto {

    private String departmentCode;

    private String departmentName;

    private String departmentDescription;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String departmentCode, String departmentName, String departmentDescription) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }
}
