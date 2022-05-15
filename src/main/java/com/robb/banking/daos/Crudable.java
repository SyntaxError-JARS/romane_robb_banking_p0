package com.robb.banking.daos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// This is another form of abstraction
public interface Crudable<T> {

    // public final int age = 16; we call a constant variable because by default it's final and cannot changed

    // Create
    T create(T newObject);

    // Read
    List<T> findAll() throws IOException;
    T findById(String id);

    // Update
    public boolean update(T updatedObj);

    //Delete
    boolean delete(String id);

}