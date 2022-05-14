package com.robb.banking.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robb.banking.models.Account_info;
import com.robb.banking.services.Account_infoServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        if (req.getParameter("id") != null) {
            Account_info account_info = account_infoServices.readbyID(req.getParameter("id"));
            String payload = mapper.writeValueAsString(account_info);
            resp.getWriter().write(payload);
            return;
        }

        List<Account_info> account_infos = account_infoServices.readAll();
        String payload = mapper.writeValueAsString(account_infos);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!checkAuth.checkAuth(req, resp)) return;
        Account_info newAccount_info = mapper.readValue(req.getInputStream(), Account_info.class);
        Account_info persistedAccount_info = account_infoServices.create(newAccount_info);

        String payload = mapper.writeValueAsString(persistedAccount_info);

        resp.getWriter().write("Persisted the provided account_info as shown below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
