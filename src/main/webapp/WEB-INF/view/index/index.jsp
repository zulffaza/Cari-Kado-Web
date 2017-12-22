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

    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="container-fluid">
        <div class="navbar-brand">
            <img src="assets/ic_icon_logo_cari_kado.png" width="176" height="47">
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
    <br>
    <br>
    <br>
    <div class="row">
        <div class="col-md-6">
            <a href="login_admin">Login Admin<img src="assets/ic_icon_logo_admin.png" style="width:358px; height:454px;"></a>
        </div>
        <div class="col-md-6">
            <a href="login_author">Login Author<img src="assets/ic_icon_logo_author.png" style="width:358px; height:454px;"></a>
        </div>
    </div>
</div>

    <script src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>
