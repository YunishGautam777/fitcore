<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <h1>Search</h1>

        <form method="get" class="card mt" style="display:flex;gap:.5rem">
            <input type="text" name="q" value="${q}" placeholder="Try 'Yoga', 'HIIT', or trainer name" style="flex:1">
            <button class="btn">Search</button>
        </form>

        <c:if test="${not empty q}">
            <h2 class="mt-2">Trainers</h2>
            <div class="grid grid-3 mt">
                <c:forEach var="t" items="${trainers}">
                    <div class="card">
                        <h3>${t.name}</h3>
                        <p class="text-muted">${t.specialization} -- ${t.experience} yrs</p>
                    </div>
                </c:forEach>
            </div>

            <h2 class="mt-2">Sessions</h2>
            <div class="grid grid-3 mt">
                <c:forEach var="s" items="${sessions}">
                    <div class="card">
                        <h3>${s.name}</h3>
                        <p class="text-muted">${s.type} -- ${s.trainerName}</p>
                        <p><strong>${s.schedule}</strong></p>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
