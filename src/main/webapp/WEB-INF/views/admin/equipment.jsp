<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipment -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Equipment Inventory</h1>

        <div class="card mt">
            <h3>Add Equipment</h3>
            <form method="post" class="form-row">
                <input type="hidden" name="action" value="create">
                <div class="form-group"><label>Name</label><input name="name" required></div>
                <div class="form-group"><label>Category</label><input name="category"></div>
                <div class="form-group"><label>Quantity</label><input type="number" name="quantity" min="0" value="1"></div>
                <div class="form-group">
                    <label>Condition</label>
                    <select name="condition"><option value="GOOD">Good</option><option value="NEEDS_SERVICE">Needs Service</option><option value="DAMAGED">Damaged</option></select>
                </div>
                <div class="form-group" style="grid-column:span 2"><button class="btn">Add</button></div>
            </form>
        </div>

        <div class="table-wrap mt">
            <table class="table">
                <thead><tr><th>Name</th><th>Category</th><th>Qty</th><th>Condition</th><th>Purchased</th><th></th></tr></thead>
                <tbody>
                <c:forEach var="e" items="${equipment}">
                    <tr>
                        <td>${e.name}</td>
                        <td>${e.category}</td>
                        <td>
                            <c:choose>
                                <c:when test="${e.quantity <= 2}"><span class="badge badge-danger">${e.quantity} -- LOW</span></c:when>
                                <c:otherwise>${e.quantity}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${e.condition == 'GOOD'}"><span class="badge badge-active">Good</span></c:when>
                                <c:when test="${e.condition == 'NEEDS_SERVICE'}"><span class="badge badge-pending">Service</span></c:when>
                                <c:otherwise><span class="badge badge-danger">Damaged</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td>${e.purchaseDate}</td>
                        <td>
                            <form method="post" style="display:inline" onsubmit="return confirm('Delete?')">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="equipmentId" value="${e.equipmentId}">
                                <button class="btn btn-sm btn-danger">x</button>
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
