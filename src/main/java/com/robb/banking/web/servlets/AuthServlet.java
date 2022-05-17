package com.robb.banking.web.servlets;

import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.models.Customer_info;
import com.robb.banking.services.Customer_infoServices;
import com.robb.banking.web.dto.LoginCreds;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.exceptions.AuthenticationException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final Customer_infoServices customer_infoServices;

    private final ObjectMapper mapper;

    public AuthServlet(Customer_infoServices customer_infoServices, ObjectMapper mapper){
        this.customer_infoServices = customer_infoServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            Customer_info authCustomer_info = customer_infoServices.authenticateCustomer_info(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authCustomer", authCustomer_info);

            resp.setStatus(200);

            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }

    }

}
