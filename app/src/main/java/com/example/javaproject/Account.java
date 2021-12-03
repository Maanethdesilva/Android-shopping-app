package com.example.javaproject;

public class Account {
    public String firstName;
    public String lastName;
    public String emailAddress;
    public boolean isStoreOwner;
    public int phoneNumber;
    public String storeName;

    public Account() {

    }

    public Account(String firstName, String lastName, String emailAddress, boolean isStoreOwner, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.isStoreOwner = isStoreOwner;
        this.phoneNumber = phoneNumber;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isStoreOwner() {
        return isStoreOwner;
    }

    public void setStoreOwner(boolean storeOwner) {
        isStoreOwner = storeOwner;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}


