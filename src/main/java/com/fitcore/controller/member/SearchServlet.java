package com.fitcore.controller.member;

import com.fitcore.dao.SessionDAO;
import com.fitcore.dao.TrainerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/search")
public class SearchServlet extends HttpServlet {

    private final TrainerDAO trainerDAO = new TrainerDAO();
    private final SessionDAO sessionDAO = new SessionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String q = req.getParameter("q");
        try {
            if (q != null && !q.isEmpty()) {
                req.setAttribute("trainers", trainerDAO.searchBySpecialization(q));
                req.setAttribute("sessions", sessionDAO.search(q));
            }
            req.setAttribute("q", q);
            req.getRequestDispatcher("/WEB-INF/views/member/search.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
