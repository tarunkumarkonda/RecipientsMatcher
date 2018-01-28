package com.mycompany.recipientsmatcher.model;

import com.opencsv.bean.CsvBindByName;

public class Pickup {
    
    @CsvBindByName(column = "FirstName", required = true)
    String firstName;
    
    @CsvBindByName(column = "LastName", required = true)
    String lastName;
    
    @CsvBindByName(column = "Street")
    String street;
    
    @CsvBindByName(column = "City")
    String city;
    
    @CsvBindByName(column = "State")
    String state;
    
    @CsvBindByName(column = "Postal")
    int postalCode;
    
    @CsvBindByName(column = "Country")
    String country;
    
    @CsvBindByName(column = "Email")
    String email;
    
    @CsvBindByName(column = "Phone")
    String phoneNumber;
    
    @CsvBindByName(column = "Latitude")
    double latitude;
    
    @CsvBindByName(column = "Longitude")
    double longitude;
    
    @CsvBindByName(column = "Categories")
    int categories;
    
    @CsvBindByName(column = "PickupAt")
    String pickupAt;
    
    @CsvBindByName(column = "TimeZoneId")
    String timeZoneId;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public String getPickupAt() {
        return pickupAt;
    }

    public void setPickupAt(String pickupAt) {
        this.pickupAt = pickupAt;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
    
    
}

