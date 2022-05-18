package com.robb.banking.services;

import com.robb.banking.exceptions.AuthenticationException;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Account_info;
import com.robb.banking.models.Customer_info;
import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class Customer_infoServices implements Serviceable<Customer_info> {

    private Customer_infoDao customer_infoDao;

    private Logger logger = Logger.getLogger();

    public Customer_infoServices(Customer_infoDao customer_infoDao) {
        this.customer_infoDao = customer_infoDao;
    }

    @Override
    public List<Customer_info> readAll() {
        logger.info("Begin reading Customer_infos in our file database.");

        try {
            List<Customer_info> customer_infos = customer_infoDao.findAll();
            logger.info("All customer_infos have been found and here are the results: \n");
            return customer_infos;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer_info readByEmail(String email) throws ResourcePersistanceException {

        Customer_info customer_info = customer_infoDao.findByEmail(email);
        return customer_info;
    }

    @Override
    public Customer_info update(Customer_info updateObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateEmailNotUsed(String email) {
        return customer_infoDao.checkEmail(email);
    }

    public Customer_info create(Customer_info newCustomer_info) {
        logger.info("Customer trying to be registered: " + newCustomer_info);
        if (!validateInput(newCustomer_info)) {
            throw new InvalidRequestException("Customer input was not validated. This could be due to an empty String or null values");
        }

        if (validateEmailNotUsed(newCustomer_info.getEmail())) {
            throw new InvalidRequestException("Customer email is already in use. Please try again with another email address or login into your previous account.");
        }

        Customer_info persistedCustomer_info = customer_infoDao.create(newCustomer_info);

        if (persistedCustomer_info == null) {
            throw new ResourcePersistanceException("Customer was not persisted to the database upon registration.");
        }
        logger.info("Customer has been persisted: " + newCustomer_info);
        return persistedCustomer_info;
    }

    @Override
    public boolean validateInput(Customer_info newCustomer_info) {
        System.out.println("Validating Customer: " + newCustomer_info);
        if (newCustomer_info == null) return false;
        if (newCustomer_info.getFirst_name() == null || newCustomer_info.getFirst_name().trim().equals(""))
            return false;
        if (newCustomer_info.getLast_name() == null || newCustomer_info.getLast_name().trim().equals("")) return false;
        if (newCustomer_info.getEmail() == null || newCustomer_info.getEmail().trim().equals(""))
            return false;
        if (newCustomer_info.getPassword() == null || newCustomer_info.getPassword().trim().equals(""))
            return false;
        return newCustomer_info.getDate_of_birth() != null || !newCustomer_info.getDate_of_birth().trim().equals("");
    }

    @Override
    public Account_info readbyEmail(String email) {
        return null;
    }

    @Override
    public Account_info update(Account_infoServices account_infoUpdate) {
        return null;
    }

    @Override
    public Account_info readById(String id) {
        return null;
    }

    public Customer_info authenticateCustomer_info(String email, String password) {

        if (email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either the username or the password is an invalid entry. Please try logging in again.");
        }

        Customer_info authenticatedCustomer_info = customer_infoDao.authenticateCustomer_info(email, password);

        if (authenticatedCustomer_info == null) {
            throw new AuthenticationException("Unauthenticated user. The information provided was not consistent with our database.");
        }

        return authenticatedCustomer_info;

    }
}
