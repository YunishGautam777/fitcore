<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <div class="main-header">
            <h1>Dashboard</h1>
            <span class="text-muted">Welcome, ${sessionScope.user.username}</span>
        </div>

        <div class="grid grid-4">
            <div class="card stat-card"><div class="num">${activeMembers}</div><div class="lbl">Active Members</div></div>
            <div class="card stat-card"><div class="num">${inactiveMembers}</div><div class="lbl">Inactive</div></div>
            <div class="card stat-card"><div class="num"><fmt:formatNumber value="${monthlyRevenue}" pattern="#,###"/></div><div class="lbl">Revenue (Month)</div></div>
            <div class="card stat-card"><div class="num">${pendingUsers.size()}</div><div class="lbl">Pending Approvals</div></div>
        </div>

        <div class="grid grid-2 mt-2">
            <div class="card">
                <div class="card-title">Recent Announcements</div>
                <c:forEach var="a" items="${announcements}">
                    <div style="padding:.6rem 0;border-bottom:1px solid var(--border)">
                        <strong>${a.title}</strong>
                        <c:if test="${a.pinned}"><span class="badge badge-pending">Pinned</span></c:if>
                        <p class="text-muted" style="font-size:.9rem">${a.body}</p>
                    </div>
                </c:forEach>
            </div>

            <div class="card">
                <div class="card-title">Pending Member Approvals</div>
                <c:choose>
                    <c:when test="${empty pendingUsers}"><p class="text-muted">No pending requests.</p></c:when>
                    <c:otherwise>
                        <table class="table">
                            <tr><th>Username</th><th>Created</th><th></th></tr>
                            <c:forEach var="u" items="${pendingUsers}">
                                <tr>
                                    <td>${u.username}</td>
                                    <td>${u.createdAt}</td>
                                    <td><a class="btn btn-sm" href="${pageContext.request.contextPath}/admin/approvals">Review</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
