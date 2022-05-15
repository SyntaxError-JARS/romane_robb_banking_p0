package com.robb.banking.services;

import com.robb.banking.daos.Account_infoDao;
import com.robb.banking.models.Account_info;
import com.robb.banking.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class Account_infoServices implements Serviceable<Account_info> {

    private final Account_infoDao account_infoDao;

    private Logger logger = Logger.getLogger();

    public Account_infoServices(Account_infoDao account_infoDao){
        this.account_infoDao = account_infoDao;
    }

    @Override
    public Account_info create(Account_info newAccount_info) { return account_infoDao.create(newAccount_info); }

    @Override
    public List<Account_info> readAll() {
        try {
            return account_infoDao.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account_info readById(String id) { return account_infoDao.findById(id); }

    @Override
    public Account_info update(Account_info updatedAccount_info) { return null; }

    @Override
    public boolean delete(String id) { return false; }

    @Override
    public boolean validateInput(Account_info object) { return false; }

    @Override
    public Account_info readbyID(String id) {
        return null;
    }

}
