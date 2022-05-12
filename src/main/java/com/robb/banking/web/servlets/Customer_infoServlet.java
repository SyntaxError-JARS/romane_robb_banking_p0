package com.robb.banking.web.servlets;

import com.robb.banking.models.Customer_info;
import com.robb.banking.services.Customer_infoServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Customer_infoServlet extends HttpServlet {

    private final Customer_infoServices customer_infoServices;

    private final ObjectMapper mapper;

    public Customer_infoServlet(Customer_infoServices customer_infoServices, ObjectMapper mapper) {
        this.customer_infoServices = customer_infoServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer_info[] customer_infos = customer_infoServices.readAll();

        String payload = mapper.writeValueAsString(customer_infos);

        resp.getWriter().write(payload);
    }

}
