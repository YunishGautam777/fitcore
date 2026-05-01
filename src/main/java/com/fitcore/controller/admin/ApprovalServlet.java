package com.fitcore.controller.admin;

import com.fitcore.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/approvals")
public class ApprovalServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("pending", userDAO.findPending());
            req.getRequestDispatcher("/WEB-INF/views/admin/approvals.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String action = req.getParameter("action");
            userDAO.updateStatus(userId, "approve".equals(action) ? "ACTIVE" : "INACTIVE");
            res.sendRedirect(req.getContextPath() + "/admin/approvals");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
