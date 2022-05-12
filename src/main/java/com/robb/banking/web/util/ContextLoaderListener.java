package com.robb.banking.web.util;

import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.services.Customer_infoServices;
import com.robb.banking.web.servlets.AuthServlet;
import com.robb.banking.web.servlets.Customer_infoServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();
        Customer_infoDao customer_infoDao = new Customer_infoDao();
        Customer_infoServices customer_infoServices = new Customer_infoServices(customer_infoDao);

        AuthServlet authServlet = new AuthServlet(customer_infoServices, mapper);
        Customer_infoServlet customer_infoServlet = new Customer_infoServlet(customer_infoServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("Customer_infoServlet", customer_infoServlet).addMapping("/customer_info/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { ServletContextListener.super.contextDestroyed(sce); }
}
