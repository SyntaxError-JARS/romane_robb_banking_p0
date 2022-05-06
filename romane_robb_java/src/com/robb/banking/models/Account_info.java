// The package is used to group related classes. It is like a folder in a file directory.

package com.robb.banking.models;

// The use of the public class is to make the Account_info class accessible and visible to all other classes.
public class Account_info {
    private String account_number;
    private int account_balance;
    private String account_type;
    private String user_email;
    private String memo;

    public Account_info() { super(); }

    public Account_info(String account_number, int account_balance, String account_type, String user_email, String memo){
        super();
        this.account_number = account_number;
        this.account_balance = account_balance;
        this.account_type = account_type;
        this.user_email = user_email;
        this.memo = memo;
    }

// public makes the class accessible and visible to all other classes.
// void means that this method does not have a return value.
    public String getAccount_number() { return account_number; }

    public void setAccount_number (String getAccount_number) { this.account_number = account_number; }

    public int getAccount_balance() { return account_balance; }

    public void setAccount_balance(int setAccount_balance) { this.account_balance = account_balance; }

    public String getAccount_type() { return account_type; }

    public void setAccount_type(String setAccount_type) { this.account_type = account_type; }

    public String getUser_email() { return user_email; }

    public void setUser_email(String setUser_email) { this.user_email = user_email; }

    public String getMemo() { return memo; }

    public void setMemo(String setMemo) { this.memo = memo; }

// The use of the Override keyword below is to override the Account_info above.
// Overriding is when a subclass (child class) has the same method as the parent class.
// Overriding is utilizing polymorphism in regard to Object-oriented programming (OOP).
    @Override
    public String toString() {
        return "Account_info{" +
                "account_number='" + account_number + '\'' +
                ", account_balance='" + account_balance + '\'' +
                ", account_type='" + account_type + '\'' +
                ", user_email='" + user_email + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
