package com.employeemanagement.service;

import com.employeemanagement.dto.ApiResponseDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {

    public ResponseEntity<ApiResponseDto<?>> saveEmployee(EmployeeDto employeeDto) throws ApplicationException;

    public ResponseEntity<ApiResponseDto<?>> getEmployee(Long empId) throws ApplicationException;

    public ResponseEntity<ApiResponseDto<?>> getEmployees() throws ApplicationException;

    public ResponseEntity<ApiResponseDto<?>> updateEmployee(Long empId, EmployeeDto employeeDto) throws ApplicationException;

    public ResponseEntity<ApiResponseDto<?>> deleteEmployee(Long empId) throws ApplicationException;

    public ResponseEntity<ApiResponseDto<?>> processBulkUploadEmployeesDetails(MultipartFile file) throws ApplicationException;

}
