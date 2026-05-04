package controller;

import jakarta.servlet.http.*;
import java.io.*;
import dao.MemberDao;

public class DeleteMemberServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                MemberDao dao = new MemberDao();
                dao.deleteMember(id);
            }
            response.sendRedirect("ViewMembersServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewMembersServlet");
        }
    }
}
