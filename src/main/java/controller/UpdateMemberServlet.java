package controller;

import jakarta.servlet.http.*;
import java.io.*;
import dao.MemberDao;
import model.Member;

public class UpdateMemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String plan = request.getParameter("plan");

            Member m = new Member(id, name, email, phone, plan);
            MemberDao dao = new MemberDao();
            dao.updateMember(m);
            response.sendRedirect("ViewMembersServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewMembers.jsp?error=true");
        }
    }
}
