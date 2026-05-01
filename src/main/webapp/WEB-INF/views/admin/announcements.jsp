<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Announcements -- FitCore Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="../common/sidebar-admin.jspf" %>
    <main class="main">
        <h1>Announcements / Notice Board</h1>

        <div class="card mt">
            <h3>Post a New Announcement</h3>
            <form method="post">
                <input type="hidden" name="action" value="create">
                <div class="form-group"><label>Title</label><input name="title" required></div>
                <div class="form-group"><label>Body</label><textarea name="body" rows="3" required></textarea></div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Category</label>
                        <select name="category"><option>GENERAL</option><option>NOTICE</option><option>OFFER</option></select>
                    </div>
                    <div class="form-group" style="display:flex;align-items:center;gap:.5rem;margin-top:1.5rem">
                        <input type="checkbox" name="pinned" id="pinned" style="width:auto"><label for="pinned" style="margin:0">Pinned</label>
                    </div>
                </div>
                <button class="btn">Post Announcement</button>
            </form>
        </div>

        <div class="grid grid-2 mt">
            <c:forEach var="a" items="${announcements}">
                <div class="card">
                    <h3>${a.title}
                        <c:if test="${a.pinned}"><span class="badge badge-pending">Pinned</span></c:if>
                    </h3>
                    <p class="text-muted">${a.body}</p>
                    <p style="font-size:.8rem;color:var(--muted)">${a.category} &middot; ${a.postedAt}</p>
                    <form method="post" onsubmit="return confirm('Delete?')">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="announcementId" value="${a.announcementId}">
                        <button class="btn btn-sm btn-danger">Delete</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
