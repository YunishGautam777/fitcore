<%-- ================================================================
     File    : login.jsp
     Section : Authentication
     Desc    : Login page for FitCore member access
     ================================================================ --%>
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

    <%-- ==================== LOGIN WRAPPER ==================== --%>
    <div class="auth-wrap">
        <div class="auth-card card">

            <%-- Page heading --%>
            <h2>Welcome Back</h2>
            <p class="text-muted text-center mb">Log in to your FitCore account</p>

            <%-- -------- Alert Messages -------- --%>

            <%-- Error alert --%>
            <c:if test="${not empty error}">
                <div class="alert alert-error">${error}</div>
            </c:if>

            <%-- Registration success alert --%>
            <c:if test="${param.registered == '1'}">
                <div class="alert alert-success">
                    Account created. Wait for admin approval to log in.
                </div>
            </c:if>

            <%-- Logout success alert --%>
            <c:if test="${param.loggedOut == '1'}">
                <div class="alert alert-success">
                    You have been logged out.
                </div>
            </c:if>

            <%-- -------- Login Form -------- --%>
            <form action="${pageContext.request.contextPath}/login" method="post">

                <%-- Username field --%>
                <div class="form-group">
                    <label>Username</label>
                    <input
                        name="username"
                        type="text"
                        autofocus
                        required>
                </div>

                <%-- Password field --%>
                <div class="form-group">
                    <label>Password</label>
                    <input
                        name="password"
                        type="password"
                        required>
                </div>

                <%-- Submit button --%>
                <button class="btn" style="width:100%">Login</button>

            </form>

            <%-- -------- Footer Links -------- --%>

            <%-- Register link --%>
            <p class="text-center mt">
                New here?
                <a href="${pageContext.request.contextPath}/register.jsp">Create an account</a>
            </p>

            <%-- Back to home link --%>
            <p class="text-center">
                <a class="text-muted" href="${pageContext.request.contextPath}/">
                    &larr; Back to home
                </a>
            </p>

        </div>
    </div>

    <%-- ==================== SCRIPTS ==================== --%>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>
</html>