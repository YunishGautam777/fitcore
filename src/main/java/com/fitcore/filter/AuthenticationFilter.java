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
 * Blocks any request to /admin/* or /member/* that does not have a logged-in user.
 */
@WebFilter(urlPatterns = {"/admin/*", "/member/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;
        HttpSession session = httpReq.getSession(false);

        User user = session != null ? (User) session.getAttribute(Constants.SESSION_USER) : null;
        if (user == null) {
            httpRes.sendRedirect(httpReq.getContextPath() + "/login.jsp?redirect=auth");
            return;
        }
        chain.doFilter(req, res);
    }
}
