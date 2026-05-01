package com.fitcore.controller;

import com.fitcore.dao.MemberDAO;
import com.fitcore.model.Member;
import com.fitcore.model.User;
import com.fitcore.service.AuthService;
import com.fitcore.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final MemberDAO memberDAO = new MemberDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = authService.login(username, password);
            if (user == null) {
                req.setAttribute("error", "Invalid credentials or account not yet approved.");
                req.getRequestDispatcher("/login.jsp").forward(req, res);
                return;
            }
            HttpSession session = req.getSession(true);
            session.setAttribute(Constants.SESSION_USER, user);

            if (Constants.ROLE_MEMBER.equals(user.getRole())) {
                Member m = memberDAO.findByUserId(user.getUserId());
                if (m != null) session.setAttribute(Constants.SESSION_MEMBER, m);
                res.sendRedirect(req.getContextPath() + "/member/dashboard");
            } else {
                res.sendRedirect(req.getContextPath() + "/admin/dashboard");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
