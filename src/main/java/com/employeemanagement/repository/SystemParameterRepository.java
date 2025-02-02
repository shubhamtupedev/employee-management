package com.employeemanagement.repository;

import com.employeemanagement.entity.SystemParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {

    public SystemParameter findByCode(String code);
}
