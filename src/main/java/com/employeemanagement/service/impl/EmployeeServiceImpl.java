package com.employeemanagement.service.impl;

import com.employeemanagement.common.ApplicationBeans;
import com.employeemanagement.common.ApplicationCommonConstant;
import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.dto.ApiResponseDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.entity.Address;
import com.employeemanagement.entity.AsyncAudit;
import com.employeemanagement.entity.Department;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.ApplicationException;
import com.employeemanagement.exception.ServiceException;
import com.employeemanagement.exception.ValidationException;
import com.employeemanagement.repository.AddressRepository;
import com.employeemanagement.repository.AsyncAuditRepository;
import com.employeemanagement.repository.DepartmentRepository;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.utility.BulkValidatorService;
import com.employeemanagement.utility.ExcelFileReader;
import com.employeemanagement.utility.FileUtils;
import com.employeemanagement.utility.NotificationProducer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BulkValidatorService bulkValidatorService;

    @Autowired
    private NotificationProducer notificationProducer;

    @Autowired
    private AsyncAuditRepository asyncAuditRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveEmployee(EmployeeDto employeeDto) throws ApplicationException {
        if (employeeRepository.findByEmail(employeeDto.getEmail()).isPresent()) {
            throw new ValidationException("The provided employee email address: " + employeeDto.getEmail() + " is already registered. Please use a different email.", ApplicationErrorCodes.ERR_EMAIL_EXISTS);
        }

        if (employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()).isPresent()) {
            throw new ValidationException("The provided employee phone number: " + employeeDto.getPhoneNumber() + " is already registered. Please use a different phone number.", ApplicationErrorCodes.ERR_PHONE_EXISTS);
        }

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        AsyncAudit asyncAudit = new AsyncAudit();
        try {
            List<Employee> employeeList = new ArrayList<>();
            Department department = employee.getDepartment();
            employeeList.add(employee);
            department.setEmployee(employeeList);

            Address address = employee.getAddress();
            address.setEmployee(employee);
            employeeRepository.save(employee);
            addressRepository.save(address);
            departmentRepository.save(department);

            asyncAudit.setProcessName("sendNotification");
            asyncAudit.setProcessStartTime(LocalDateTime.now());
            asyncAudit.setQueueName(ApplicationBeans.QUEUE_NAME);
            asyncAuditRepository.save(asyncAudit);

            HashMap<String, String> notificationDataMap = new HashMap<>();
            notificationDataMap.put("personalEmail", employee.getPersonalEmail());
            notificationDataMap.put("phoneNumber", employee.getPhoneNumber());
            notificationDataMap.put("auditId", asyncAudit.getId().toString());
            notificationProducer.sendNotification(notificationDataMap);
        } catch (Exception e) {
            throw ServiceException();
        }
        return new ResponseEntity<>(new ApiResponseDto<>("Employee details saved successfully."), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getEmployee(Long empId) throws ApplicationException {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ValidationException("No records found for the provided employee ID: " + empId, ApplicationErrorCodes.NO_RECORDS_FOUND));
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
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ValidationException("No records found for the provided employee ID: " + empId, ApplicationErrorCodes.NO_RECORDS_FOUND));

        Optional<Employee> empList = employeeRepository.findByEmail(employeeDto.getEmail());
        if (empList.isPresent() && !empList.get().getId().equals(empId)) {
            throw new ValidationException("The provided employee email address: " + employeeDto.getEmail() + " is already registered. Please use a different email.", ApplicationErrorCodes.ERR_EMAIL_EXISTS);
        }

        Optional<Employee> employeeList = employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber());
        if (employeeList.isPresent() && !employeeList.get().getId().equals(empId)) {
            throw new ValidationException("The provided employee phone number: " + employeeDto.getPhoneNumber() + " is already registered. Please use a different phone number.", ApplicationErrorCodes.ERR_PHONE_EXISTS);
        }

        Employee emp = modelMapper.map(employeeDto, Employee.class);
        try {
            emp.setId(employee.getId());
            employeeRepository.save(emp);
        } catch (Exception e) {
            throw ServiceException();
        }
        return new ResponseEntity<>(new ApiResponseDto<>("Employee details updated successfully."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteEmployee(Long empId) throws ApplicationException {
        employeeRepository.findById(empId).orElseThrow(() -> new ValidationException("No records found for the provided employee ID: " + empId, ApplicationErrorCodes.NO_RECORDS_FOUND));
        try {
            employeeRepository.deleteById(empId);
        } catch (Exception ex) {
            throw ServiceException();
        }

        return new ResponseEntity<>(new ApiResponseDto<>("Employee details deleted successfully."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> processBulkUploadEmployeesDetails(MultipartFile file) throws ApplicationException {
        String name = null;
        String email = null;
        String phoneNumber = null;
        String fileExtension = FileUtils.getFileExtension(file);
        if (fileExtension != null && !fileExtension.equals(ApplicationCommonConstant.FILE_EXTENSION)) {
            throw new ValidationException("Invalid file format. Please upload a valid Excel file with the '.xlsx' extension.", ApplicationErrorCodes.FILE_EXTENSION_NOT_VALID);
        }

        //Excel read
        System.out.println("\n=======================================================================");
        System.out.println("Excel read execution started");
        long startTime = System.currentTimeMillis(); // Record start time
        Sheet sheet = ExcelFileReader.readExcel(file);
        List<EmployeeDto> employeeDtoList = new ArrayList<>(sheet.getLastRowNum());
        Iterator<Row> iterator = sheet.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            Row row = (Row) iterator.next();
            if (row.getCell(0) != null) {
                name = row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1) != null) {
                email = row.getCell(1).getStringCellValue();
            }
            if (row.getCell(2) != null) {
                phoneNumber = row.getCell(2).getStringCellValue();
            }
//            EmployeeDto employeeDto = new EmployeeDto(null, name, email, phoneNumber);
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDtoList.add(employeeDto);
        }

        long endTime = System.currentTimeMillis(); // Record end time
        double readExcelExecutionTimeInSeconds = (endTime - startTime) / 1000.0;
        System.out.println("Excel read execution time: " + readExcelExecutionTimeInSeconds + " seconds");
        System.out.println("=======================================================================");

        //Dto Validation
        System.out.println("\n======================================================================");
        System.out.println("Validation execution started");
        long validationStartTime = System.currentTimeMillis();
        HashMap<String, List<String>> errors = bulkValidatorService.validateDetails(employeeDtoList);
        long validationEndTime = System.currentTimeMillis(); // Record end time
        double validationExecutionTimeInSeconds = (validationEndTime - validationStartTime) / 1000.0;
        System.out.println("Validation execution time: " + validationExecutionTimeInSeconds + " seconds");
        System.out.println("========================================================================");
        if (errors != null && !errors.isEmpty()) {
            throw new ValidationException(null, errors);
        }

        System.out.println("\n=======================================================================");
        System.out.println("Bulk save execution started");
        long bulkSaveStartTime = System.currentTimeMillis();
        saveBulkDetails(employeeDtoList);
        long bulkSaveEndTime = System.currentTimeMillis(); // Record end time
        double bulkSaveExecutionTimeInSeconds = (bulkSaveEndTime - bulkSaveStartTime) / 1000.0;
        System.out.println("Bulk save execution time: " + bulkSaveExecutionTimeInSeconds + " seconds");
        System.out.println("=========================================================================");

        String message = String.format("Successfully processed bulk employee details in %.1f seconds.", bulkSaveExecutionTimeInSeconds);
        return new ResponseEntity<>(new ApiResponseDto<>(message), HttpStatus.CREATED);
    }

    private static ServiceException ServiceException() {
        return new ServiceException("An unexpected error occurred while processing your request. Please try again later.", ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR);
    }

    public void saveBulkDetails(List<EmployeeDto> employeeDtoList) {
        try {
            List<Employee> employeeList = employeeDtoList.stream().map(this::convertToEntity).toList();
            employeeRepository.saveAll(employeeList);
        } catch (Exception exception) {
            throw ServiceException();
        }
    }

    private Employee convertToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }
}
