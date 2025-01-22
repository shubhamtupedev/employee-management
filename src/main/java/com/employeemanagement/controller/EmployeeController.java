package com.employeemanagement.controller;

import com.employeemanagement.dto.ApiResponseDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<?>> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<?>> getEmployee(@PathVariable("id") Long empId) {
        return employeeService.getEmployee(empId);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getEmployees() {
        return employeeService.getEmployees();
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<?>> getEmployee(@PathVariable("id") Long empId, @RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.updateEmployee(empId, employeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteEmployee(@PathVariable("id") Long empId) {
        return employeeService.deleteEmployee(empId);
    }
}
