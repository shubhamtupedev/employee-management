package com.employeemanagement.utility;

import com.employeemanagement.exception.ApplicationException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class ExcelFileReader {

    public static Sheet readExcel(MultipartFile file) {
        Sheet sheet = null;
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            workbook.close();
        } catch (Exception exception) {
            throw new ApplicationException("Excel read failed", null);
        }
        return sheet;
    }
}
