<%--
  Created by IntelliJ IDEA.
  User: nindy ilhami
  Date: 12/25/2017
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cari Kado User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <script src="bootstrap-3.3.7-dist/jquery/jquery-3.1.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="navbar-brand">
        <img src="/assets/admin/fullicon1.png" width="170" height="40" style="margin-bottom:10px">
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <li style="margin: 2px">
                <a href="/dashboard/admin">HOME<span class="glyphicon"></span></a>
            </li>
            <li style="margin: 2px; background-color: #fbcd30;">
                <a href="/logout">LOGOUT<span class="glyphicon"></span></a>
            </li>
        </ul>
    </div>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="ic_icon_boneka_slider.png">
        </div>

        <div class="item">
            <img src="ic_icon_boneka_slider3.png">
        </div>

        <div class="item">
            <img src="ic_icon_boneka_slider2.png">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="container" style="background: #ffffff; margin-top: 50px; margin-left: 100px;">
    <div class="row">
        <div class="col-md-3">
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
        </div>
        <div class="col-md-3">
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
        </div>
        <div class="col-md-3">
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
        </div>
        <div class="col-md-3">
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
            <p style="background: #f18887; padding: 130px; border-radius: 10px;"></p><br><br>
        </div>

    </div>
</div>
</body>
</html>
