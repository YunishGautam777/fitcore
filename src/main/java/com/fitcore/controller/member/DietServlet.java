package com.fitcore.controller.member;

import com.fitcore.dao.DietPlanDAO;
import com.fitcore.model.Member;
import com.fitcore.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/diet")
public class DietServlet extends HttpServlet {

    private final DietPlanDAO dao = new DietPlanDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Member m = (Member) req.getSession().getAttribute(Constants.SESSION_MEMBER);
        if (m == null) { res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }
        try {
            req.setAttribute("diets", dao.findByMember(m.getMemberId()));
            req.getRequestDispatcher("/WEB-INF/views/member/diet.jsp").forward(req, res);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
