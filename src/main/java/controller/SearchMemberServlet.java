package controller;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.util.List;
import dao.MemberDao;
import model.Member;

public class SearchMemberServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        try {
            MemberDao dao = new MemberDao();
            List<Member> members = dao.searchMembers(keyword);
            request.setAttribute("members", members);
            request.getRequestDispatcher("jsp/searchMember.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/adminDashboard.jsp");
        }
    }
}
