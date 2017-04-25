package com.gitatme.waterwhere.model;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * User class stores the form of a user
 */
@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private String pass;
    private String phone;
    private String address;
    private String accountType;

    /**
     * empty constructor required for firebase
     */
    public User () {

    }

    /**
     * constructor to initialize the user
     * @param name name of user
     * @param email email address
     * @param phone phone numbr
     * @param pass password
     * @param address home address
     * @param accountType type of user - permissions
     */
    public User (String name, String email, String phone,
                 String pass, String address, String accountType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.address = address;
        this.accountType = accountType;
    }

    /**
     * getter for email attribute
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for name attribute
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for pass attribute
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * getter for phone attribute
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getter for address attribute
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for accountType attribute
     * @return accountType
     */
    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "com.gitatme.waterwhere.User{" +
                "name='" + name + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
