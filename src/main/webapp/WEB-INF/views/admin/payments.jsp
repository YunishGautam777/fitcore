<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payments -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Payments &amp; Memberships</h1>

        <div class="card mt">
            <h3>Record Payment + Extend Plan</h3>
            <form method="post" class="form-row">
                <input type="hidden" name="action" value="record">
                <div class="form-group">
                    <label>Member</label>
                    <select name="memberId" required>
                        <c:forEach var="m" items="${members}">
                            <option value="${m.memberId}">${m.name} (#${m.memberId})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Plan</label>
                    <select name="planId" required>
                        <c:forEach var="p" items="${plans}">
                            <option value="${p.planId}">${p.planName} -- &#8377; ${p.price}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Method</label>
                    <select name="method"><option>CASH</option><option>CARD</option><option>ONLINE</option></select>
                </div>
                <div class="form-group" style="align-self:end"><button class="btn">Record Payment</button></div>
            </form>
        </div>

        <div class="table-wrap mt">
            <table class="table">
                <thead><tr><th>Date</th><th>Member</th><th>Amount</th><th>Method</th><th>Status</th><th></th></tr></thead>
                <tbody>
                <c:forEach var="p" items="${payments}">
                    <tr>
                        <td>${p.date}</td>
                        <td>${p.memberName}</td>
                        <td>&#8377; <fmt:formatNumber value="${p.amount}" pattern="#,###"/></td>
                        <td>${p.paymentMethod}</td>
                        <td>
                            <c:choose>
                                <c:when test="${p.status == 'PAID'}"><span class="badge badge-active">Paid</span></c:when>
                                <c:when test="${p.status == 'PENDING'}"><span class="badge badge-pending">Pending</span></c:when>
                                <c:otherwise><span class="badge badge-danger">${p.status}</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${p.status == 'PENDING'}">
                                <form method="post" style="display:inline">
                                    <input type="hidden" name="action" value="status">
                                    <input type="hidden" name="paymentId" value="${p.paymentId}">
                                    <input type="hidden" name="status" value="PAID">
                                    <button class="btn btn-sm btn-success">Mark Paid</button>
                                </form>
                            </c:if>
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
