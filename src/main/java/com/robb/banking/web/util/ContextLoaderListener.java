// This is used to group all the related classes. It is like a folder in a file directory.
package com.robb.banking.web.util;

// Import tells the compiler the path of the class or the entire package.
// We import classes in Java to be used below the import statement.
import com.robb.banking.daos.Account_infoDao;
import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.services.Customer_infoServices;
import com.robb.banking.services.Account_infoServices;
import com.robb.banking.web.servlets.AuthServlet;
import com.robb.banking.web.servlets.Customer_infoServlet;
import com.robb.banking.web.servlets.Account_infoServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// WebListener is used to register a class as a listener of a web application.
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    // Override is
    // We use Override when a subclass (child class) has the same method as the parent class.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();

        Customer_infoDao customer_infoDao = new Customer_infoDao();
        Account_infoDao account_infoDao = new Account_infoDao();

        Customer_infoServices customer_infoServices = new Customer_infoServices(customer_infoDao);
        Account_infoServices account_infoServices = new Account_infoServices(account_infoDao);

        AuthServlet authServlet = new AuthServlet(customer_infoServices, mapper);
        Customer_infoServlet customer_infoServlet = new Customer_infoServlet(customer_infoServices, mapper);
        Account_infoServlet account_infoServlet = new Account_infoServlet(account_infoServices, mapper);


        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("Customer_infoServlet", customer_infoServlet).addMapping("/users/*");
        context.addServlet("Account_infoServlet", account_infoServlet).addMapping("/account/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { ServletContextListener.super.contextDestroyed(sce); }
}
