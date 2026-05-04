package controller;

import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import util.DBUtil;

/**
 * Servlet for handling user login.
 * Authenticates users and redirects based on their role.
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles POST requests for user login.
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve login credentials from request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get database connection
            conn = DBUtil.getConnection();
            // Query to check user credentials and get role
            String sql = "SELECT role FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // User found, get role
                String role = rs.getString("role");
                // Create session and set attributes
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                // Redirect based on role
                if ("admin".equals(role)) {
                    response.sendRedirect("adminDashboard.jsp");
                } else if ("member".equals(role)) {
                    response.sendRedirect("memberDashboard.jsp");
                } else {
                    // Invalid role
                    response.sendRedirect("login.jsp?error=true");
                }
            } else {
                // Invalid credentials
                response.sendRedirect("login.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=true");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
