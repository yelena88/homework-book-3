<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- JSTL Library --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
<head>
    <title>JDBC to JSP Ex. 15</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-container w3-deep-orange">
    <h1>Expenses table:</h1>
</div>

<table class="w3-table-all w3-centered">
    <tr>
        <th>Id</th>
        <th>Pay Date</th>
        <th>Sum</th>
        <th>Receiver</th>
    </tr>

    <%-- Get our DB data from our request --%>
    <c:forEach var="row" items="${result}">
        <tr>
            <td><c:out value="${row.num}"/></td>
            <td><c:out value="${row.paydate}"/></td>
            <td><c:out value="${row.receiver}"/></td>
            <td><c:out value="${row.value}"/></td>
        </tr>
    </c:forEach>

</table>
<div class="w3-container w3-deep-orange w3-opacity w3-center-align w3-padding">
    <table class="w3-table w3-centered">
        <tr>
            <%-- Add our add and delete expenses buttons --%>
            <td>
                <button class="w3-btn w3-lime w3-round-large" style="width: 100%"
                        onclick="location.href='/myapps/AddExpenses'">Add Expenses
                </button>
            </td>
            <td>
                <button class="w3-btn w3-lime w3-round-large" style="width: 100%"
                        onclick="location.href='/myapps/DeleteExpenses'">Delete Expenses
                </button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
