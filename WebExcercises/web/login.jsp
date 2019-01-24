<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
<head>
    <title>login</title>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-container w3-deep-orange">
    <h1>Login</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <form method="post" action="j_security_check" class="w3-selection w3-light-grey w3-padding">
            <label>Name:
                <input type="text" name="j_username" class="w3-input w3-border w3-round-large" style="width: auto"><br/>
            </label>
            <label>Password:
                <input type="password" name="j_password" class="w3-input w3-border w3-round-large"
                       style="width: auto"><br/>
            </label>
            <button type="submit" class="w3-btn w3-lime w3-round-large w3-margin-bottom">Login</button>
        </form>
    </div>
</div>
</body>
</html>