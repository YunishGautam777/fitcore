<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payments -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-member.jspf" %>
    <main class="main">
        <h1>Payments &amp; Renewals</h1>

        <c:if test="${param.submitted == '1'}">
            <div class="alert alert-success">Renewal request submitted -- waiting for admin verification.</div>
        </c:if>

        <div class="card mt">
            <h3>Submit Renewal Request</h3>
            <form method="post" class="form-row">
                <div class="form-group">
                    <label>Plan</label>
                    <select name="planId" required>
                        <c:forEach var="p" items="${plans}">
                            <option value="${p.planId}">${p.planName} -- &#8377; ${p.price} (${p.duration} days)</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Method</label>
                    <select name="method"><option>CASH</option><option>CARD</option><option>ONLINE</option></select>
                </div>
                <div class="form-group" style="align-self:end"><button class="btn">Submit Request</button></div>
            </form>
        </div>

        <div class="table-wrap mt">
            <table class="table">
                <thead><tr><th>Date</th><th>Amount</th><th>Method</th><th>Status</th></tr></thead>
                <tbody>
                <c:forEach var="p" items="${payments}">
                    <tr>
                        <td>${p.date}</td>
                        <td>&#8377; <fmt:formatNumber value="${p.amount}" pattern="#,###"/></td>
                        <td>${p.paymentMethod}</td>
                        <td>
                            <c:choose>
                                <c:when test="${p.status == 'PAID'}"><span class="badge badge-active">Paid</span></c:when>
                                <c:when test="${p.status == 'PENDING'}"><span class="badge badge-pending">Pending</span></c:when>
                                <c:otherwise><span class="badge badge-danger">${p.status}</span></c:otherwise>
                            </c:choose>
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
