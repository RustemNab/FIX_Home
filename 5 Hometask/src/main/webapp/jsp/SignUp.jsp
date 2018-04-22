<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 20.03.2018
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        <%@include file="../css/styles.css"%>
    </style>
</head>
<body>
<div id="login">
    <h1 align="center">
        Please Sign Up!
    </h1>
    <form method="post" action="/signup">
        <fieldset class="clearfix">
        <label for="name">User name
            <p><span class="fontawesome-user"></span><input class="input-field" type="text" id="name" name="name_"></p>
        </label>
        <label for="login_">Login
            <p><span class="fontawesome-user"></span><input class="input-field" type="text" id="login_" name="login_"></p>
        </label>
        <label for="password">Password
            <p><span class="fontawesome-user"></span><input class="input-field" type="password" id="password" name="password_"></p>
        </label>
        <input type="submit" value="Submit">
        </fieldset>
    </form>
</div>

</body>
</html>