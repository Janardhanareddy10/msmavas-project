package com.APB.main.Asia_Payments_Bank.service;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {

    private static final int PASSWORD_STRENGTH = 10; // Strength of the password encryption
    private static final int MIN_PASSWORD_LENGTH = 8; // Minimum length required for the password
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    // Regex pattern for password: 
    // - At least one digit
    // - At least one lowercase letter
    // - At least one uppercase letter
    // - At least one special character
    // - No whitespace allowed
    // - Minimum length of 8 characters

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    private static Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        return passwordPattern.matcher(password).matches();
    }
}

