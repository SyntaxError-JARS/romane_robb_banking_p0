package com.robb.banking.services;

public interface Serviceable <T>{

    T create(T newObject);

    T[] readAll();
    T readById(String id);

    T update(T updateObject);

    boolean delete(String id);

    boolean validateInput(T object);
}
