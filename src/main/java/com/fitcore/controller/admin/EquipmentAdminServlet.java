package com.fitcore.controller.admin;

import com.fitcore.dao.EquipmentDAO;
import com.fitcore.model.Equipment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/admin/equipment")
public class EquipmentAdminServlet extends HttpServlet {

    private final EquipmentDAO dao = new EquipmentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("equipment", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/equipment.jsp").forward(req, res);
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
                Equipment e = new Equipment();
                e.setName(req.getParameter("name"));
                e.setCategory(req.getParameter("category"));
                e.setCondition(req.getParameter("condition"));
                e.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                e.setPurchaseDate(Date.valueOf(LocalDate.now()));
                dao.insert(e);
            } else if ("update".equals(action)) {
                Equipment e = new Equipment();
                e.setEquipmentId(Integer.parseInt(req.getParameter("equipmentId")));
                e.setName(req.getParameter("name"));
                e.setCategory(req.getParameter("category"));
                e.setCondition(req.getParameter("condition"));
                e.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                dao.update(e);
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("equipmentId")));
            }
            res.sendRedirect(req.getContextPath() + "/admin/equipment");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
