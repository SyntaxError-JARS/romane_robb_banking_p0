package com.robb.banking.services;

import com.robb.banking.daos.UserDao;
import com.robb.banking.models.Customer_info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class UserServiceTestSuite {

    UserServices sut;

    @BeforeEach
    public void testPrep(){
        sut = new UserServices(new UserDao());
    }

    @Test
    public void test_validInput_givenValidUser_returnTrue(){

        Customer_info customer_info = new Customer_info("valid", "valid", "valid", "valid", "valid");

        boolean actualResult = sut.validateInput(customer_info);

        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsCustomer_info(){

        Customer_info customer_info = new Customer_info("valid", "valid", "valid", "valid", "valid");

        Customer_info actualCustomer_info = sut.create(customer_info);

        Assertions.assertEquals("valid", actualCustomer_info.getFirst_name());
        Assertions.assertEquals("valid", actualCustomer_info.getLast_name());
        Assertions.assertEquals("valid", actualCustomer_info.getEmail_address());
        Assertions.assertEquals("valid", actualCustomer_info.getUserpassword());
        Assertions.assertEquals("valid", actualCustomer_info.getDate_of_birth());

    }

    @Test
    @Disabled
    public void test3(){

    }

}
