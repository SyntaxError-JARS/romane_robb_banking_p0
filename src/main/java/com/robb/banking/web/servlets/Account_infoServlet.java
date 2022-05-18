package com.robb.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.models.Account_info;
import com.robb.banking.services.Account_infoServices;

import com.robb.banking.exceptions.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Account_infoServlet extends HttpServlet implements Authable {

    private final Account_infoServices account_infoServices;
    private final ObjectMapper mapper;

    public Account_infoServlet(Account_infoServices account_infoServices, ObjectMapper mapper) {
        this.account_infoServices = account_infoServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Authable.checkAuth(req, resp)) return;

        if(req.getParameter("id") != null){
            Account_info account_info;
            try {
                resp.getWriter().write("This User! \n");
                account_info = account_infoServices.findbyId(req.getParameter("id"));
            }catch (ResourcePersistanceException e){
                //logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(account_info);
            resp.getWriter().write("You have successfully looked up a bank account!\n");
            resp.getWriter().write(payload);
            return;
        }

        ArrayList<Account_info> account_info = account_infoServices.findAll();

        String payload = mapper.writeValueAsString(account_info);

        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Authable.checkAuth(req, resp)) return;
        try {
        Account_info newAccount_info = mapper.readValue(req.getInputStream(), Account_info.class);
        Account_info persistedAccount_info = account_infoServices.create(newAccount_info);

        String payload = mapper.writeValueAsString(persistedAccount_info);

        resp.getWriter().write("Persisted the provided account_info as shown below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
        } catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Authable.checkAuth(req, resp)) return;


        }

        @Override
        protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {


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