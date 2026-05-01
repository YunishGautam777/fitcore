<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header class="topnav">
    <div class="container inner">
        <a class="brand" href="${pageContext.request.contextPath}/">FIT<span>CORE</span></a>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/about.jsp">About</a>
            <a href="${pageContext.request.contextPath}/contact.jsp">Contact</a>
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </nav>
    </div>
</header>

<main class="container mt-2">
    <h1>Get in Touch</h1>

    <c:if test="${param.sent == '1'}">
        <div class="alert alert-success">Thanks! We'll respond soon.</div>
    </c:if>

    <div class="grid grid-2 mt">
        <div class="card">
            <h3>Visit Us</h3>
            <p class="text-muted">FitCore Gym &middot; 12 Iron Lane, Sector 21, New Delhi</p>
            <p class="text-muted"><strong>Phone:</strong> +91 98765 00000</p>
            <p class="text-muted"><strong>Email:</strong> hello@fitcore.example</p>

            <!-- Embedded Google Maps placeholder -->
            <iframe
              title="FitCore location"
              src="https://www.google.com/maps?q=New+Delhi&output=embed"
              width="100%" height="220" style="border:0;border-radius:8px;margin-top:1rem"
              loading="lazy"></iframe>
        </div>

        <div class="card">
            <h3>Send a Message</h3>
            <form method="post" action="${pageContext.request.contextPath}/contact-submit">
                <div class="form-group"><label>Name</label><input type="text" name="name" required></div>
                <div class="form-group"><label>Email</label><input type="email" name="email" required></div>
                <div class="form-group"><label>Subject</label><input type="text" name="subject"></div>
                <div class="form-group"><label>Message</label><textarea name="message" rows="5" required></textarea></div>
                <button class="btn">Send</button>
            </form>
        </div>
    </div>
</main>

<footer class="footer"><div class="container"><strong>FitCore Gym</strong> &copy; 2026</div></footer>
</body>
</html>
