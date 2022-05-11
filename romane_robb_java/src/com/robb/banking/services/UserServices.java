package com.robb.banking.services;

import com.robb.banking.daos.UserDao;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.models.Customer_info;
import com.robb.banking.util.logging.Logger;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.io.InvalidClassException;

public class UserServices implements Serviceable<Customer_info>{

    private UserDao userDao;

    private Logger logger = Logger.getLogger();

    public Customer_info[] readAll(){
        logger.info("Begin reading customers in our file database.");

        try {
            Customer_info[] customer_infos = UserDao.findAll();
            logger.info("All customers have been found and here are the results: \n");

            return customer_infos;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer_info update(Customer_info updateObject) { return null; }

    @Override
    public boolean delete(String id) { return false; }

    public boolean validateEmailNotUsed(String email){ return userDao.checkEmail(email); }

    public Customer_info create(Customer_info newCustomer_info){
        logger.info("Customer trying to be registered: " + newCustomer_info);
        if(!validateInput(newCustomer_info)){
            throw new InvalidRequestException("Customer input was not validated. This could be due to an empty String or null values");
        }

        Customer_info persistedCustomer_info = userDao.create(newCustomer_info);

        if(persistedCustomer_info == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration.");
        }
        logger.info("Customer has been persisted: " + newCustomer_info);
        return persistedCustomer_info;
    }

    @Override
    public boolean validateUserInput(Customer_info newCustomer_info) {
        System.out.println("Validating Customer: " + newCustomer_info);
        if(newCustomer_info == null) return false;
        if(newCustomer_info.getFirst_name() == null || newCustomer_info.getFirst_name().trim().equals("")) return false;
        if(newCustomer_info.getLast_name() == null || newCustomer_info.getLast_name().trim().equals("")) return false;
        if(newCustomer_info.getEmail_address() == null || newCustomer_info.getEmail_address().trim().equals("")) return false;
        if(newCustomer_info.getUserpassword() == null || newCustomer_info.getUserpassword().trim().equals("")) return false;
        return newCustomer_info.getDate_of_birth() != null || !newCustomer_info.getDate_of_birth().trim().equals("");
    }

    public Customer_info authenticateCustomer_info(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either the username or the password is an invalid entry. Please try logging in again.");
        }

        Customer_info authenticatedCustomer_info = userDao.authenticateCustomer_info(email, password);

        if (authenticatedCustomer_info == null){
            throw new AuthenticationException("Unauthenticated user. The information provided was not consistent with our database.");
        }

        return authenticatedCustomer_info;

    }
}
