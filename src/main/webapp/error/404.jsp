<%-- ================================================================
     File    : 404.jsp
     Section : Error Pages
     Desc    : Custom 404 Not Found error page for FitCore
     ================================================================ --%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Not Found -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <%-- ==================== ERROR WRAPPER ==================== --%>
    <div class="auth-wrap">
        <div class="card auth-card text-center">

            <%-- Error code --%>
            <h1 style="font-size:4rem;color:var(--secondary)">404</h1>

            <%-- Error message --%>
            <h2>Page Not Found</h2>

            <%-- Navigate back home --%>
            <a href="${pageContext.request.contextPath}/" class="btn mt">
                Go Home
            </a>

        </div>
    </div>

</body>
</html>