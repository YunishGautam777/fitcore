package com.fitcore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Prevents the browser from caching authenticated pages so back-button after
 * logout cannot reveal stale dashboards.
 */
@WebFilter(urlPatterns = {"/admin/*", "/member/*"})
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpRes = (HttpServletResponse) res;
        httpRes.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        httpRes.setHeader("Pragma", "no-cache");
        httpRes.setDateHeader("Expires", 0);
        chain.doFilter(req, res);
    }
}
