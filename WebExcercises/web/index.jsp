<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="indexStyle.css" type="text/css">
    <title>Заполните форму</title>
</head>
<body>
<h1>Заполните форму</h1>

<form method="post" action="formHandler" title="Заполните и отправьте форму" novalidate>
    <small>Внимание! Обязательны для заполнения: поле &#171;Имя&#187;, а также хотя бы одно из полей &#171;Телефон&#187; и
        &#171;E-mail&#187;</small><BR><BR>
    <fieldset>
        <label>Сведения о пользователе:</label><br/><br/>
        <input type="text" name="username" pattern="^[А-ЯЁA-Z][А-ЯЁа-яё A-Za-z]+$" id="name_input" size="25" maxlength="128"
               placeholder="Введите Имя" required>
        <label for="name_input">* Имя</label><BR>
        <input type="text" name="phoneN" size="25" maxlength="15" placeholder="+375-XX-XXXXXXX" id="phone_input"
               pattern="\+375[- ]*(29|44|33|25)[- ]*[0-9]{7}">
        <label for="phone_input">Телефон (в формате: +375-XX-XXXXXXX)</label><BR>
        <input type="email" name="e-mail" size="25" placeholder="e-mail" id="email_input" maxlength="128">
        <label for="email_input">E-mail (в формате: user@mail.ru)</label><BR>
        <I>* - Обязательное поле</I><BR>
        <input type="submit" name="send" id="button_send" value="Отправить">
        <input type="reset" name="reset" id="button_reset" value="Сбросить">
    </fieldset>
</form>
</body>
</html>
