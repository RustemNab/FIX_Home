<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 20.03.2018
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <style type="text/css">
        <%@include file="css/styles.css"%>
    </style>
</head>
<body>
<div id="login">
    <form method="post" action="/login">
        <fieldset class="clearfix">
        <label for="name">Login
            <p><span class="fontawesome-user"></span><input class="input-field" type="text" id="name" name="name"></p>
        </label>
        <label for="password">Password
            <p><span class="fontawesome-lock"></span><input class="input-field" type="password" id="password" name="password"></p>
        </label>
        <input type="submit" value="Sign Up">
        </fieldset>
    </form>
    <p>Нет аккаунта? &nbsp;&nbsp;<a href="/signup">Регистрация</a><span class="fontawesome-arrow-right"></span></p>
</div>
</body>
</html>
