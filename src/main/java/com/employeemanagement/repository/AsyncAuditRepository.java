package com.employeemanagement.repository;

import com.employeemanagement.entity.AsyncAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsyncAuditRepository extends JpaRepository<AsyncAudit, Long> {
}
