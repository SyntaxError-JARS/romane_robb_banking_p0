package com.robb.banking.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Authable {

    static boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authCustomer_info") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered user");
            resp.setStatus(401);
            return false;
        }
        return true;
    }
}
