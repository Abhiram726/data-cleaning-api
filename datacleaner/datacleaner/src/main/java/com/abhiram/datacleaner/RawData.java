package com.abhiram.datacleaner;

// This class represents incoming JSON structure (name, email, city)
public class RawData {

    private String name;
    private String email;
    private String city;

    public RawData() {
        // Default constructor required for JSON deserialization
    }

    public RawData(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
