package com.robb.banking.web.servlets;

import com.robb.banking.exceptions.InvalidRequestException;
import com.robb.banking.exceptions.ResourcePersistanceException;
import com.robb.banking.models.Customer_info;
import com.robb.banking.services.Customer_infoServices;
import com.robb.banking.daos.Customer_infoDao;
import com.robb.banking.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.robb.banking.util.logging.Logger;

import static com.robb.banking.web.servlets.Authable.checkAuth;

public class Customer_infoServlet extends HttpServlet implements Authable {

    private final Customer_infoServices customer_infoServices;

    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();

    public Customer_infoServlet(Customer_infoServices customer_infoServices, ObjectMapper mapper) {
        this.customer_infoServices = customer_infoServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!checkAuth(req, resp)) return;

//        if (req.getParameter("email") != null && req.getParameter("email") != null) {
//            resp.getWriter().write("You have the following email " + req.getParameter("email"));
//            return;
//        }

        if (req.getParameter("email") != null) {
            Customer_info customer_info;
            try {
                customer_info = customer_infoServices.readByEmail(req.getParameter("email"));
                resp.getWriter().write("This customer matches the email address provided: \n");
            } catch (ResourcePersistanceException e) {
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(customer_info);
            resp.getWriter().write(payload);
            return;
        }

        List<Customer_info> customer_infos = customer_infoServices.readAll();

        String payload = mapper.writeValueAsString(customer_infos);


        resp.getWriter().write(payload);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Let's create a User
        Customer_info newCustomerinfo = mapper.readValue(req.getInputStream(), Customer_info.class); // from JSON to Java Object (User)
        Customer_info persistedUser = customer_infoServices.create(newCustomerinfo);

        String payload = mapper.writeValueAsString(persistedUser); // Mapping from Java Object (User) to JSON

        resp.getWriter().write("Persisted the provided user as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authCustomer_info") == null) {
            resp.getWriter().write("Unauthorized request. You are not logged in as a registered user.");
            resp.setStatus(401);
            return false;
        }
        return true;


    }
}