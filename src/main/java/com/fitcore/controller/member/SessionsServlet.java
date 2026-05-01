package com.fitcore.controller.member;

import com.fitcore.dao.SessionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/sessions")
public class SessionsServlet extends HttpServlet {

    private final SessionDAO sessionDAO = new SessionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String q = req.getParameter("q");
            req.setAttribute("sessions",
                    (q == null || q.isEmpty()) ? sessionDAO.findAll() : sessionDAO.search(q));
            req.setAttribute("q", q);
            req.getRequestDispatcher("/WEB-INF/views/member/sessions.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
