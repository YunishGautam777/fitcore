package com.fitcore.controller.admin;

import com.fitcore.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
//DAO objects
    private final MemberDAO memberDAO = new MemberDAO();
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final UserDAO userDAO = new UserDAO();
    private final AnnouncementDAO announcementDAO = new AnnouncementDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("activeMembers",   memberDAO.countByStatus("ACTIVE"));
            req.setAttribute("inactiveMembers", memberDAO.countByStatus("INACTIVE"));
            req.setAttribute("monthlyRevenue",  paymentDAO.monthlyRevenue());
            req.setAttribute("pendingUsers",    userDAO.findPending());
            req.setAttribute("announcements",   announcementDAO.findRecent(5));
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
