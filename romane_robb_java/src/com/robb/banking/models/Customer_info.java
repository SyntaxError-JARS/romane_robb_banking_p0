// The package is used to group related classes. It is like a folder in a file directory.

package com.robb.banking.models;

// The use of the public class is to make the Customer_info class accessible and visible to all other classes.
public class Customer_info {

// The use of private below is to restrict access to ONLY be accessed by the defining class which in this case would be Customer_info.
    private String first_name;
    private String last_name;
    private String email_address;
    private String userpassword;
    private String date_of_birth;


// The use of "this" is referring to the current object. We do this to eliminate confusion between class attributes and parameters of the same name.
// The use of super is to access the parent class constructor (a special method that is used to initialize objects).
// Super is utilizing inheritance in regard to Object-oriented programming (OOP).
// () is used to call the specific method it is next to.
    public Customer_info(String first_name, String last_name, String email_address, String userpassword, String date_of_birth) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.userpassword = userpassword;
        this.date_of_birth = date_of_birth;
    }

    public Customer_info() {
    }

    public String getFirst_name() {
        return first_name;
    }

// public makes the class accessible and visible to all other classes.
// void means that this method does not have a return value.

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

// the Stringbuilder class is used to modify the variables in purple.
// .append is used to change the value to the current sequence.
// append is a StringBuilder and StringBuffer class method.
//
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
// The use of the Override keyword below is to override the Customer_info above.
// Overriding is when a subclass (child class) has the same method as the parent class.
// Overriding is utilizing polymorphism in regard to Object-oriented programming (OOP).
    @Override
    public String toString() {
        return "Customer_info{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_address='" + email_address + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';

    }

}