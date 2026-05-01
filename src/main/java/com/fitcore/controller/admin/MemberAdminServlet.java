package com.fitcore.controller.admin;

import com.fitcore.dao.MemberDAO;
import com.fitcore.model.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/members")
public class MemberAdminServlet extends HttpServlet {

    private final MemberDAO memberDAO = new MemberDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String q = req.getParameter("q");
            req.setAttribute("members",
                    (q == null || q.isEmpty()) ? memberDAO.findAll() : memberDAO.search(q));
            req.setAttribute("q", q);
            req.getRequestDispatcher("/WEB-INF/views/admin/members.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("update".equals(action)) {
                Member m = memberDAO.findById(Integer.parseInt(req.getParameter("memberId")));
                if (m != null) {
                    m.setName(req.getParameter("name"));
                    m.setContact(req.getParameter("contact"));
                    m.setEmail(req.getParameter("email"));
                    m.setAddress(req.getParameter("address"));
                    m.setMembershipType(req.getParameter("membershipType"));
                    m.setStatus(req.getParameter("status"));
                    memberDAO.update(m);
                }
            } else if ("delete".equals(action)) {
                memberDAO.delete(Integer.parseInt(req.getParameter("memberId")));
            }
            res.sendRedirect(req.getContextPath() + "/admin/members");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
