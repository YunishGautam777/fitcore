<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="auth-wrap">
    <div class="card auth-card">
        <h2>Welcome Back</h2>
        <p class="text-center text-muted mb">Log in to your FitCore account</p>

        <c:if test="${not empty error}"><div class="alert alert-error">${error}</div></c:if>
        <c:if test="${param.registered == '1'}">
            <div class="alert alert-success">Account created. Wait for admin approval to log in.</div>
        </c:if>
        <c:if test="${param.loggedOut == '1'}">
            <div class="alert alert-success">You have been logged out.</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required autofocus>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button class="btn" style="width:100%">Login</button>
        </form>

        <p class="text-center mt">
            New here? <a href="${pageContext.request.contextPath}/register.jsp">Create an account</a>
        </p>
        <p class="text-center"><a href="${pageContext.request.contextPath}/" class="text-muted">&larr; Back to home</a></p>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
