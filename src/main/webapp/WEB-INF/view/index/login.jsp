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

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="/css/login.css"/>

</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="navbar-brand">
        <img src="/assets/admin/fullicon1.png" width="176" height="47" style="margin-bottom:10px">
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <li style="margin:4px"><a href="#">INFORMASI KADO</a></li>
            <li style="margin:4px"><a href="#">REVIEW</a></li>
        </ul>
    </div>
</nav>
    <div class="container" style="background: #ffffff; margin-top: 100px; margin-left: 150px;">
        <br>
        <br>
        <br>
        <div class="row">
            <div class="col-md-6">
                <img src="/assets/login/ic_icon_logo_admin.png" style="width:358px; height:454px;">
            </div>
            <div class="col-md-6">

                <div id="myCarousel" class="carousel slide" data-ride="carousel">

                    <div class="carousel-inner">
                        <div class="item active">
                            <center><img src="/assets/login/ic_icon_login_admin.png" style="width:309px; height:121px;" alt="Login Admin"></center>
                        </div>

                        <div class="item">
                            <center><img src="/assets/login/ic_icon_login_author.png" style="width:309px; height:121px;" alt="Login Author">
                        </div>

                        <div class="item">
                            <center><img src="/assets/login/ic_icon_login_author.png" style="width:309px; height:121px;" alt="Login Customer Service">
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

                <br>
                <center><b>Please Login with Your Account here. Swipe for more</center>
                <br>

                <h2>${message}</h2>
                <form method="post" action="/login">

                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="email" class="form-control" name="userEmail" placeholder="Masukkan Email">
                </div>
                <br>
                <br>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" class="form-control" name="userPassword" placeholder="Masukkan Password">
                </div>
                <br>
                <br>
                <div align="center">
                    <button type="Submit" value="login" class="btn btn-info" >Login</button>
                </div>
            </div>
        </div>

        <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
