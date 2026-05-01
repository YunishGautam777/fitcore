package com.fitcore.controller.member;

import com.fitcore.model.Member;
import com.fitcore.service.BookingService;
import com.fitcore.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/book")
public class BookSessionServlet extends HttpServlet {

    private final BookingService bookingService = new BookingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);
        if (m == null) { res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }
        try {
            int sessionId = Integer.parseInt(req.getParameter("sessionId"));
            BookingService.BookingResult r = bookingService.book(m.getMemberId(), sessionId);
            res.sendRedirect(req.getContextPath() + "/member/sessions?status=" + r.name());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
