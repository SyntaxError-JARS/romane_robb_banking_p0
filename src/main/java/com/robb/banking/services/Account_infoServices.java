package com.robb.banking.services;

import com.robb.banking.daos.Account_infoDao;
import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Account_info;
import com.robb.banking.util.logging.Logger;

import java.util.List;

public class Account_infoServices implements Serviceable<Account_info> {

    private Account_infoDao account_infoDao;

    private Account_info account_info;

    private Customer_infoDao customer_infoDao;
    private Logger logger = Logger.getLogger();

    public Account_infoServices(Account_infoDao account_infoDao, Customer_infoDao customer_infoDao){
        this.account_infoDao = account_infoDao;
        this.customer_infoDao = customer_infoDao;

    }

    public Account_infoServices(Account_infoDao account_infoDao) {
    }

    private boolean validateAccountInput(Account_info newAccount_info){

        System.out.println("Account_infoServices::validateAccountInput() : Validating Account: " + newAccount_info);

        if(newAccount_info == null) return false;
        if(newAccount_info.getAccount_number() == null || newAccount_info.getAccount_number().trim().equals("")) return false;
        if(newAccount_info.getAccount_type() == null || newAccount_info.getAccount_type().trim().equals("")) return false;
        if(newAccount_info.getEmail() == null || newAccount_info.getEmail().trim().equals("")) return false;
        if(newAccount_info.getMemo() == null || newAccount_info.getMemo().trim().equals("")) return false;
        return newAccount_info.getAccount_number() != null || !newAccount_info.getAccount_number().trim().equals("");
}

    @Override
    public Account_info create(Account_info newAccount_info) {
            System.out.println("AccountService::create() : Account trying to be registered: " + newAccount_info);

//            if(!validateAccountInput(newAccount_info)){ // checking if false
//                //System.out.println("User was not validated");
//                throw new InvalidRequestException("Account information was not validated");
//            }

            // TODO: Will implement with JDBC (connecting to our database)
            //
//            if(persistedAccount_info == null){
//                throw new RuntimeException();
//            }
//            System.out.println("AccountService::registerAccount() : Account has been persisted: " + persistedAccount_info);
            return account_infoDao.create(newAccount_info);
        }


    @Override
    public List<Account_info> readAll() {
        return null;
    }

    private boolean validateEmailNotUsed(String email) {
        return false;
    }

    @Override
    public Account_info readByEmail(String id) {
        return null;
    }

    @Override
    public Account_info findById(String id) {
        return null;
    }

    @Override
    public Account_info readById(String id) {
        return null;
    }

    @Override
    public Account_info findbyId(String id) {
        return null;
    }


    @Override
    public Account_info update(Account_info updatedAccount_info) {
        logger.info("User trying to update: " + updatedAccount_info);
        if(!validateInput(updatedAccount_info)){
            throw new InvalidRequestException("Account was not validated");
        }
        if(validateEmailNotUsed(updatedAccount_info.getEmail())){
            throw new InvalidRequestException("User id is already in use. Please try again with another email or login into previous made account.");
        }

        Account_info persistedAccount_info = account_infoDao.create(updatedAccount_info);

        logger.info("Account has been persisted: " + updatedAccount_info);
        return updatedAccount_info;
    }


    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Account_info object) {
        return true;
    }

    @Override
    public Account_info readbyEmail(String id) {
        return null;
    }

    @Override
    public Account_info update(Account_infoServices account_infoUpdate) {
        return null;
    }
}