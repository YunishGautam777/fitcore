package com.fitcore.controller;

import com.fitcore.model.Member;
import com.fitcore.service.AuthService;
import com.fitcore.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name     = req.getParameter("name");
        String email    = req.getParameter("email");

        if (!ValidationUtil.notBlank(username) || !ValidationUtil.validPassword(password) ||
            !ValidationUtil.notBlank(name) || !ValidationUtil.isEmail(email)) {
            req.setAttribute("error", "Please fill all fields correctly. Password must be 6+ chars.");
            req.getRequestDispatcher("/register.jsp").forward(req, res);
            return;
        }

        Member m = new Member();
        m.setName(name);
        m.setEmail(email);
        m.setContact(req.getParameter("contact"));
        m.setGender(req.getParameter("gender"));
        m.setAddress(req.getParameter("address"));
        m.setMembershipType(req.getParameter("membership_type"));
        m.setFitnessGoal(req.getParameter("fitness_goal"));
        String dob = req.getParameter("dob");
        if (ValidationUtil.notBlank(dob)) m.setDob(Date.valueOf(dob));

        try {
            boolean ok = authService.register(m, username, password);
            if (ok) {
                res.sendRedirect(req.getContextPath() + "/login.jsp?registered=1");
            } else {
                req.setAttribute("error", "Registration failed. Try a different username.");
                req.getRequestDispatcher("/register.jsp").forward(req, res);
            }
        } catch (SQLException e) {
            req.setAttribute("error", "Registration error: " + e.getMessage());
            req.getRequestDispatcher("/register.jsp").forward(req, res);
        }
    }
}
