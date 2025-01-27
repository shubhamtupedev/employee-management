package com.employeemanagement.utility;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CreateBulkFakeData {

    public static void main(String[] args) {
        Faker faker = new Faker();

        // Set to track unique emails and avoid duplicates
        Set<String> emailSet = new HashSet<>();
        Set<String> phoneSet = new HashSet<>();

        // Create a workbook and sheet for writing data
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet2 = workbook.createSheet("Employee Data");

        // Create header row
        Row header = sheet2.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Email");
        header.createCell(2).setCellValue("Phone Number");

        // Generate 100,000 unique records
        int recordCount = 100000;
        for (int i = 1; i <= recordCount; i++) {
            // Generate fake employee data
            String firstName = faker.name().firstName();
            String middleName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String fullname = firstName + " " + middleName + " " + lastName;

            String fakeemail;
            // Ensure email uniqueness
            do {
                fakeemail = faker.internet().emailAddress();
            } while (emailSet.contains(fakeemail));
            emailSet.add(fakeemail);

            String fakePhoneNumber;
            do {
                fakePhoneNumber = "9" + faker.number().digits(9); // Starts with 9 and 9 random digits
            } while (phoneSet.contains(fakePhoneNumber));
            phoneSet.add(fakePhoneNumber);

            // Create a new row for each record
            Row row = sheet2.createRow(i);
            row.createCell(0).setCellValue(fullname);
            row.createCell(1).setCellValue(fakeemail);
            row.createCell(2).setCellValue(fakePhoneNumber);
        }

        // Write to Excel file
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\employee_data_100k.xlsx");
            System.out.println();
            System.out.println("true");
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
            System.out.println("Excel file with 100,000 records has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
