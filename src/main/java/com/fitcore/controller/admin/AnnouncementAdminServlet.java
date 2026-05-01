package com.fitcore.controller.admin;

import com.fitcore.dao.AnnouncementDAO;
import com.fitcore.model.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/announcements")
public class AnnouncementAdminServlet extends HttpServlet {

    private final AnnouncementDAO dao = new AnnouncementDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            req.setAttribute("announcements", dao.findRecent(50));
            req.getRequestDispatcher("/WEB-INF/views/admin/announcements.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("create".equals(action)) {
                Announcement a = new Announcement();
                a.setTitle(req.getParameter("title"));
                a.setBody(req.getParameter("body"));
                a.setCategory(req.getParameter("category"));
                a.setPinned("on".equals(req.getParameter("pinned")));
                dao.insert(a);
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("announcementId")));
            }
            res.sendRedirect(req.getContextPath() + "/admin/announcements");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
