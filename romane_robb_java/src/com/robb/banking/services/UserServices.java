package com.robb.banking.services;

import com.robb.banking.daos.UserDao;
import com.robb.banking.models.Customer_info;

public class UserServices {

    private UserDao userDao = new UserDao();

    public void readUsers(){
        System.out.println("Start reading Users in our file database.");
        Customer_info[] customer_info;

        try {

            Customer_info = userDao.findAll();
            System.out.println("Here are all the users found: \n");
                // for (int = 0; i < users.length; i++) {
                    // User user = users[i];
                    // if(user != null) {
                        // System.out.println(User);
                    // }
                // }

            Customer_info user = new Customer_info();

            Object user1 = new Customer_info ("Marilyn", "Monroe", "mm@hollywood.com", "passwordmm", "6/1/1926");

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

    public boolean registerUser(Customer_info newUser){
        System.out.println("New user trying to register: " + newUser);
        if(!validateUserInput(newUser)){
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        validateEmailNotUsed(newUser.getEmail());

        if(persistedCustomer_info == null){
            throw new RuntimeException();
        }
        System.out.println("User has been persisted: " + newUser);
        return true;
    }

    private boolean validateUserInput(Customer_info newCustomer_info) {
        System.out.println("Validating User: " + newCustomer_info);
        if(newCustomer_info == null) return false;
        if(newCustomer_info.getFirst_name() == null || newCustomer_info.getFirst_name().trim().equals("")) return false;
        if(newCustomer_info.getLast_name() == null || newCustomer_info.getLast_name().trim().equals("")) return false;
        if(newCustomer_info.getEmail_address() == null || newCustomer_info.getEmail_address().trim().equals("")) return false;
        if(newCustomer_info.getUserpassword() == null || newCustomer_info.getUserpassword().trim().equals("")) return false;
        if(newCustomer_info.getDate_of_birth() == null || newCustomer_info.getDate_of_birth().trim().equals("")) return false;
    }
}
