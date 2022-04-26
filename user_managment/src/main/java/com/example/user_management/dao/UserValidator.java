package com.example.user_management.dao;

import com.example.user_management.model.User;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private final String PHONE_PATTERN = "[0-9]{10}";
    private final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public void isValid(User user) throws InvalidUserException {
        if (user.getFirstName().trim().isEmpty()) {
            throw new InvalidUserException("invalid first name");
        }

        if (user.getLastName().trim().isEmpty()) {
            throw new InvalidUserException("invalid last name");
        }

        if (user.getBirthdate().compareTo(LocalDate.now()) > 0) {
            throw new InvalidUserException("invalid birthdate");
        }

        if (!isPhoneNumber(user.getPhoneNumber())) {
            throw new InvalidUserException("invalid phone");
        }

        if (!isEmail(user.getEmail())) {
            throw new InvalidUserException("invalid email");
        }
    }

    private boolean isPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public class InvalidUserException extends Exception {
        public InvalidUserException(String errorMessage) {
            super(errorMessage);
        }
    }

}
