package com.fitcore.controller.admin;

import com.fitcore.dao.MemberDAO;
import com.fitcore.dao.PaymentDAO;
import com.fitcore.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {

    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("monthlyRevenue", paymentDAO.monthlyRevenue());
            req.setAttribute("activeMembers",   memberDAO.countByStatus("ACTIVE"));
            req.setAttribute("inactiveMembers", memberDAO.countByStatus("INACTIVE"));
            req.setAttribute("popularClasses",  popularClasses());
            req.setAttribute("equipmentMaint",  equipmentMaintenanceCounts());
            req.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private Map<String, Integer> popularClasses() throws SQLException {
        Map<String, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT s.name, COUNT(b.booking_id) AS cnt FROM WorkoutSessions s " +
                "LEFT JOIN SessionBookings b ON s.session_id = b.session_id " +
                "GROUP BY s.session_id, s.name ORDER BY cnt DESC LIMIT 10";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) map.put(rs.getString("name"), rs.getInt("cnt"));
        }
        return map;
    }

    private Map<String, Integer> equipmentMaintenanceCounts() throws SQLException {
        Map<String, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT e.name, COUNT(l.log_id) AS cnt FROM Equipment e " +
                "LEFT JOIN MaintenanceLogs l ON e.equipment_id = l.equipment_id " +
                "GROUP BY e.equipment_id, e.name ORDER BY cnt DESC";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) map.put(rs.getString("name"), rs.getInt("cnt"));
        }
        return map;
    }
}
