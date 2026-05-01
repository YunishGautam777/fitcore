<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Members -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <div class="main-header">
            <h1>Members</h1>
            <form method="get" style="display:flex;gap:.5rem">
                <input type="text" name="q" value="${q}" placeholder="Search name / email">
                <button class="btn btn-sm">Search</button>
            </form>
        </div>

        <div class="table-wrap">
            <table class="table" id="membersTable">
                <thead>
                <tr>
                    <th>ID</th><th>Name</th><th>Email</th><th>Contact</th>
                    <th>Plan</th><th>Status</th><th>Join</th><th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="m" items="${members}">
                    <tr>
                        <td>${m.memberId}</td>
                        <td>${m.name}</td>
                        <td>${m.email}</td>
                        <td>${m.contact}</td>
                        <td>${m.membershipType}</td>
                        <td>
                            <c:choose>
                                <c:when test="${m.status == 'ACTIVE'}"><span class="badge badge-active">Active</span></c:when>
                                <c:when test="${m.status == 'SUSPENDED'}"><span class="badge badge-danger">Suspended</span></c:when>
                                <c:otherwise><span class="badge badge-inactive">Inactive</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>${m.joinDate}</td>
                        <td>
                            <form method="post" style="display:inline" onsubmit="return confirm('Delete this member?')">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="memberId" value="${m.memberId}">
                                <button class="btn btn-sm btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
