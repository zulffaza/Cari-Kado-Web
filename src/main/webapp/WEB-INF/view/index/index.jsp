<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 10/4/2017
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CariKado</title>

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/index.css"/>">
</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
        <div class="navbar-brand">
            <img src="<c:url value="/assets/admin/fullicon1.png"/>" width="160" height="30">
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li style="margin:4px"><a href="<c:url value="#"/>">INFORMASI KADO</a></li>
                <li style="margin:4px"><a href="<c:url value="#"/>">REVIEW</a></li>
            </ul>
        </div>
</nav>

<div class="container">
    <br>
    <br>
    <br>
    <div class="row">
        <div class="col-md-6">
            <a href="<c:url value="/login_admin"/>">Login Admin<img src="<c:url value="/assets/admin/ic_icon_logo_admin.png"/>" style="width:358px; height:454px;"></a>
        </div>
        <div class="col-md-6">
            <a href="<c:url value="/login_author"/>">Login Author<img src="<c:url value="/assets/author/ic_icon_logo_author.png"/>" style="width:358px; height:454px;"></a>
        </div>
    </div>
</div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/index.js"/>"></script>
</body>
</html>
