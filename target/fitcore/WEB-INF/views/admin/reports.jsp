<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reports -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Reports &amp; Analytics</h1>

        <div class="grid grid-3 mt">
            <div class="card stat-card"><div class="num">&#8377; <fmt:formatNumber value="${monthlyRevenue}" pattern="#,###"/></div><div class="lbl">This-Month Revenue</div></div>
            <div class="card stat-card"><div class="num">${activeMembers}</div><div class="lbl">Active Memberships</div></div>
            <div class="card stat-card"><div class="num">${inactiveMembers}</div><div class="lbl">Expired / Inactive</div></div>
        </div>

        <div class="grid grid-2 mt-2">
            <div class="card">
                <h3>Most Popular Classes</h3>
                <table class="table">
                    <thead><tr><th>Class</th><th>Bookings</th></tr></thead>
                    <tbody>
                    <c:forEach var="e" items="${popularClasses}">
                        <tr><td>${e.key}</td><td>${e.value}</td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="card">
                <h3>Equipment Maintenance Frequency</h3>
                <table class="table">
                    <thead><tr><th>Equipment</th><th>Maintenance Logs</th></tr></thead>
                    <tbody>
                    <c:forEach var="e" items="${equipmentMaint}">
                        <tr><td>${e.key}</td><td>${e.value}</td></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="grid grid-2 mt-2">
            <div class="card"><h3>Monthly Revenue vs. Expenses</h3><div class="chart-shell">[ Bar chart ]</div></div>
            <div class="card"><h3>Member Retention Trend</h3><div class="chart-shell">[ Line chart ]</div></div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
