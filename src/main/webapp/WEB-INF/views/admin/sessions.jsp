<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sessions -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Workout Sessions / Classes</h1>

        <div class="card mt">
            <h3>Schedule a Class</h3>
            <form method="post" class="form-row">
                <input type="hidden" name="action" value="create">
                <div class="form-group"><label>Name</label><input name="name" required></div>
                <div class="form-group"><label>Type</label><input name="type" placeholder="Yoga / HIIT / Zumba"></div>
                <div class="form-group">
                    <label>Trainer</label>
                    <select name="trainerId">
                        <option value="">--</option>
                        <c:forEach var="t" items="${trainers}">
                            <option value="${t.trainerId}">${t.name} (${t.specialization})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group"><label>Capacity</label><input type="number" name="capacity" min="1" value="20"></div>
                <div class="form-group" style="grid-column:span 2">
                    <label>Schedule</label>
                    <input type="datetime-local" name="schedule" required>
                </div>
                <div class="form-group" style="grid-column:span 2"><button class="btn">Create Class</button></div>
            </form>
        </div>

        <div class="table-wrap mt">
            <table class="table">
                <thead><tr><th>Name</th><th>Type</th><th>Trainer</th><th>When</th><th>Filled</th><th></th></tr></thead>
                <tbody>
                <c:forEach var="s" items="${sessions}">
                    <tr>
                        <td>${s.name}</td>
                        <td>${s.type}</td>
                        <td>${s.trainerName}</td>
                        <td>${s.schedule}</td>
                        <td>${s.enrolled} / ${s.capacity}</td>
                        <td>
                            <form method="post" style="display:inline" onsubmit="return confirm('Delete session?')">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="sessionId" value="${s.sessionId}">
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
