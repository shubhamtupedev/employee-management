package com.employeemanagement.service.impl;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.entity.dto.ApiResponseDto;
import com.employeemanagement.entity.Attendance;
import com.employeemanagement.exception.ValidationException;
import com.employeemanagement.repository.AttendanceRepository;
import com.employeemanagement.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> markLoginTime(Long empId, LocalDateTime loginDateTime) {
        List<Attendance> attendanceList = attendanceRepository.findByEmpIdAndLocalDate(empId, LocalDate.now());
        attendanceList.forEach(attendance -> validateLoginAndLogoutTime(true, false, attendance, loginDateTime));

        Attendance attendance = new Attendance();
        attendance.setEmpId(empId);
        attendance.setLoginTime(loginDateTime);
        attendance.setLoginDone(true);
        attendance.setLogoutDone(false);
        attendance.setLocalDate(LocalDate.now());
        attendanceRepository.save(attendance);

        return ResponseEntity.ok().body(new ApiResponseDto<>("Login successful. Thank you."));
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> markLogoutTime(Long empId, LocalDateTime logoutDateTime) {
        List<Attendance> attendanceList = attendanceRepository.findByEmpIdAndLocalDate(empId, LocalDate.now());

        if (attendanceList.isEmpty()) {
            throw new ValidationException(ApplicationErrorCodes.ATTENDANCE_ERROR_01, null);
        }
        Attendance attendance = (Attendance) attendanceList.get(0);
        validateLoginAndLogoutTime(false, true, attendance, logoutDateTime);

        attendance.setLogoutTime(logoutDateTime);
        attendance.setLogoutDone(true);
        attendanceRepository.save(attendance);

        return new ResponseEntity<>(new ApiResponseDto<>("Logout successful. Thank you."), HttpStatus.OK);
    }

    private void validateLoginAndLogoutTime(Boolean isLogin, Boolean isLogout, Attendance attendance, LocalDateTime localDateTime) {
        if (isLogin && attendance.getLoginDone()) {
            throw new ValidationException(ApplicationErrorCodes.ATTENDANCE_ERROR_02, null);
        }

        if (isLogout) {
            if (!attendance.getLoginDone()) {
                throw new ValidationException(ApplicationErrorCodes.ATTENDANCE_ERROR_03, null);
            }
            if (attendance.getLogoutDone()) {
                throw new ValidationException(ApplicationErrorCodes.ATTENDANCE_ERROR_04, null);
            }

            LocalDateTime requiredLogoutTime = attendance.getLoginTime().plusHours(9);
            if (localDateTime.isBefore(requiredLogoutTime)) {
                throw new ValidationException(ApplicationErrorCodes.ATTENDANCE_ERROR_05, null);
            }
        }
    }
}
