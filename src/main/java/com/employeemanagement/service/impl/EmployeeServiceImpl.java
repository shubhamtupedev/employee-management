package com.employeemanagement.service.impl;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.dto.ApiResponseDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.ApplicationException;
import com.employeemanagement.exception.ServiceException;
import com.employeemanagement.exception.ValidationException;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveEmployee(EmployeeDto employeeDto) throws ApplicationException {
        employeeRepository.findByEmail(employeeDto.getEmail()).orElseThrow(
                () -> new ValidationException("The provided employee email address is already registered. Please use a different email.", ApplicationErrorCodes.ERR_EMAIL_EXISTS)
        );

        employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()).orElseThrow(
                () -> new ValidationException("The provided employee phone number is already registered. Please use a different phone number.", ApplicationErrorCodes.ERR_PHONE_EXISTS)
        );
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw throwServiceException();
        }
        return new ResponseEntity<>(new ApiResponseDto<>("Employee details saved successfully."), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getEmployee(Long empId) throws ApplicationException {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ValidationException("No records found for the provided employee ID: " + empId, ApplicationErrorCodes.NO_RECORDS_FOUND));
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<>(new ApiResponseDto<>(employeeDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getEmployees() throws ApplicationException {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeResponseList = new ArrayList<>();
        if (employeeList.isEmpty()) {
            throw new ValidationException("No records found.", ApplicationErrorCodes.NO_RECORDS_FOUND);
        }
        employeeList.forEach((employee) -> {
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeResponseList.add(employeeDto);
        });
        return new ResponseEntity<>(new ApiResponseDto<>(employeeResponseList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateEmployee(Long empId, EmployeeDto employeeDto) throws ApplicationException {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteEmployee(Long empId) throws ApplicationException {
        return null;
    }

    private static ServiceException throwServiceException() {
        return new ServiceException("An unexpected error occurred while processing your request. Please try again later.", ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR);
    }
}
