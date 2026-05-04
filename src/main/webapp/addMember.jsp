<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/15/2026
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Member - Gym Management System</title>
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
            max-width: 500px;
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
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="tel"], select {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
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
        .error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>Gym Management System</h2>
    </div>
    <div class="container">
        <h1>Add New Member</h1>
        <% if (request.getParameter("error") != null) { %>
            <div class="error">Error adding member. Please try again.</div>
        <% } %>
        <form method="POST" action="AddMemberServlet">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter member name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter member email" required>

            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" placeholder="Enter member phone" required>

            <label for="plan">Plan:</label>
            <select id="plan" name="plan" required>
                <option value="">-- Select Plan --</option>
                <option value="Basic">Basic</option>
                <option value="Premium">Premium</option>
                <option value="Elite">Elite</option>
            </select>

            <button type="submit">Add Member</button>
        </form>
        <div class="back-link">
            <a href="adminDashboard.jsp">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
