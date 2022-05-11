package com.robb.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final UserServices userServices;

    private final ObjectMapper mapper;

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User[] users = userServices.readAll();

        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

}
