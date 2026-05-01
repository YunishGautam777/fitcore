<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Dashboard -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <div class="main-header">
            <h1>Hi, ${sessionScope.member.name}</h1>
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/member/bmi.jsp">BMI Calculator</a>
        </div>

        <div class="tile-grid">
            <div class="card">
                <div class="card-title">Active Plan</div>
                <h3>${empty plan ? 'No plan assigned' : plan.planName}</h3>
                <p class="text-muted">Expires: ${empty sessionScope.member.planExpiry ? '-' : sessionScope.member.planExpiry}</p>
            </div>

            <div class="card">
                <div class="card-title">Visits This Month</div>
                <div class="stat-card"><div class="num">${monthlyVisits}</div></div>
                <div class="progress mt"><span style="width:${monthlyVisits * 5}%"></span></div>
            </div>

            <div class="card">
                <div class="card-title">My Bookings</div>
                <c:choose>
                    <c:when test="${empty bookings}"><p class="text-muted">No upcoming bookings.</p></c:when>
                    <c:otherwise>
                        <c:forEach var="b" items="${bookings}" end="3">
                            <p>&bull; <strong>${b.name}</strong> -- ${b.schedule}</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="card">
                <div class="card-title">Recent Payments</div>
                <c:choose>
                    <c:when test="${empty payments}"><p class="text-muted">No payments yet.</p></c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${payments}" end="3">
                            <p>${p.date} -- &#8377; <fmt:formatNumber value="${p.amount}" pattern="#,###"/> [${p.status}]</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <h2 class="mt-2">Notice Board</h2>
        <div class="grid grid-2 mt">
            <c:forEach var="a" items="${announcements}">
                <div class="card">
                    <h3>${a.title}
                        <c:if test="${a.pinned}"><span class="badge badge-pending">Pinned</span></c:if>
                    </h3>
                    <p class="text-muted">${a.body}</p>
                    <small class="text-muted">${a.postedAt}</small>
                </div>
            </c:forEach>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
