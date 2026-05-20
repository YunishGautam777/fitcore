<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="auth-wrap">
    <div class="card auth-card" style="max-width:560px">
        <h2>Join FitCore</h2>
        <p class="text-center text-muted mb">Create a member account (admin will approve)</p>

        <c:if test="${not empty error}"><div class="alert alert-error">${error}</div></c:if>

        <form method="post" action="${pageContext.request.contextPath}/register">
            <div class="form-row">
                <div class="form-group"><label>Full Name</label><input type="text" name="name" required></div>
                <div class="form-group"><label>Date of Birth</label><input type="date" name="dob"></div>
            </div>
            <div class="form-row">
                <div class="form-group"><label>Email</label><input type="email" name="email" required></div>
                <div class="form-group"><label>Contact</label><input type="text" name="contact"></div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label>Gender</label>
                    <select name="gender"><option>MALE</option><option>FEMALE</option><option>OTHER</option></select>
                </div>
                <div class="form-group">
                    <label>Membership Type</label>
                    <select name="membership_type">
                        <option>Monthly Basic</option>
                        <option>Quarterly Pro</option>
                        <option>Annual Elite</option>
                    </select>
                </div>
            </div>
            <div class="form-group"><label>Address</label><input type="text" name="address"></div>
            <div class="form-group"><label>Fitness Goal</label><input type="text" name="fitness_goal" placeholder="e.g. Lose 5 kg"></div>
            <hr style="border-color:var(--border);margin:1rem 0">
            <div class="form-row">
                <div class="form-group"><label>Username</label><input type="text" name="username" required></div>
                <div class="form-group"><label>Password</label><input type="password" name="password" required minlength="6"></div>
            </div>
            <button class="btn" style="width:100%">Register</button>
        </form>

        <p class="text-center mt">Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>
    </div>
</div>

</body>
</html>
//committed by fitcore on 2024-06-15 10:30:00