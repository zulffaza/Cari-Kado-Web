<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 10/17/2017
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin - Add Role</title>
</head>
<body>
    <form method="post" action="/dashboard/admin/add/role">
        <input name="roleId" type="number" value="${role.id}" hidden="hidden" />

        <table>
            <tr>
                <td>Nama :</td>
                <td><input name="roleName" type="text" value="${role.name}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${empty role.id}">
                            <input class="btn btn-success" type="submit" value="Add">
                        </c:when>
                        <c:otherwise>
                            <input class="btn btn-success" type="submit" value="Edit">
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
