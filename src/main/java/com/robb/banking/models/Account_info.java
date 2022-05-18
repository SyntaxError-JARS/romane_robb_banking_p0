// The package is used to group related classes. It is like a folder in a file directory.

package com.robb.banking.models;

import java.io.IOException;

// The use of the public class is to make the Account_info class accessible and visible to all other classes.
public class Account_info {

    private int id;
    private String account_number;
    private double account_balance;
    private String account_type;
    private String email;
    private String memo;

    public Account_info() { super(); }

// The use of "this" is referring to the current object. We do this to eliminate confusion between class attributes and parameters of the same name.
// The use of super is to access the parent class constructor (a special method that is used to initialize objects).
// Super is utilizing inheritance in regard to Object-oriented programming (OOP).
// () is used to call the specific method it is next to.

    public Account_info(int id, String account_number, double account_balance, String account_type, String email, String memo){
        super();
        this.id = id;
        this.account_number = account_number;
        this.account_balance = account_balance;
        this.account_type = account_type;
        this.email = email;
        this.memo = memo;
    }


// public makes the class accessible and visible to all other classes.
// void means that this method does not have a return value.

    public int getId() { return id; }

    public void setId (int id) { this.id = this.id; }
    public String getAccount_number() { return account_number; }

    public void setAccount_number (String getAccount_number) { this.account_number = account_number; }

    public double getAccount_balance() { return account_balance; }

    public void setAccount_balance(double setAccount_balance) { this.account_balance = account_balance; }

    public String getAccount_type() { return account_type; }

    public void setAccount_type(String setAccount_type) { this.account_type = account_type; }

    public String getEmail() { return email; }

    public void setEmail(String setEmail) { this.email = email; }

    public String getMemo() { return memo; }

    public void setMemo(String setMemo) { this.memo = memo; }

// The use of the Override keyword below is to override the Account_info above.
// Overriding is when a subclass (child class) has the same method as the parent class.
// Overriding is utilizing polymorphism in regard to Object-oriented programming (OOP).
    @Override
    public String toString() {
        return "Account_info{" +
                "id='" + id + '\n' +
                "account_number='" + account_number + '\n' +
                ", account_balance='" + account_balance + '\n' +
                ", account_type='" + account_type + '\n' +
                ", email='" + email + '\n' +
                ", memo='" + memo + '\n' +
                '}';
    }
}
