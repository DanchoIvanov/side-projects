package com.example.user_management.model;

import java.time.LocalDate;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;

    public User(int id, String firstName, String lastName, LocalDate birthdate, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
