package com.fitcore.controller.member;

import com.fitcore.model.Member;
import com.fitcore.service.BookingService;
import com.fitcore.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Handles session booking requests for authenticated members.
 * Maps to POST /member/book endpoint.
 */
@WebServlet("/member/book")
public class BookSessionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BookingService bookingService = new BookingService();

    /**
     * Processes the booking request.
     * Redirects to login if the member session is not found.
     * Redirects to sessions page with booking result status on success.
     *
     * @param req  the HttpServletRequest object
     * @param res  the HttpServletResponse object
     * @throws ServletException if a database error occurs
     * @throws IOException      if a redirect fails
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);

        if (m == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int sessionId = Integer.parseInt(req.getParameter("sessionId"));
            BookingService.BookingResult r = bookingService.book(m.getMemberId(), sessionId);
            res.sendRedirect(req.getContextPath() + "/member/sessions?status=" + r.name());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
