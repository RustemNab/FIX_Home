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

<!DOCTYPE html>

<html>
<body>
<table width="40%" border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
</tr>
    <c:forEach var="tovar" items="${tovaryArray}">
            <tr>
                <td><c:out value="${tovar.id}"/></td>
                <td><c:out value="${tovar.name}"/></td>
                <td><c:out value="${tovar.price}"/></td>
            </tr>
    </c:forEach>

</table>
</body>
</html>