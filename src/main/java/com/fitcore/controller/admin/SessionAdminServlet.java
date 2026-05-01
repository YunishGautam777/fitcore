package com.fitcore.controller.admin;

import com.fitcore.dao.SessionDAO;
import com.fitcore.dao.TrainerDAO;
import com.fitcore.model.WorkoutSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/admin/sessions")
public class SessionAdminServlet extends HttpServlet {

    private final SessionDAO sessionDAO = new SessionDAO();
    private final TrainerDAO trainerDAO = new TrainerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("sessions", sessionDAO.findAll());
            req.setAttribute("trainers", trainerDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/sessions.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("create".equals(action)) {
                WorkoutSession s = new WorkoutSession();
                s.setName(req.getParameter("name"));
                s.setType(req.getParameter("type"));
                s.setCapacity(Integer.parseInt(req.getParameter("capacity")));
                String tid = req.getParameter("trainerId");
                if (tid != null && !tid.isEmpty()) s.setTrainerId(Integer.parseInt(tid));
                s.setSchedule(Timestamp.valueOf(LocalDateTime.parse(req.getParameter("schedule"))));
                sessionDAO.insert(s);
            } else if ("delete".equals(action)) {
                sessionDAO.delete(Integer.parseInt(req.getParameter("sessionId")));
            }
            res.sendRedirect(req.getContextPath() + "/admin/sessions");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
