package com.robb.banking.services;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.models.Customer_info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class Customer_infoServiceTestSuite {

    Customer_infoServices sut;
    Customer_infoDao mockCustomer_infoDoa;

    @BeforeEach
    public void testPrep(){
        mockCustomer_infoDoa = mock(Customer_infoDao.class);
        sut = new Customer_infoServices(mockCustomer_infoDoa);
    }

    @Test
    public void test_validInput_givenValidUser_returnTrue(){

        Customer_info customer_info = new Customer_info("valid", "valid", "valid", "valid", "valid");
        when(mockCustomer_infoDoa.create(customer_info)).thenReturn(customer_info);

        boolean actualResult = sut.validateInput(customer_info);

        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsCustomer_info(){

        Customer_info customer_info = new Customer_info("valid", "valid", "valid", "valid", "valid");

        Customer_info actualCustomer_info = sut.create(customer_info);

        Assertions.assertEquals("valid", actualCustomer_info.getFirst_name());
        Assertions.assertEquals("valid", actualCustomer_info.getLast_name());
        Assertions.assertEquals("valid", actualCustomer_info.getEmail());
        Assertions.assertEquals("valid", actualCustomer_info.getPassword());
        Assertions.assertEquals("valid", actualCustomer_info.getDate_of_birth());
        verify(mockCustomer_infoDoa, times(1)).create(customer_info);

    }

    @Test
    @Disabled
    public void test3(){

    }

}
