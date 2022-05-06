package com.robb.banking.menus;

import com.robb.banking.services.UserServices;

import com.robb.banking.models.Customer_info;

import java.io.BufferedReader;

public class RegisterMenu extends Menu{

    private UserServices userServices = new UserServices();

    public RegisterMenu(BufferedReader terminalReader) { super("Register", "/register", terminalReader); }

    @Override
    public void render() throws Exception {
        System.out.println("What is your full name?");
        String fullName = terminalReader.readLine();

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("Date of Birth?");
        String dob = terminalReader.readLine();

        String[] nameArray = fullName.split(" ");
        String fname = nameArray[0];
        String lname = nameArray[1];

        if (!password.equals(passwordCheck)) {
            System.out.println("Passwords don't match");
            return;
        }

        Customer_info newCustomer_info = new Customer_info(fname, lname, email, password, dob);
        System.out.println ("Here is the information that was provided by the user: " + newCustomer_info);
        userServices.registerCustomer_info(newCustomer_info);
    }
}