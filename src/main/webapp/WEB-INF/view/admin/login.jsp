<%--
  Created by IntelliJ IDEA.
  User: nindy ilhami
  Date: 11/8/2017
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Admin</title>

    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="css/index.css">
</head>

<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="container-fluid">
        <div class="navbar-brand">
            <img src="ic_icon_logo_cari_kado.png" width="176" height="47">
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li style="margin:2px"><a href="informasi_kado">INFORMASI KADO <span class="glyphicon glyphicon-upload"></span></a></li>
                <li style="margin:2px"><a href="review">REVIEW <span class="glyphicon glyphicon-user"></span></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <img src="ic_icon_logo_admin.png" style="width:358px; height:454px;">
        </div>
        <div class="col-md-6">
            <img align="center" src="ic_icon_login_admin.png" style="width:409px; height:121px;"></center>
            <br>
            <p align="center"><b>Please Login with Your Account here.</b></p>
            <br>
            <span class="glyphicon glyphicon-user"></span>
            <input type="text" class="form-control" id="username" placeholder="Nama atau Username">
            <br>
            <span class="glyphicon glyphicon-key"></span>
            <input type="text" class="form-control" id="password" placeholder="Password">
            <br>
            <button type="login" class="btn btn-alert" >Login</button>
        </div>
    </div>
</div>
</body>
</html>
