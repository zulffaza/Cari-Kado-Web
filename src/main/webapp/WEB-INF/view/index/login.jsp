<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 9/26/2017
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
</head>
<body>
    <h2>${message}</h2>
    <form method="post" action="/login">
        <table>
            <tr>
                <td>Email :</td>
                <td><input name="userEmail" type="email"/></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input name="userPassword" type="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>

    <script src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
