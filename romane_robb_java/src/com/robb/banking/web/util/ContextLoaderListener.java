package com.robb.banking.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.daos.UserDao;
import com.robb.banking.services.UserServices;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();
        UserDao userDao = new UserDao();
        UserServices userServices = new userServices(userDao);

        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { ServletContextListener.super.contextDestroyed(sce); }
}
