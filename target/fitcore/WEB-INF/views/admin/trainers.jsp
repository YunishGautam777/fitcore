<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainers -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Trainers</h1>

        <div class="grid grid-2 mt">
            <div class="card">
                <h3>Add Trainer</h3>
                <form method="post">
                    <input type="hidden" name="action" value="create">
                    <div class="form-group"><label>Name</label><input name="name" required></div>
                    <div class="form-group"><label>Specialization</label><input name="specialization"></div>
                    <div class="form-row">
                        <div class="form-group"><label>Experience (yrs)</label><input type="number" name="experience" min="0"></div>
                        <div class="form-group"><label>Contact</label><input name="contact"></div>
                    </div>
                    <div class="form-group">
                        <label>Shift</label>
                        <select name="assignedShift"><option>MORNING</option><option>EVENING</option><option>FULL_DAY</option></select>
                    </div>
                    <button class="btn">Add Trainer</button>
                </form>
            </div>

            <div class="card">
                <h3>All Trainers</h3>
                <div class="table-wrap">
                    <table class="table">
                        <tr><th>Name</th><th>Spec.</th><th>Exp.</th><th>Shift</th><th></th></tr>
                        <c:forEach var="t" items="${trainers}">
                            <tr>
                                <td>${t.name}</td><td>${t.specialization}</td>
                                <td>${t.experience}y</td><td>${t.assignedShift}</td>
                                <td>
                                    <form method="post" style="display:inline" onsubmit="return confirm('Delete trainer?')">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="trainerId" value="${t.trainerId}">
                                        <button class="btn btn-sm btn-danger">x</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
