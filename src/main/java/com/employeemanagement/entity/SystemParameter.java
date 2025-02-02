package com.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "SYS_PARAM")
public class SystemParameter extends BaseEntity {

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "VALUE", unique = true, nullable = false)
    private String value;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TYPE", nullable = false)
    private String type;

    public SystemParameter() {
    }

    public SystemParameter(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String code, String value, String description, String type) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.code = code;
        this.value = value;
        this.description = description;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
