package com.employeemanagement.utility;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String getFileExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        if (originalFileName != null && originalFileName.contains(".")) {
            return originalFileName.substring(originalFileName.indexOf(".") + 1);
        }

        return null;
    }
}
