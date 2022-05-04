package com.robb.banking.models;

public class Customer_info {

    private String first_name;

    private String last_name;

    private String email_address;

    private String userpassword;

    private String date_of_birth;

    public Customer_info(String first_name, String last_name, String email_address, String userpassword, String date_of_birth){
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.userpassword = userpassword;
        this.date_of_birth = date_of_birth;
    }

    public Customer_info() {

    }

    public String getFirst_name() { return first_name; }

    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getEmail_address () { return email_address; }

    public void setEmail_address(String email_address) { this.email_address = email_address; }

    public String getUserpassword() { return userpassword; }

    public void setUserpassword(String userpassword) { this.userpassword = userpassword; }

    public String getDate_of_birth() { return date_of_birth; }

    public void setDate_of_birth(String date_of_birth) { this.date_of_birth = date_of_birth; }

    public String toFileString() {
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(first_name).append(",")
                .append(last_name).append(",")
                .append(email_address).append(",")
                .append(email_address).append(",")
                .append(userpassword).append(",")
                .append(date_of_birth).append(",");

        return mutableString.toString();
    }

    @Override
    public String toString() {
        return "Customer_info{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_address='" + email_address + '\'' +
                
    }
}
