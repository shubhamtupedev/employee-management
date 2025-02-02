package com.employeemanagement.service;

import com.employeemanagement.entity.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface AttendanceService {

    public ResponseEntity<ApiResponseDto<?>> markLoginTime(Long empId, LocalDateTime loginDateTime);

    public ResponseEntity<ApiResponseDto<?>> markLogoutTime(Long empId, LocalDateTime logoutDateTime);

}
