package com.robb.banking.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().write("<h1>This Test Servlet is working as intended for Robb's Banking Application.</h1>");

   }

}
