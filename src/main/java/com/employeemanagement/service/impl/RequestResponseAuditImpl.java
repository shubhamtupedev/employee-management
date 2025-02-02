package com.employeemanagement.service.impl;

import com.employeemanagement.entity.RequestResponseAudit;
import com.employeemanagement.repository.RequestResponseAuditRepository;
import com.employeemanagement.service.RequestResponseAuditService;
import com.employeemanagement.utility.CompressionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestResponseAuditImpl implements RequestResponseAuditService {

    private final RequestResponseAuditRepository requestResponseAuditRepository;

    public RequestResponseAuditImpl(RequestResponseAuditRepository requestResponseAuditRepository) {
        this.requestResponseAuditRepository = requestResponseAuditRepository;
    }

    @Override
    public void saveAuditDetails(String path, String requestPayload, String responsePayload, String error) {
        requestResponseAuditRepository.save(new RequestResponseAudit(null, path, CompressionUtils.compress(requestPayload), CompressionUtils.compress(responsePayload), error, LocalDateTime.now()));
    }
}
