package com.employeemanagement.service;

public interface RequestResponseAuditService {

    public void saveAuditDetails(String path, String requestPayload, String responsePayload, String error);
}
