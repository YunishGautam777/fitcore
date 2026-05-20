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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            loadMembers(request);
            request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            switch (action) {
                case "update":
                    updateMember(request);
                    break;

                case "delete":
                    deleteMember(request);
                    break;

                default:
                    break;
            }

            response.sendRedirect(request.getContextPath() + "/admin/members");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void loadMembers(HttpServletRequest request) throws SQLException {

        String query = request.getParameter("q");

        if (query == null || query.trim().isEmpty()) {
            request.setAttribute("members", memberDAO.findAll());
        } else {
            request.setAttribute("members", memberDAO.search(query));
        }

        request.setAttribute("q", query);
    }

    private void updateMember(HttpServletRequest request) throws SQLException {

        int memberId = Integer.parseInt(request.getParameter("memberId"));
        Member member = memberDAO.findById(memberId);

        if (member == null) {
            return;
        }

        member.setName(request.getParameter("name"));
        member.setContact(request.getParameter("contact"));
        member.setEmail(request.getParameter("email"));
        member.setAddress(request.getParameter("address"));
        member.setMembershipType(request.getParameter("membershipType"));
        member.setStatus(request.getParameter("status"));

        memberDAO.update(member);
    }

    private void deleteMember(HttpServletRequest request) throws SQLException {

        int memberId = Integer.parseInt(request.getParameter("memberId"));
        memberDAO.delete(memberId);
    }
}