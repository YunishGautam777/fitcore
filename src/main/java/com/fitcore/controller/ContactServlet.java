package com.fitcore.controller;

import com.fitcore.dao.ContactDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/contact-submit")
public class ContactServlet extends HttpServlet {

    private final ContactDAO dao = new ContactDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            dao.insert(
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("subject"),
                req.getParameter("message")
            );
            res.sendRedirect(req.getContextPath() + "/contact.jsp?sent=1");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
