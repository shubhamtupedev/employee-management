package com.employeemanagement.utility;

import com.employeemanagement.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BulkValidatorService {

    @Autowired
    private EmployeeDtoValidator employeeDtoValidator;

    public <T> HashMap<String, List<String>> validateDetails(List<T> listOfDto) {
        HashMap<String, List<String>> errors = new HashMap<>();
        int rowNum = 1;
        for (T dto : listOfDto) {
            if (dto instanceof EmployeeDto) {
                List<String> errorList = employeeDtoValidator.validateEmployeeDto((EmployeeDto) dto);
                if (!errorList.isEmpty()) {
                    errors.put("Row number : " + rowNum, employeeDtoValidator.validateEmployeeDto((EmployeeDto) dto));
                }
            }
            rowNum++;
        }
        return errors;
    }
}
