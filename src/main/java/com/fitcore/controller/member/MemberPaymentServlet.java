package com.fitcore.controller.member;

import com.fitcore.dao.PaymentDAO;
import com.fitcore.dao.PlanDAO;
import com.fitcore.model.Member;
import com.fitcore.model.Payment;
import com.fitcore.util.Constants;
import com.fitcore.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/member/payments")
public class MemberPaymentServlet extends HttpServlet {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final PlanDAO planDAO = new PlanDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);
        if (m == null) { res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }
        try {
            req.setAttribute("payments", paymentDAO.findByMember(m.getMemberId()));
            req.setAttribute("plans", planDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/member/payments.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    /** Member submits a renewal request — creates a PENDING payment for admin to verify. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);
        if (m == null) { res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }
        try {
            int planId = Integer.parseInt(req.getParameter("planId"));
            Payment p = new Payment();
            p.setMemberId(m.getMemberId());
            p.setPlanId(planId);
            p.setAmount(planDAO.findById(planId).getPrice());
            p.setDate(Date.valueOf(LocalDate.now()));
            p.setStatus("PENDING");
            p.setPaymentMethod(req.getParameter("method"));
            try (Connection con = DBUtil.getConnection()) {
                paymentDAO.insert(p, con);
            }
            res.sendRedirect(req.getContextPath() + "/member/payments?submitted=1");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
