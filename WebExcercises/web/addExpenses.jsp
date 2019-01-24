<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
<head>
    <title>Add Expenses</title>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-container w3-deep-orange">
    <h1>Add Expenses</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <form method="post" action="AddExpenses" class="w3-selection w3-light-grey w3-padding">
            <label>Pay Date:
                <input type="date" name="paydate" class="w3-input w3-border w3-round-large"
                       style="width: auto"><br/>
            </label>
            <label>Receiver:
                <input type="number" name="receiver" class="w3-input w3-border w3-round-large"
                       style="width: auto"><br/>
            </label>
            <label>Value:
                <input type="number" name="value" class="w3-input w3-border w3-round-large"
                       style="width: auto"><br/>
            </label>
            <button type="submit" class="w3-btn w3-lime w3-round-large w3-margin-bottom">Add</button>
        </form>
    </div>
</div>

<div class="w3-container w3-deep-orange w3-opacity w3-center-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/myapps/jdbc-jsp'">Home</button>
</div>
</body>
</html>