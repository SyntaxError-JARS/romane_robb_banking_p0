package com.robb.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.models.Account_info;
import com.robb.banking.services.Account_infoServices;
import java.io.IOException;

import com.robb.banking.exceptions.AuthenticationException;
import com.robb.banking.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import com.robb.banking.util.logging.Logger;


public class Account_infoServlet extends HttpServlet implements Authable {

    private final Account_infoServices account_infoServices;
    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();

    public Account_infoServlet(Account_infoServices account_infoServices, ObjectMapper mapper) {
        this.account_infoServices = account_infoServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;

        // TODO: Let's create an account.

        Account_info newAccount_info = mapper.readValue(req.getInputStream(), Account_info.class); // from JSON to Java Object (Account)
        Account_info persistedAccount = account_infoServices.create(newAccount_info);

        String payload = mapper.writeValueAsString(persistedAccount); // Mapping from Java Object (Account) to JSON
        resp.getWriter().write("Persisted the provided account as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Authable.checkAuth(req, resp)) return;

        // TODO: Let's read an account.

        if (req.getParameter("id") != null) {
            Account_info account_info;
            try {
                account_info = account_infoServices.findbyId(req.getParameter("id"));
                resp.getWriter().write("This account matches the id number provided: \n");
            } catch (ResourcePersistanceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(account_info);
            resp.getWriter().write(payload);
            return;
        }

        List<Account_info> account_infos = account_infoServices.readAll();
        String payload = mapper.writeValueAsString(account_infos);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Authable.checkAuth(req, resp)) return;

        // TODO: Let's update an account.

        Account_infoServices account_infoUpdate = mapper.readValue(req.getInputStream(), Account_infoServices.class);
        Account_info updatedAccount_info = account_infoServices.update(account_infoUpdate);

        String payload = mapper.writeValueAsString(account_infoUpdate);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp))   return;

        // TODO: Let's delete an account.

        Account_info account_info = mapper.readValue(req.getInputStream(), Account_info.class);
        if ( !account_infoServices.delete(account_info.getAccount_number()) ){
            resp.getWriter().write("Fail to delete account.");
            resp.setStatus(401); // Unauthorized
            return;
        }

        String payload = mapper.writeValueAsString(account_info);
        resp.getWriter().write(payload);
        //resp.getWriter().write("<h1>/Account was deleted!!</h1>");
        resp.setStatus(200); //success
    }



        protected boolean checkAuth (HttpServletRequest req, HttpServletResponse resp) throws IOException {
            HttpSession httpSession = req.getSession();
            if (httpSession.getAttribute("authUser") == null) {
                resp.getWriter().write("Unauthorized request - not logged in as register user ");
                resp.setStatus(401);
                return false;
            }
            return true;
        }

    }