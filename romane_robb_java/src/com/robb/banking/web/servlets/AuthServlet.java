package com.robb.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Customer_info;
import com.robb.banking.services.UserServices;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final UserServices userServices;

    private final ObjectMapper mapper;

    public AuthServlet(UserServices userServices, ObjectMapper mapper){
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            LoginCreds loginCreds = mapper.readvalue(req.getInputStream(), LoginCreds.class);

            Customer_info authCustomer_info = userServices.authenticateCustomer_info(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authTrainer", authCustomer_info);
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }

    }

}
