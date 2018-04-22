<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 13.03.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <style type="text/css">
        <%@include file="../css/styles.css"%>
    </style>
</head>
<body>
    <div class="form-style-2-heading" align="center">
        All items!
    </div>
<table align="center">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
</tr>
    <c:forEach var="product" items="${productsArray}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.price}"/></td>
                <input type="hidden" value="${product.id}" name="id_"></td>
                <td><input type="submit" value="ред."></td>
                <td><a href="${pageContext.request.contextPath}/delete?id_=${product.id}">Удалить</a></td>
            </tr>
    </c:forEach>
</table>
    <h3>
        <div align="center">
            <a href="/add">Add new item</a>
            &nbsp;&nbsp;&nbsp;
            <!--<a href="/products">List all products</a>-->
        </div>
    </h3>

</body>
</html>