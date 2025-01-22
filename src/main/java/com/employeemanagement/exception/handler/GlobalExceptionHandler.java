package com.employeemanagement.exception.handler;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.dto.ErrorDetailsDto;
import com.employeemanagement.exception.ApplicationException;
import com.employeemanagement.exception.ServiceException;
import com.employeemanagement.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> handleGlobalException(Exception ex, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDate.now(), ex.getMessage(), ApplicationErrorCodes.UNEXPECTED_ERROR, webRequest.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetailsDto);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetailsDto> handleApplicationException(ApplicationException ex, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDate.now(), ex.getMessage(), ex.getErrorCode(), webRequest.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetailsDto);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDetailsDto> handleServiceException(ServiceException ex, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDate.now(), ex.getMessage(), ex.getErrorCode(), webRequest.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetailsDto);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetailsDto> handleValidationException(ValidationException ex, WebRequest webRequest) {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDate.now(), ex.getMessage(), ex.getErrorCode(), webRequest.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetailsDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetailsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        HashMap<String, String> errorMap = new HashMap<>();
        for (ObjectError error : objectErrorList) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName, message);
        }
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(LocalDate.now(), errorMap.toString(), ApplicationErrorCodes.BEAN_VALIDATION_EXCEPTION, webRequest.getDescription(false));
        return ResponseEntity.badRequest().body(errorDetailsDto);
    }

}