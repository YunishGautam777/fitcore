package com.fitcore.controller.admin;

import com.fitcore.dao.MemberDAO;
import com.fitcore.dao.PaymentDAO;
import com.fitcore.dao.PlanDAO;
import com.fitcore.service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/payments")
public class PaymentAdminServlet extends HttpServlet {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private final PlanDAO planDAO = new PlanDAO();
    private final PaymentService paymentService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("payments", paymentDAO.findAllWithMemberName());
            req.setAttribute("members", memberDAO.findAll());
            req.setAttribute("plans", planDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/payments.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("record".equals(action)) {
                int memberId = Integer.parseInt(req.getParameter("memberId"));
                int planId = Integer.parseInt(req.getParameter("planId"));
                String method = req.getParameter("method");
                paymentService.recordPaymentAndExtendPlan(memberId, planId, method);
            } else if ("status".equals(action)) {
                paymentDAO.updateStatus(
                    Integer.parseInt(req.getParameter("paymentId")),
                    req.getParameter("status"));
            }
            res.sendRedirect(req.getContextPath() + "/admin/payments");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
