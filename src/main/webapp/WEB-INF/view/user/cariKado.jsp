<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nindy ilhami
  Date: 12/25/2017
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Cari Kado</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"/>">
    <script src="<c:url value="/bootstrap-3.3.7-dist/jquery/jquery-3.1.1.min.js"/>"></script>
    <script src="<c:url value="/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="navbar-brand">
        <img src="<c:url value="/assets/admin/fullicon1.png"/>" width="160" height="30">
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <li style="margin:4px"><a href="<c:url value="/informasi_kado"/>">INFORMASI KADO</a></li>
            <li style="margin:4px"><a href="<c:url value="/review"/>">REVIEW</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="background: #ffffff; margin-top: 100px; margin-left: 150px;">
    <br>
    <br>
    <div class="row">
        <div class="col-md-6">
            <img src="<c:url value="/assets/admin/ic_icon_logo_admin.png"/>" style="width:358px; height:454px;">
        </div>
        <div class="col-md-6" align="center">
            <center><b>Please enter your spesification</center>
            <br>
            <br>
            <div class="input-group">
                <p align="center">Usia</p>
                <input id="age" type="text" class="form-control" name="age">
            </div>
            <br>
            <div align="center" class="dropdown">
                <p align="center">Jenis Kelamin</p>
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Jenis Kelamin
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/#"/>">Female</a></li>
                    <li><a href="<c:url value="/#"/>">Male</a></li>
                </ul>
            </div>
            <br>
            <div class="input-group">
                <p align="center">Budget</p>
                <input id="budget" type="text" class="form-control" name="budget">
            </div>
            <br>
            <div align="center" class="dropdown">
                <p align="center">Kategori</p>
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Kategori
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/#"/>">Bunga</a></li>
                    <li><a href="<c:url value="/#"/>">Boneka</a></li>
                    <li><a href="<c:url value="/#"/>">Jam Tangan</a></li>
                </ul>
            </div>
            <br>
            <div align="center">
                <input style="background: #A91E31; border: 0; font-weight: bold; font-color: #ffffff;" type="button" class="btn btn-alert" value="Submit" /></a>
            </div>
        </div>
    </div>
</body>
</html>
