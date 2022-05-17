package com.robb.banking.daos;

import com.robb.banking.models.Customer_info;
import com.robb.banking.util.ConnectionFactory;
import com.robb.banking.util.logging.Logger;
import com.robb.banking.exceptions.ResourcePersistanceException;

import java.io.IOException;
import java.sql.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Customer_infoDao implements Crudable<Customer_info> {

    private Logger logger = Logger.getLogger();

    @Override
    public Customer_info create(Customer_info newCustomer_info) {
        System.out.println("Here is the newCustomer_info as it enters our DAO layer: " + newCustomer_info);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into customer_info (first_name, last_name, email, password, date_of_birth) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newCustomer_info.getFirst_name());
            System.out.println(newCustomer_info.getLast_name());

            ps.setString(1, newCustomer_info.getFirst_name());
            ps.setString(2, newCustomer_info.getLast_name());
            ps.setString(3, newCustomer_info.getEmail());
            ps.setString(4, newCustomer_info.getPassword());
            ps.setString(5, newCustomer_info.getDate_of_birth());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new ResourcePersistanceException("The User was not entered into the database due to some issue.");
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newCustomer_info;

    }

    @Override
    public List<Customer_info> findAll() throws IOException {

        Customer_info[] customer_infos = new Customer_info[10];

        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from customer_info";
            Statement s = conn.createStatement();

            ResultSet rs = ((Statement) s).executeQuery(sql);

            while (rs.next()) {
                Customer_info customer_info = new Customer_info();

                customer_info.setFirst_name(rs.getString("first_name"));
                customer_info.setLast_name(rs.getString("last_name"));
                customer_info.setEmail(rs.getString("email"));
                customer_info.setPassword(rs.getString("password"));
                customer_info.setDate_of_birth(rs.getString("date_of_birth"));

                System.out.println("Inserted user into index" + index);
                customer_infos[index] = customer_info;
                index++;
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning customer information to user.");
        return Arrays.asList(customer_infos);
    }

    @Override
    public Customer_info findById(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from Customer Info where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Customer_info customer_info = new Customer_info();

            customer_info.setFirst_name(rs.getString("first_name"));
            customer_info.setLast_name(rs.getString("last_name"));
            customer_info.setEmail(rs.getString("email"));
            customer_info.setPassword(rs.getString("usePassword"));
            customer_info.setDate_of_birth(rs.getString("date_of_birth"));

            return customer_info;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Customer_info updateObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public Customer_info authenticateCustomer_info(String email, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from trainer where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Customer_info customer_info = new Customer_info();

            customer_info.setFirst_name(rs.getString("first_name"));
            customer_info.setLast_name(rs.getString("last_name"));
            customer_info.setEmail(rs.getString("email"));
            customer_info.setPassword(rs.getString("password"));
            customer_info.setDate_of_birth(rs.getString("date_of_birth"));

            return customer_info;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
        public boolean checkEmail (String email){

            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                String sql = "select email from user where email = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();

                if (!rs.isBeforeFirst()) {
                    return false;
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }


        }
    }

