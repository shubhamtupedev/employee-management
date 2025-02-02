package com.employeemanagement.controller;

import com.employeemanagement.entity.dto.ApiResponseDto;
import com.employeemanagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("login")
    public ResponseEntity<ApiResponseDto<?>> markLoginTime(@RequestParam("id") Long empId, LocalDateTime loginDateTime) {
        return attendanceService.markLoginTime(empId, loginDateTime);
    }

    @PostMapping("logout")
    public ResponseEntity<ApiResponseDto<?>> markLogoutTime(@RequestParam("id") Long empId, LocalDateTime logoutDateTime) {
        return attendanceService.markLogoutTime(empId, logoutDateTime);
    }
}
