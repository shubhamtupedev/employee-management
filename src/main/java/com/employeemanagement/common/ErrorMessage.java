package com.employeemanagement.common;

import java.util.HashMap;

public class ErrorMessage {

    public static final HashMap<String, String> errorCodeMsgMap;

    static {
        errorCodeMsgMap = new HashMap<String, String>();
        errorCodeMsgMap.put(ApplicationErrorCodes.ERR_EMAIL_EXISTS, "The provided employee email address: {0} is already registered. Please use a different email.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ERR_PHONE_EXISTS, "The provided employee phone number: {0} is already registered. Please use a different phone number.");
        errorCodeMsgMap.put(ApplicationErrorCodes.NO_RECORDS_FOUND, "No records found for the provided employee ID: {0}");
        errorCodeMsgMap.put(ApplicationErrorCodes.FILE_EXTENSION_NOT_VALID, "Invalid file format. Please upload a valid Excel file with the '.xlsx' extension.");
        errorCodeMsgMap.put(ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR, "An service error occurred while processing your request. Please try again later.");
        errorCodeMsgMap.put(ApplicationErrorCodes.UNEXPECTED_ERROR, "An unexpected error occurred while processing your request. Please try again later.");
        errorCodeMsgMap.put(ApplicationErrorCodes.APPLICATION_UNEXPECTED_ERROR, "An application unexpected error occurred while processing your request. Please try again later.");
        errorCodeMsgMap.put(ApplicationErrorCodes.BEAN_VALIDATION_EXCEPTION, "An unexpected error occurred while processing your request. Please try again later.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ATTENDANCE_ERROR_01, "You have not logged in yet.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ATTENDANCE_ERROR_02, "You have already logged in.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ATTENDANCE_ERROR_03, "You need to log in first.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ATTENDANCE_ERROR_04, "You have already logged out.");
        errorCodeMsgMap.put(ApplicationErrorCodes.ATTENDANCE_ERROR_05, "You must complete 9 hours before logging out.");
    }


}
