package com.employeemanagement.utility;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.exception.ServiceException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionUtils {


    // Compress data
    public static byte[] compress(String data) throws ServiceException {
        try {
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
            Deflater deflater = new Deflater();
            deflater.setInput(input);
            deflater.finish();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(input.length);
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new ServiceException(ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR);
        }
    }

    // Decompress data
    public static String decompress(byte[] compressedData) throws ServiceException {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(compressedData);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);
            byte[] buffer = new byte[1024];
            try {
                while (!inflater.finished()) {
                    int count = inflater.inflate(buffer);
                    outputStream.write(buffer, 0, count);
                }
            } catch (Exception e) {
                throw new IOException("Failed to decompress data", e);
            } finally {
                outputStream.close();
            }
            return outputStream.toString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ServiceException(ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR);
        }
    }
}


