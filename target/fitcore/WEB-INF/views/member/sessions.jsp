<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browse Classes -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <div class="main-header">
            <h1>Available Classes</h1>
            <form method="get" style="display:flex;gap:.5rem">
                <input type="text" name="q" value="${q}" placeholder="Search by name / type / trainer">
                <button class="btn btn-sm">Search</button>
            </form>
        </div>

        <c:if test="${param.status == 'SUCCESS'}"><div class="alert alert-success">Booking confirmed!</div></c:if>
        <c:if test="${param.status == 'FULL'}"><div class="alert alert-error">Sorry, that class is full.</div></c:if>
        <c:if test="${param.status == 'ALREADY_BOOKED'}"><div class="alert alert-error">You're already booked.</div></c:if>

        <div class="tile-grid mt">
            <c:forEach var="s" items="${sessions}">
                <div class="card">
                    <h3>${s.name}</h3>
                    <p class="text-muted">${s.type} &middot; ${s.trainerName}</p>
                    <p><strong>${s.schedule}</strong></p>
                    <p class="text-muted">${s.enrolled} / ${s.capacity} spots filled</p>
                    <div class="flex gap mt">
                        <c:choose>
                            <c:when test="${s.full}">
                                <button class="btn btn-sm" disabled>Full</button>
                            </c:when>
                            <c:otherwise>
                                <form method="post" action="${pageContext.request.contextPath}/member/book" style="display:inline">
                                    <input type="hidden" name="sessionId" value="${s.sessionId}">
                                    <button class="btn btn-sm">Apply / Book</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <button class="btn btn-sm btn-secondary"
                                data-wishlist="${s.sessionId}"
                                onclick="toggleWishlist(${s.sessionId}, '${s.name}')">&#9734; Wishlist</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
