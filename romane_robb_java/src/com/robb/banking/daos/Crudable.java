package com.robb.banking.daos;

import java.io.IOException;

public interface Crudable {

    // Create
    T create(T newObject);

    // Read
    T[] findAll() throws IOException;
    T findById(String id);

    // Update
    public boolean update(T updatedObj);

    // Delete

    boolean delete(String id);

}
