package com.employeemanagement.repository;

import com.employeemanagement.entity.RequestResponseAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestResponseAuditRepository extends JpaRepository<RequestResponseAudit, Long> {
}
