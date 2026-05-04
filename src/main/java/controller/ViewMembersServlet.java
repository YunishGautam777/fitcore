package controller;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.util.List;
import dao.MemberDao;
import model.Member;

public class ViewMembersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MemberDao dao = new MemberDao();
            List<Member> members = dao.getAllMembers();
            request.setAttribute("members", members);
            request.getRequestDispatcher("viewMembers.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp");
        }
    }
}
