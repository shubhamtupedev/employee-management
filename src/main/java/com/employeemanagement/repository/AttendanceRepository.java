package com.employeemanagement.repository;

import com.employeemanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    public List<Attendance> findByEmpIdAndLocalDate(Long empId, LocalDate localDate);
}
