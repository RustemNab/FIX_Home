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
    <form method="post" action="/edit">

            <c:forEach var="editItem" items="${editItem}">
                <form action="/edit" method="post">
                    <fieldset class="clearfix">
                    <div class="login-input">
                        <c:out value="${editItem.id}" />
                    </div>
                    <input type="hidden" value="${editItem.id}" name="id_">
                    <input type="text" class="login-input" value="${editItem.name}" name="name_" />
                    <input type="text" class="login-input" value="${editItem.price}" name="price_" />
                    <button type="submit">SAVE</button>
                    </fieldset>
                </form>
            </c:forEach>

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