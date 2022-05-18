package com.robb.banking.services;

import com.robb.banking.daos.Account_infoDao;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Account_info;
import com.robb.banking.util.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Account_infoServices implements Serviceable<Account_info> {

  private static Account_infoDao account_infoDao;

    public Account_infoServices(Account_infoDao account_infoDao) {
        this.account_infoDao = account_infoDao;
    }


    public boolean CreateBankAccount(Account_info account_info) {

        Account_info persistedAccount = account_infoDao.create(account_info);

//        if(persistedAccount == null){
//            throw new ResourcePersistanceException("Account was not persisted on account creation");
//        }

        return true;
    }

    public boolean validateAccount(Account_info account_info) {
        if (account_info == null) return false;
        if (account_info.getAccount_number() == null) return false;
        return true;
    }

    public Account_info findbyId(String bId) {

        Account_info findbyId = account_infoDao.findById(bId);

        return findbyId;
    }

    public ArrayList<Account_info> findAll() throws IOException {

        ArrayList<Account_info> findAll = (ArrayList<Account_info>) account_infoDao.findAll();

        return findAll;
    }

    public static boolean updateAccountName(String id2, String newBankAccountName) {
        boolean updatedBankAccount = account_infoDao.update(id2, newBankAccountName);

        return updatedBankAccount;

    }

    public static boolean deleteBankAccount(String id) {
        boolean deletedBankAccount = account_infoDao.delete(id);

        return deletedBankAccount;
    }

    public Account_info deposit(String deposit, String id) {

        if (depositCheck(deposit) == false) {
            throw new InvalidRequestException("User inputted a negative number or not a int. Please check to see if the information enter was correct.");
        }
        Account_info depositInAccount = account_infoDao.deposit(deposit, id);


        return depositInAccount;

    }

    public Account_info withDraw(String deposit, String id) {


        //BankAccountData.currentAccountAmount();
        if (withdrawCheck(deposit) == false) {
            throw new InvalidRequestException("User inputted a negative number or not a int. Also you could have entered more money than you have. Please check to see if the information enter was correct or that you have enough money.");
        }

        Account_info withdrawInAccount = account_infoDao.withdraw(deposit, id);

        //

        return withdrawInAccount;

    }

//    public boolean deleteBankAccount(String id) {
//        boolean deletedBankAccount = account_infoDao.delete(id);
//
//        return deletedBankAccount;
//    }

    //if(bankAccountData.getBankAccountName() == null || bankAccountData.getBankAccountName().trim().equals("")) return false;
    public boolean depositCheck(String deposit) {

        if (Integer.parseInt(deposit) < 0 || deposit.equals("")) return false;



        return true;
    }

    public boolean withdrawCheck(String deposit) {

        //Account_info account_info;
        if (Integer.parseInt(deposit) < 0 || deposit.equals("")) return false;
        if (Account_info.getAccount_balance()- Integer.parseInt(deposit) < 0) return false;

        return true;
    }


    @Override
    public Account_info create(Account_info newObject) {
        return null;
    }

    @Override
    public List<Account_info> readAll() {
        return null;
    }

    @Override
    public Account_info readByEmail(String id) {
        return null;
    }

    @Override
    public Account_info update(Account_info updateObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Account_info object) {
        return false;
    }

    @Override
    public Account_info readbyEmail(String id) {
        return null;
    }
}