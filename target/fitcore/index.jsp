<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FitCore -- Forge Your Strongest Self</title>
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
            <a href="${pageContext.request.contextPath}/register.jsp" class="btn btn-sm">Join Now</a>
        </nav>
    </div>
</header>

<section class="hero">
    <div>
        <h1>FORGE YOUR <span>STRONGEST</span><br>SELF.</h1>
        <p>FitCore is the all-in-one gym management platform that powers your workouts, trainers, classes, and progress -- in one place.</p>
        <a href="${pageContext.request.contextPath}/register.jsp" class="btn">Start Training</a>
        <a href="${pageContext.request.contextPath}/about.jsp" class="btn btn-outline" style="margin-left:.7rem">Learn More</a>
    </div>
</section>

<section class="features container">
    <h2>Why FitCore?</h2>
    <div class="grid grid-3">
        <div class="card">
            <h3>Smart Class Booking</h3>
            <p class="text-muted">Browse, wishlist and book Yoga, HIIT, Zumba and more -- with capacity-aware reservations.</p>
        </div>
        <div class="card">
            <h3>Personal Trainers</h3>
            <p class="text-muted">Search trainers by specialisation and get matched with the right coach for your goals.</p>
        </div>
        <div class="card">
            <h3>Track Everything</h3>
            <p class="text-muted">Attendance streaks, payment history, diet plans and BMI -- all in your member dashboard.</p>
        </div>
    </div>
</section>

<footer class="footer">
    <div class="container">
        <strong>FitCore Gym</strong> &copy; 2026 -- Forge your strongest self.
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
