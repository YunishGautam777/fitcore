<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/15/2026
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Member" %>
<%
    String email = (String) session.getAttribute("email");
    String role = (String) session.getAttribute("role");
    if (email == null || !"admin".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Member> searchResults = (List<Member>) request.getAttribute("members");
    String keyword = request.getParameter("keyword");
%>
<html>
<head>
    <title>Search Member - Gym Management System</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }
        .container {
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        .search-form {
            margin-bottom: 30px;
            display: flex;
            gap: 10px;
        }
        .search-form input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        .search-form button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-form button:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th {
            background-color: #333;
            color: white;
            padding: 12px;
            text-align: left;
            border-bottom: 2px solid #333;
        }
        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        .edit-btn, .delete-btn {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            color: white;
        }
        .edit-btn {
            background-color: #2196F3;
        }
        .edit-btn:hover {
            background-color: #0b7dda;
        }
        .delete-btn {
            background-color: #f44336;
        }
        .delete-btn:hover {
            background-color: #da190b;
        }
        .back-link {
            text-align: center;
            margin-top: 15px;
        }
        .back-link a {
            color: #333;
            text-decoration: none;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
        .no-results {
            text-align: center;
            padding: 20px;
            color: #666;
        }
        .search-info {
            margin-bottom: 20px;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>Gym Management System</h2>
    </div>
    <div class="container">
        <h1>Search Members</h1>
        <form method="GET" action="SearchMemberServlet" class="search-form">
            <input type="text" name="keyword" placeholder="Search by name or email..."
                   value="<%= keyword != null ? keyword : "" %>">
            <button type="submit">Search</button>
        </form>

        <% if (keyword != null && !keyword.isEmpty()) { %>
            <div class="search-info">
                <p>Search results for: <strong><%= keyword %></strong></p>
            </div>

            <% if (searchResults != null && searchResults.size() > 0) { %>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Plan</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Member m : searchResults) { %>
                            <tr>
                                <td><%= m.getId() %></td>
                                <td><%= m.getName() %></td>
                                <td><%= m.getEmail() %></td>
                                <td><%= m.getPhone() %></td>
                                <td><%= m.getPlan() %></td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="editMember.jsp?id=<%= m.getId() %>" class="edit-btn">Edit</a>
                                        <a href="DeleteMemberServlet?id=<%= m.getId() %>" class="delete-btn" onclick="return confirm('Are you sure?');">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <div class="no-results">
                    <p>No members found matching "<%= keyword %>".</p>
                </div>
            <% } %>
        <% } else { %>
            <div class="no-results">
                <p>Enter a search keyword to find members by name or email.</p>
            </div>
        <% } %>

        <div class="back-link">
            <a href="adminDashboard.jsp">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
