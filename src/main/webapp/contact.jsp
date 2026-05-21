<%-- ================================================================
     File    : contact.jsp
     Section : Public Pages
     Desc    : Contact page with gym info and message form
     ================================================================ --%>
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

    <%-- ==================== SITE HEADER ==================== --%>
    <header class="topnav">
        <div class="container inner">

            <%-- Brand logo --%>
            <a class="brand" href="${pageContext.request.contextPath}/">
                FIT<span>CORE</span>
            </a>

            <%-- Navigation menu --%>
            <nav class="nav-links">
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="${pageContext.request.contextPath}/about.jsp">About</a>
                <a href="${pageContext.request.contextPath}/contact.jsp">Contact</a>
                <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
            </nav>

        </div>
    </header>

    <%-- ==================== PAGE BODY ==================== --%>
    <main class="container mt-2">

        <h1>Get in Touch</h1>

        <%-- Alert: shown only when form submitted successfully --%>
        <c:if test="${param.sent == '1'}">
            <div class="alert alert-success">
                Thanks! We'll respond soon.
            </div>
        </c:if>

        <div class="grid grid-2 mt">

            <%-- -------- Card 1: Location Info -------- --%>
            <div class="card">

                <h3>Visit Us</h3>

                <%-- Gym address --%>
                <p class="text-muted">
                    FitCore Gym &middot; 12 Iron Lane, Sector 21, New Delhi
                </p>

                <%-- Contact details --%>
                <p class="text-muted">
                    <strong>Phone:</strong> +91 98765 00000
                </p>
                <p class="text-muted">
                    <strong>Email:</strong> hello@fitcore.example
                </p>

                <%-- Google Maps embed --%>
                <iframe
                    src="https://www.google.com/maps?q=New+Delhi&output=embed"
                    title="FitCore location"
                    width="100%"
                    height="220"
                    loading="lazy"
                    style="border:0;border-radius:8px;margin-top:1rem">
                </iframe>

            </div>

            <%-- -------- Card 2: Contact Form -------- --%>
            <div class="card">

                <h3>Send a Message</h3>

                <%-- Message submission form --%>
                <form action="${pageContext.request.contextPath}/contact-submit" method="post">

                    <%-- Visitor name --%>
                    <div class="form-group">
                        <label for="visitorName">Name</label>
                        <input
                            id="visitorName"
                            name="name"
                            type="text"
                            required>
                    </div>

                    <%-- Visitor email --%>
                    <div class="form-group">
                        <label for="visitorEmail">Email</label>
                        <input
                            id="visitorEmail"
                            name="email"
                            type="email"
                            required>
                    </div>

                    <%-- Message subject --%>
                    <div class="form-group">
                        <label for="visitorSubject">Subject</label>
                        <input
                            id="visitorSubject"
                            name="subject"
                            type="text">
                    </div>

                    <%-- Message content --%>
                    <div class="form-group">
                        <label for="visitorMessage">Message</label>
                        <textarea
                            id="visitorMessage"
                            name="message"
                            rows="5"
                            required></textarea>
                    </div>

                    <%-- Submit --%>
                    <button type="submit" class="btn">Send</button>

                </form>

            </div>

        </div>
    </main>

    <%-- ==================== SITE FOOTER ==================== --%>
    <footer class="footer">
        <div class="container">
            <strong>FitCore Gym</strong> &copy; 2026
        </div>
    </footer>

</body>
</html>