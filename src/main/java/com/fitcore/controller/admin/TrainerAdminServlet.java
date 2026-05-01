package com.fitcore.controller.admin;

import com.fitcore.dao.TrainerDAO;
import com.fitcore.model.Trainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/trainers")
public class TrainerAdminServlet extends HttpServlet {

    private final TrainerDAO dao = new TrainerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("trainers", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/trainers.jsp").forward(req, res);
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
                Trainer t = bind(req, new Trainer());
                dao.insert(t);
            } else if ("update".equals(action)) {
                Trainer t = bind(req, new Trainer());
                t.setTrainerId(Integer.parseInt(req.getParameter("trainerId")));
                dao.update(t);
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("trainerId")));
            }
            res.sendRedirect(req.getContextPath() + "/admin/trainers");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private Trainer bind(HttpServletRequest req, Trainer t) {
        t.setName(req.getParameter("name"));
        t.setSpecialization(req.getParameter("specialization"));
        try { t.setExperience(Integer.parseInt(req.getParameter("experience"))); } catch (Exception ignore) {}
        t.setContact(req.getParameter("contact"));
        t.setAssignedShift(req.getParameter("assignedShift"));
        return t;
    }
}
