<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Diet Plan -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <h1>My Diet Plans</h1>
        <c:choose>
            <c:when test="${empty diets}">
                <div class="card mt"><p class="text-muted">Your trainer hasn't assigned a plan yet.</p></div>
            </c:when>
            <c:otherwise>
                <div class="grid grid-2 mt">
                    <c:forEach var="d" items="${diets}">
                        <div class="card">
                            <h3>${d.calories} kcal/day</h3>
                            <p class="text-muted">By ${d.trainerName} -- ${d.assignedDate}</p>
                            <pre style="white-space:pre-wrap;color:var(--text);font-family:inherit">${d.mealDetails}</pre>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
