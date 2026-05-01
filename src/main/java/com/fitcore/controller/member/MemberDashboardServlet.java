package com.fitcore.controller.member;

import com.fitcore.dao.*;
import com.fitcore.model.Member;
import com.fitcore.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/dashboard")
public class MemberDashboardServlet extends HttpServlet {

    private final AttendanceDAO attendanceDAO = new AttendanceDAO();
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final SessionDAO sessionDAO = new SessionDAO();
    private final AnnouncementDAO announcementDAO = new AnnouncementDAO();
    private final PlanDAO planDAO = new PlanDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);
        if (m == null) { res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }
        try {
            req.setAttribute("monthlyVisits", attendanceDAO.countMonthlyVisits(m.getMemberId()));
            req.setAttribute("payments",      paymentDAO.findByMember(m.getMemberId()));
            req.setAttribute("bookings",      sessionDAO.findBookedByMember(m.getMemberId()));
            req.setAttribute("announcements", announcementDAO.findRecent(5));
            if (m.getPlanId() != null) req.setAttribute("plan", planDAO.findById(m.getPlanId()));
            req.getRequestDispatcher("/WEB-INF/views/member/dashboard.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
