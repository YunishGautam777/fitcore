package com.fitcore.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        terminateUserSession(request);
        redirectToLoginPage(request, response);
    }

    private void terminateUserSession(HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        boolean sessionExists = (currentSession != null);

        if (sessionExists) {
            currentSession.invalidate();
        }
    }

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String contextPath = request.getContextPath();
        String loginUrl = contextPath + "/login.jsp?loggedOut=1";
        response.sendRedirect(loginUrl);
    }
}