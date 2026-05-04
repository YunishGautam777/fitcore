<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/15/2026
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = (String) session.getAttribute("email");
    String role = (String) session.getAttribute("role");
    if (email == null || !"admin".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Admin Dashboard - Gym Management System</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f7fa;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .header {
            background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
            color: white;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .header h2 {
            font-size: 24px;
            margin: 0;
            font-weight: bold;
        }

        .main-wrapper {
            display: flex;
            flex: 1;
        }

        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            color: white;
            padding: 20px 0;
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
        }

        .sidebar h3 {
            padding: 0 20px 15px 20px;
            font-size: 14px;
            color: #bdc3c7;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-top: 20px;
        }

        .sidebar h3:first-child {
            margin-top: 0;
        }

        .nav {
            display: flex;
            flex-direction: column;
            gap: 0;
            list-style: none;
        }

        .nav a {
            display: block;
            padding: 12px 20px;
            color: white;
            text-decoration: none;
            transition: all 0.3s;
            border-left: 4px solid transparent;
            font-size: 14px;
        }

        .nav a:hover {
            background-color: #34495e;
            border-left-color: #3498db;
            padding-left: 24px;
        }

        .nav a.logout {
            color: #e74c3c;
        }

        .nav a.logout:hover {
            background-color: rgba(231, 76, 60, 0.1);
            border-left-color: #e74c3c;
        }

        .content {
            flex: 1;
            padding: 40px;
        }

        .content h1 {
            font-size: 32px;
            color: #2c3e50;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
        }

        .dashboard-card {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            text-decoration: none;
            color: #2c3e50;
            transition: all 0.3s;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            border-top: 4px solid #3498db;
        }

        .dashboard-card:nth-child(2) {
            border-top-color: #2ecc71;
        }

        .dashboard-card:nth-child(3) {
            border-top-color: #e74c3c;
        }

        .dashboard-card:nth-child(4) {
            border-top-color: #f39c12;
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.15);
        }

        .dashboard-card-icon {
            font-size: 40px;
            margin-bottom: 15px;
        }

        .dashboard-card h3 {
            font-size: 16px;
            margin: 10px 0;
            font-weight: bold;
        }

        .dashboard-card p {
            font-size: 12px;
            color: #7f8c8d;
            margin: 0;
        }

        @media (max-width: 768px) {
            .main-wrapper {
                flex-direction: column;
            }

            .sidebar {
                width: 100%;
                display: flex;
                overflow-x: auto;
                padding: 10px 0;
            }

            .nav {
                flex-direction: row;
            }

            .nav a {
                padding: 10px 15px;
                white-space: nowrap;
                border-left: none;
                border-bottom: 3px solid transparent;
            }

            .nav a:hover {
                border-bottom-color: #3498db;
                border-left: none;
                padding-left: 15px;
            }

            .content {
                padding: 20px;
            }

            .content h1 {
                font-size: 24px;
                margin-bottom: 20px;
            }

            .dashboard-grid {
                grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
                gap: 15px;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>👤 Welcome, <%= email %></h2>
    </div>

    <div class="main-wrapper">
        <div class="sidebar">
            <h3>📋 Members</h3>
            <div class="nav">
                <a href="addMember.jsp">➕ Add Member</a>
                <a href="viewMembers.jsp">👥 View Members</a>
                <a href="editMember.jsp">✏️ Edit Member</a>
                <a href="searchMember.jsp">🔍 Search Member</a>
            </div>

            <h3>🏋️ Trainers</h3>
            <div class="nav">
                <a href="addTrainer.jsp">➕ Add Trainer</a>
                <a href="viewTrainers.jsp">👨‍🏫 View Trainers</a>
            </div>

            <h3>Account</h3>
            <div class="nav">
                <a href="LogoutServlet" class="logout">🚪 Logout</a>
            </div>
        </div>

        <div class="content">
            <h1>Admin Dashboard</h1>

            <div class="dashboard-grid">
                <a href="addMember.jsp" class="dashboard-card">
                    <div class="dashboard-card-icon">➕</div>
                    <h3>Add Member</h3>
                    <p>Register new member</p>
                </a>

                <a href="viewMembers.jsp" class="dashboard-card">
                    <div class="dashboard-card-icon">👥</div>
                    <h3>View Members</h3>
                    <p>Manage all members</p>
                </a>

                <a href="addTrainer.jsp" class="dashboard-card">
                    <div class="dashboard-card-icon">➕</div>
                    <h3>Add Trainer</h3>
                    <p>Register new trainer</p>
                </a>

                <a href="viewTrainers.jsp" class="dashboard-card">
                    <div class="dashboard-card-icon">👨‍🏫</div>
                    <h3>View Trainers</h3>
                    <p>Manage all trainers</p>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
