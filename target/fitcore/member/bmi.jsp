<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BMI Calculator -- FitCore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="app">
    <%@ include file="/WEB-INF/views/common/sidebar-member.jspf" %>
    <main class="main">
        <h1>BMI Calculator</h1>
        <p class="text-muted">Quick body-mass-index check. Runs entirely in your browser.</p>

        <div class="card mt" style="max-width:480px">
            <div class="form-group"><label>Height (cm)</label><input type="number" id="bmiHeight" min="50" max="250"></div>
            <div class="form-group"><label>Weight (kg)</label><input type="number" id="bmiWeight" min="20" max="300"></div>
            <button class="btn" onclick="calcBMI()">Calculate</button>
            <p id="bmiResult" class="mt"></p>

            <div class="mt" style="font-size:.85rem;color:var(--muted)">
                <p>&lt; 18.5 -- Underweight</p>
                <p>18.5 -- 24.9 -- Normal</p>
                <p>25 -- 29.9 -- Overweight</p>
                <p>&ge; 30 -- Obese</p>
            </div>
        </div>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
