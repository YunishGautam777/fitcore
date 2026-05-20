<%-- Page: contact.jsp | Purpose: Contact form and gym location info --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FitCore -- Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%-- ========== HEADER / NAVIGATION ========== --%>
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

<%-- ========== MAIN CONTENT ========== --%>
<main class="container mt-2">

    <h1>Get in Touch</h1>

    <%-- Success message shown after form is submitted --%>
    <c:if test="${param.sent == '1'}">
        <div class="alert alert-success">Thanks! We'll respond soon.</div>
    </c:if>

    <div class="grid grid-2 mt">

        <%-- LEFT CARD: Gym address and map --%>
        <div class="card">
            <h3>Visit Us</h3>
            <p class="text-muted">FitCore Gym &middot; 12 Iron Lane, Sector 21, New Delhi</p>
            <p class="text-muted"><strong>Phone:</strong> +91 98765 00000</p>
            <p class="text-muted"><strong>Email:</strong> hello@fitcore.example</p>

            <%-- Embedded Google Maps --%>
            <iframe
              title="FitCore location"
              src="https://www.google.com/maps?q=New+Delhi&output=embed"
              width="100%"
              height="220"
              loading="lazy"
              style="border:0;border-radius:8px;margin-top:1rem"></iframe>
        </div>

        <%-- RIGHT CARD: Contact form --%>
        <div class="card">

            <h3>Send a Message</h3>

            <%-- Form posts to ContactServlet --%>
            <form method="post" action="${pageContext.request.contextPath}/contact-submit">

                <%-- Full name --%>
                <div class="form-group">
                    <label for="userName">Name</label>
                    <input type="text" id="userName" name="name" required>
                </div>

                <%-- Email address --%>
                <div class="form-group">
                    <label for="userEmail">Email</label>
                    <input type="email" id="userEmail" name="email" required>
                </div>

                <%-- Subject line (optional) --%>
                <div class="form-group">
                    <label for="userSubject">Subject</label>
                    <input type="text" id="userSubject" name="subject">
                </div>

                <%-- Message body --%>
                <div class="form-group">
                    <label for="userMessage">Message</label>
                    <textarea id="userMessage" name="message" rows="5" required></textarea>
                </div>

                <%-- Submit button --%>
                <button type="submit" class="btn">Send</button>

            </form>

        </div>
    </div>
</main>

<%-- ========== FOOTER ========== --%>
<footer class="footer">
    <div class="container">
        <strong>FitCore Gym</strong> &copy; 2026
    </div>
</footer>

</body>
</html>