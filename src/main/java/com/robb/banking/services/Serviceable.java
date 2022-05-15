package com.robb.banking.services;

import com.robb.banking.models.Account_info;
import com.robb.banking.models.Customer_info;

import java.util.List;

public interface Serviceable<T> {

    T create(T newObject);

    List<T> readAll();
    T readById(String id);

    T update(T updateObject);

    boolean delete(String id);

    boolean validateInput(T object);

    Account_info readbyID(String id);
}
