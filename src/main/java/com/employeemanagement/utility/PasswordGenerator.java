package com.employeemanagement.utility;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*";

    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SYMBOLS;
    private static final int PASSWORD_LENGTH = 8;

    public static void main(String[] args) {
        System.out.println("Generated Password: " + generatePassword());
    }

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Ensure password has at least one lowercase, one uppercase, one digit, and one symbol
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));

        // Fill the rest of the password with random characters from all categories
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Shuffle to ensure randomness
        StringBuilder shuffledPassword = new StringBuilder(PASSWORD_LENGTH);
        while (password.length() > 0) {
            int index = random.nextInt(password.length());
            shuffledPassword.append(password.charAt(index));
            password.deleteCharAt(index);
        }

        return shuffledPassword.toString();
    }
}

