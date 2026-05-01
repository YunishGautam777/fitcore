<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approvals -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Pending Member Approvals</h1>
        <c:choose>
            <c:when test="${empty pending}">
                <div class="card mt"><p class="text-muted">No requests pending. Looking good.</p></div>
            </c:when>
            <c:otherwise>
                <div class="table-wrap mt">
                    <table class="table">
                        <thead><tr><th>Username</th><th>Created</th><th>Role</th><th>Action</th></tr></thead>
                        <tbody>
                        <c:forEach var="u" items="${pending}">
                            <tr>
                                <td>${u.username}</td>
                                <td>${u.createdAt}</td>
                                <td>${u.role}</td>
                                <td>
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="userId" value="${u.userId}">
                                        <input type="hidden" name="action" value="approve">
                                        <button class="btn btn-sm btn-success">Approve</button>
                                    </form>
                                    <form method="post" style="display:inline">
                                        <input type="hidden" name="userId" value="${u.userId}">
                                        <input type="hidden" name="action" value="reject">
                                        <button class="btn btn-sm btn-danger">Reject</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
