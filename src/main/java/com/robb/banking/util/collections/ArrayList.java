package com.robb.banking.util.collections;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;

@JsonSerialize(using = ArrayListSerializer.class)
public class ArrayList<T> implements List<T> {

    protected int size;
    protected Object[] elements;
    public ArrayList() { elements = new Object[16]; }

    public ArrayList(int initialCapacity) { elements = new Object[initialCapacity]; }

    @Override
    public boolean add(T element) {
        resizeBackingArrayIfNeeded();
        return true;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() { return size; }

    @Override
    public T get(int index) {
        return null;
    }

    protected void resizeBackingArrayIfNeeded() {

    }

    @Override
    public String toString() { return Arrays.toString(elements); }
}
