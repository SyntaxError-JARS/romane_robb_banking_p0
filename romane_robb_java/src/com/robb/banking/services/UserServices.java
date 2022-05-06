package com.robb.banking.services;

import com.robb.banking.daos.UserDao;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Customer_info;

import java.io.IOException;
import java.io.InvalidClassException;

public class UserServices {

    private UserDao userDao = new UserDao();

    public void readCustomer_info(){
        System.out.println("Start reading Users in our file database.");
        Customer_info[] customer_info;

        try {

            Customer_info[] Customer_info = userDao.findAll();
            System.out.println("Here are all the customers found: \n");
                // for (int = 0; i < users.length; i++) {
                    // User user = users[i];
                    // if(user != null) {
                        // System.out.println(User);
                    // }
                // }

            Customer_info user = new Customer_info();

            Object customer_info1 = new Customer_info ("Marilyn", "Monroe", "mm@hollywood.com", "passwordmm", "6/1/1926");

            Customer_info test = new Customer_info();
            System.out.println(test.getLast_name());

            for(Object t:Customer_info ){
                if(t != null) {
                    System.out.println((Customer_info) t);
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean validateEmailNotUsed(String email){
        userDao.checkEmail(email);
        return false;
    }

    public boolean registerCustomer_info(Customer_info newCustomer_info){
        System.out.println("New customer trying to register: " + newCustomer_info);
        if(!validateUserInput(newCustomer_info)){
            System.out.println("Customer was not validated");
            throw new RuntimeException();
        }

        validateEmailNotUsed(newCustomer_info.getEmail_address());

        boolean persistedCustomer_info = false;
        if(persistedCustomer_info = Boolean.parseBoolean(null)){
            throw new InvalidRequestException("User input was not validated. It either contained an empty string or no value.");
        }
        System.out.println("Customer has been persisted: " + newCustomer_info);
        return true;
    }

    private boolean validateUserInput(Customer_info newCustomer_info) {
        System.out.println("Validating Customer: " + newCustomer_info);
        if(newCustomer_info == null) return false;
        if(newCustomer_info.getFirst_name() == null || newCustomer_info.getFirst_name().trim().equals("")) return false;
        if(newCustomer_info.getLast_name() == null || newCustomer_info.getLast_name().trim().equals("")) return false;
        if(newCustomer_info.getEmail_address() == null || newCustomer_info.getEmail_address().trim().equals("")) return false;
        if(newCustomer_info.getUserpassword() == null || newCustomer_info.getUserpassword().trim().equals("")) return false;
        return newCustomer_info.getDate_of_birth() != null || !newCustomer_info.getDate_of_birth().trim().equals("");
    }
}
