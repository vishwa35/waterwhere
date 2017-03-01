package com.gitatme.waterwhere;

/**
 * Created by shuka on 3/1/2017.
 */

public class User {
    private String name;
    private String email;
    private String pass;
    private String phone;
    private String address;
    private String accountType;

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
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter for name attribute
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * getter for pass attribute
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * getter for phone attribute
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getter for address attribute
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for accountType attribute
     * @return
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
