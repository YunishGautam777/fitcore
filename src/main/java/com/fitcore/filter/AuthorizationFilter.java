package com.fitcore.filter;

import com.fitcore.model.User;
import com.fitcore.util.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Enforces RBAC: admins cannot hit member URLs and vice versa.
 */
@WebFilter(urlPatterns = {"/admin/*", "/member/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;
        HttpSession session = httpReq.getSession(false);
        User user = session != null ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (user == null) { chain.doFilter(req, res); return; }   // AuthenticationFilter handles this

        String path = httpReq.getRequestURI().substring(httpReq.getContextPath().length());

        if (path.startsWith("/admin") && !Constants.ROLE_ADMIN.equals(user.getRole())) {
            httpRes.sendRedirect(httpReq.getContextPath() + "/error/403.jsp");
            return;
        }
        if (path.startsWith("/member") && !Constants.ROLE_MEMBER.equals(user.getRole())) {
            httpRes.sendRedirect(httpReq.getContextPath() + "/error/403.jsp");
            return;
        }
        chain.doFilter(req, res);
    }
}
