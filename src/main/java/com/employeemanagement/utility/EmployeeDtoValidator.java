package com.employeemanagement.utility;

import com.employeemanagement.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDtoValidator {

    public List<String> validateEmployeeDto(EmployeeDto employeeDto) {
        List<String> errorList = new ArrayList<>();
        String validationNameMessage = validateName(employeeDto.getName());
        if (validationNameMessage != null) {
            errorList.add("name :" + validationNameMessage);
        }
        String validationEmailMessage = validateEmail(employeeDto.getEmail());
        if (validationEmailMessage != null) {
            errorList.add("email :" + validationEmailMessage);
        }
        String validationPhoneNumberMessage = validatePhoneNumber(employeeDto.getPhoneNumber());
        if (validationPhoneNumberMessage != null) {
            errorList.add("phoneNumber :" + validationPhoneNumberMessage);
        }
        return errorList;
    }

    private static String validateName(String name) {
        String message = null;
        if (name == null) {
            message = "Employee name cannot be blank.";
        } else if (!name.matches("^[a-zA-Z\\s']+$")) {
            message = "Employee name must contain only alphabets and spaces.";
        } else if (name.length() < 3) {
            message = "Employee name must have minimum 3 characters.";
        } else if (name.length() > 50) {
            message = "Employee name must have maximum 50 characters.";
        }
        return message;
    }

    private static String validateEmail(String email) {
        String message = null;
        if (email == null) {
            message = "Employee email cannot be blank.";
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            message = "Employee email must be a valid email address.";
        }
        return message;
    }

    private static String validatePhoneNumber(String phoneNumber) {
        String message = null;
        if (phoneNumber == null) {
            message = "Employee phone number cannot be blank.";
        } else if (!phoneNumber.matches("^[0-9]{10}$")) {
            message = "Employee phone number must be a valid 10-digit number.";
        }
        return message;
    }
}
