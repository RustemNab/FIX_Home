<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        <%@include file="css/styles.css"%>
    </style>
</head>
<body>
<div id="login" align="center">
    <c:if test="${tovar != null}">
    <form action="new" method="post">
        </c:if>
        <c:if test="${tovar == null}">
        <form action="add" method="post">
            </c:if>
                    <h2>
                        <c:if test="${tovar == null}">
                            Add new product
                        </c:if>
                    </h2>
                <c:if test="${tovar != null}">


                <fieldset class="clearfix">
                    <p><span class="fontawesome-user"></span><input type="hidden" name="id" value="<c:out value='${tovar.id}' />" /></p>
                </c:if>
            <label for="name">Item name
                <p><span class="fontawesome-user"></span><input id="name" type="text" name="name" size="45"
                       value="<c:out value='${tovar.name}' />"
                /></p>
            </label>
            <label for="price">Price
                <p><span class="fontawesome-user"></span><input id="price" type="text" name="price" size="45"
                       value="<c:out value='${tovar.price}' />"
                /></p>
            </label>
            <input type="submit" value="Save">
                </fieldset>
        </form>
</div>

<div class="form-style-2" style="text-align: center;">
    <h2>
        &nbsp;&nbsp;&nbsp;
        <a href="/tovary">All items</a>

    </h2>
</div>
</div>
</body>
</html>