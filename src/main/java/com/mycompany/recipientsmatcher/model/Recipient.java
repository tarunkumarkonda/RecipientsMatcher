package com.mycompany.recipientsmatcher.model;

import com.opencsv.bean.CsvBindByName;

public class Recipient {
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
    
    @CsvBindByName(column = "Restrictions")
    int restrictions;
    
    @CsvBindByName(column = "Sunday")
    int sunday;
    
    @CsvBindByName(column = "Monday")
    int monday;
    
    @CsvBindByName(column = "Tuesday")
    int tuesday;
    
    @CsvBindByName(column = "Wednesday")
    int wednesday;
    
    @CsvBindByName(column = "Thursday")
    int thursday;
    
    @CsvBindByName(column = "Friday")
    int friday;
    
    @CsvBindByName(column = "Saturday")
    int saturday;

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

    public int getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(int restrictions) {
        this.restrictions = restrictions;
    }

    public int getSunday() {
        return sunday;
    }

    public void setSunday(int sunday) {
        this.sunday = sunday;
    }

    public int getMonday() {
        return monday;
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public int getFriday() {
        return friday;
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public int getSaturday() {
        return saturday;
    }

    public void setSaturday(int saturday) {
        this.saturday = saturday;
    }

         
    
}
