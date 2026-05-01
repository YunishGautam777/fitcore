<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Attendance -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <h1>My Attendance</h1>

        <div class="card mt"><div class="stat-card">
            <div class="num">${monthlyVisits}</div><div class="lbl">Visits This Month</div>
        </div></div>

        <div class="table-wrap mt">
            <table class="table">
                <thead><tr><th>Date</th><th>Check-in</th><th>Check-out</th></tr></thead>
                <tbody>
                <c:forEach var="a" items="${attendance}">
                    <tr><td>${a.date}</td><td>${a.checkInTime}</td><td>${a.checkOutTime}</td></tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
