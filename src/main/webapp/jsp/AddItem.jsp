<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        <%@include file="../css/styles.css"%>
    </style>
</head>
<body>
<div id="login">
    <form method="post" action="/add">
        <fieldset class="clearfix">
            <label for="name">Item name
                <p><span class="fontawesome-user"></span><input class="input-field" type="text"
                                                                id="name" name="name"></p>
            </label>
            <label for="price">Price
                <p><span class="fontawesome-user"></span><input class="input-field" type="text"
                                                                id="price" name="price"></p>
            </label>
            <input type="submit" value="Save">
        </fieldset>
    </form>
<div class="form-style-2" style="text-align: center;">
    <h2>
        &nbsp;&nbsp;&nbsp;
        <a href="/products">All items</a>

    </h2>
</div>
</div>
</body>
</html>