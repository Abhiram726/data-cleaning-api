package com.abhiram.datacleaner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CleanedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // Primary key (auto-generated)

    private String name;
    private String email;
    private String city;
    private String value;

    // Default constructor (needed by JPA)
    public CleanedData() {}

    // Convenience constructor
    public CleanedData(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

     public void setValue(String value) {
        this.value = value;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}