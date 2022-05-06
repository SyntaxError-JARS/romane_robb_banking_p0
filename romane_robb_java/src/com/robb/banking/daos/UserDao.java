package com.robb.banking.daos;

import com.robb.banking.util.ConnectionFactory;
import com.robb.banking.models.Customer_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements Crudable<Customer_info>{

    @Override
    public Customer_info create(Customer_info newCustomer_info) {
        System.out.println("Here is the newCustomer_info as it enters our DAO layer: " + newCustomer_info);

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into customer_info (fname, lname, email, password, dob) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newCustomer_info.getFname());
            System.out.println(newCustomer_info.getLname());

            ps.setString(1, newCustomer_info.getFname());
            ps.setString(2, newCustomer_info.getLname());
            ps.setString(3, newCustomer_info.getEmail());
            ps.setString(4, newCustomer_info.getPassword());
            ps.setString(5, newCustomer_info.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        return newCustomer_info;

    }

    @Override
    public Customer_info[] findAll() throws IOException {
        Customer_info[] customer_info = new Customer_info[10];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from user";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Customer_info customer_info = new Customer_info();

                Customer_info.setFname(rs.getString("first_name"));
                Customer_info.setLname(rs.getString("last_name"));
                Customer_info.setEmail(rs.getString("email_address"));
                Customer_info.setPassword(rs.getString("userpassword"));
                Customer_info.setDob(rs.getString("date_of_birth"));

                System.out.println("Inserted user into index" + index);
                customer_info[index] = customer_info;
                index++;
                System.out.println("Going to the next line for our following index.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning customer information to user.");
        return customer_info;
    }

    @Override
    public Customer_info findById(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from User where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            Customer_info customer_info = new Customer_info();

            customer_info.setFname(rs.getString("first_name"));
            customer_info.setLname(rs.getString("last_name"));
            customer_info.setEmail(rs.getString("email_address"));
            customer_info.setPassword(rs.getString("userpassword"));
            customer_info.setDob(rs.getString("date_of_birth"));

            return customer_info;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Customer_info updateObj) { return false; }

    @Override
    public boolean delete(String id) { return false; }

    public void checkEmail(String email) {
        String sql = "select email from user where email = " + email;
    }
}
