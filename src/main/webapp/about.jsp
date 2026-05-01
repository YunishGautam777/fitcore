<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About -- FitCore</title>
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
    <h1>About FitCore</h1>

    <div class="grid grid-2 mt">
        <div class="card">
            <h3>Our Mission</h3>
            <p class="text-muted">Make professional fitness coaching accessible to everyone, every day.</p>
        </div>
        <div class="card">
            <h3>Our Vision</h3>
            <p class="text-muted">Become the most data-driven neighbourhood gym chain in the country.</p>
        </div>
    </div>

    <h2 class="mt-2">Our Trainer Team</h2>
    <div class="grid grid-3 mt">
        <div class="card"><h3>Aman Khanna</h3><p class="text-muted">Strength &amp; Conditioning -- 6 yrs</p></div>
        <div class="card"><h3>Priya Verma</h3><p class="text-muted">Yoga -- 8 yrs</p></div>
        <div class="card"><h3>Rahul Singh</h3><p class="text-muted">HIIT -- 4 yrs</p></div>
        <div class="card"><h3>Neha Sharma</h3><p class="text-muted">Zumba -- 5 yrs</p></div>
    </div>

    <h2 class="mt-2">Facilities</h2>
    <div class="grid grid-4 mt">
        <div class="card"><h3>Strength Zone</h3><p class="text-muted">Olympic platforms, racks, free weights.</p></div>
        <div class="card"><h3>Cardio Zone</h3><p class="text-muted">Treadmills, bikes, rowers.</p></div>
        <div class="card"><h3>Group Studio</h3><p class="text-muted">Yoga, HIIT, Zumba, Spin.</p></div>
        <div class="card"><h3>Recovery Lounge</h3><p class="text-muted">Foam rollers, massage chairs.</p></div>
    </div>

    <h2 class="mt-2">Working Hours</h2>
    <div class="card mt">
        <table class="table">
            <tr><th>Mon -- Fri</th><td>5:30 AM -- 11:00 PM</td></tr>
            <tr><th>Saturday</th><td>6:00 AM -- 10:00 PM</td></tr>
            <tr><th>Sunday</th><td>7:00 AM -- 8:00 PM</td></tr>
        </table>
    </div>
</main>

<footer class="footer"><div class="container"><strong>FitCore Gym</strong> &copy; 2026</div></footer>
</body>
</html>
