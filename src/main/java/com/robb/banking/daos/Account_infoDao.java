package com.robb.banking.daos;

import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.models.Account_info;
import com.robb.banking.models.Customer_info;
import com.robb.banking.util.ConnectionFactory;
import com.robb.banking.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class Account_infoDao implements Crudable<Account_info> {

    private Logger logger = Logger.getLogger();

    private Account_infoDao account_infoDao;
    private Customer_infoDao customer_infoDao;

    @Override
    public Account_info create(Account_info newAccount_info) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account_info values (default, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newAccount_info.getAccount_number());
            ps.setInt(2, newAccount_info.getAccount_balance());
            ps.setString(3, newAccount_info.getAccount_type());
            ps.setString(4, newAccount_info.getEmail());
            ps.setString(5, newAccount_info.getMemo());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("Account info was not entered into our database due to some issues.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            return null;
        }
        return newAccount_info;
    }

    @Override
    public ArrayList<Account_info> findAll() throws IOException {

        List<Account_info> account_infos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account_info";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Account_info account_info = new Account_info();

                account_info.setAccount_number(rs.getString("Account_number"));
                account_info.setAccount_balance(rs.getInt("Account_balance"));
                account_info.setAccount_type(rs.getString("Account_type"));
                account_info.setEmail(rs.getString("Email"));
                account_info.setMemo(rs.getString("Memo"));

                account_infos.add(account_info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<Account_info> newAccount_info = new ArrayList<>();
        return newAccount_info;
    }

    @Override
    public Account_info findByEmail(String email) {
        return null;
    }

//    @Override
//    public Account_info findByEmail(String email) {
//        return null;
//    }

    @Override
    public Account_info findById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account_info where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Account_info was not found in the database. Please check ID entered was correct.");
            }

            Account_info account_info = new Account_info();

            account_info.setAccount_number(rs.getString("Account_number"));
            account_info.setAccount_balance(rs.getInt("Account_balance"));
            account_info.setAccount_type(rs.getString("Account_type"));
            account_info.setEmail(rs.getString("Email"));
            account_info.setMemo(rs.getString("Memo"));

            return account_info;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Account_info updatedObj) {
        return true;
    }

//    @Override
//    public Account_info update(Account_info updateObject) {
//        System.out.println("AccountService::update() : Customer tries to update : " + updateObject);
//
//        private validateAccountInput();
//
//        if(!validateAccountInput(updateObject)){ // checking if false
//            System.out.println("User was not validated");
//            throw new RuntimeException();
//        }
//
//        // TODO: Will implement with JDBC (connecting to our database)
//        if( !account_infoDao.update(updateObject) ){
//            throw new RuntimeException();
//        }
//        System.out.println("AccountService::update() : Account has been updated: " + updateObject);
//        return updateObject;
//    }

    @Override
    public boolean delete(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "delete from account_info where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);


            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Account_info deposit(String deposit, String id) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update account_info set account_balance = account_balance + ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(deposit));
            ps.setInt(2, Integer.parseInt(id));


            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return findById(id);

    }

    public Account_info withdraw(String deposit, String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "update account_info set account_balance = account_balance - ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(deposit));
            ps.setInt(2, Integer.parseInt(id));

            ps.executeUpdate();


            return findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

}