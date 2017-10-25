<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 10/17/2017
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Role</title>
</head>
<body>
    <h3>${page}</h3>
    <h3>${pageSize}</h3>
    <h3>${sort}</h3>

    <c:forEach items="${roles}" var="role">
        <h2>${role.name}</h2>
        <a href="/dashboard/admin/delete/role/${role.id}">
            <h3>Hapus</h3>
        </a>
        <br>
    </c:forEach>
</body>
</html>
