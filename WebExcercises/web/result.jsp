<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
<head>
    <title>Result</title>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-deep-orange">
    <h1>Result</h1>
</div>

<div class="w3-container w3-center">
    <h2>
        <c:out value="${requestScope.message}"/>
    </h2>
</div>

<div class="w3-container w3-deep-orange w3-opacity w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/myapps/jdbc-jsp'">Back to Main Table</button>
</div>
</body>
</html>