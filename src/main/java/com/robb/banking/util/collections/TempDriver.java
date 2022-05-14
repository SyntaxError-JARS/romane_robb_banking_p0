package com.robb.banking.util.collections;

import com.robb.banking.models.Customer_info;

public class TempDriver {
    public static void main(String[] args) {
        List<Customer_info> customer_infosLL = new LinkedList<>();

        Customer_info customer_info = new Customer_info("Michael", "Lee", "ml@mail.com", "password", "01-01-1901");
        Customer_info customer_info2 = new Customer_info("Jessica", "Lee", "Jl@mail.com", "password1", "01-01-1902");
        Customer_info customer_info3 = new Customer_info();
        System.out.println(customer_infosLL.add(customer_info));
        System.out.println(customer_infosLL.add(customer_info));
        System.out.println(customer_infosLL.add(customer_info2));
        System.out.println(customer_infosLL.add(customer_info));
        System.out.println(customer_infosLL.add(customer_info));
        System.out.println(customer_infosLL.add(customer_info));
        System.out.println(customer_infosLL.size());

        System.out.println(customer_infosLL.contains(customer_info));
        System.out.println(customer_infosLL.remove(customer_info3));
        System.out.println(customer_infosLL.size());
        System.out.println(customer_infosLL.get(2));

        List<String> stringLL = new LinkedList<>();
        stringLL.add("Hello there");
        stringLL.add("Ahhhh");
        stringLL.add("General");
        stringLL.add("Kenobi");

        System.out.println(stringLL.contains("General"));
        System.out.println(stringLL.get(2));

        List<Integer> integerList = new LinkedList<>();
        integerList.add(1);
    }
}
