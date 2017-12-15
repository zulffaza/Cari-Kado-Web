<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 10/17/2017
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Admin</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="/css/index.css" />
    </head>
    <body>
        <nav class="navbar navbar-fixed-top" style="background: #A91E31;">
            <div class="container-fluid" style="background: #A91E31;">
                <div class="navbar-brand">
                    <img src="/assets/admin/fullicon1.png" width="176" height="47" style="margin:1px">
                </div>
                <div>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="margin: 4px">
                            <a href="/dashboard/admin">HOME<span class="glyphicon"></span></a>
                        </li>
                        <li style="margin: 4px; background-color: #fbcd30;">
                            <a href="/logout">LOGOUT<span class="glyphicon"></span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container" style="background: #ffffff; margin-top: 80px;">
            <br>
            <br>
            <br>

            <div class="row">
                <br>
                <br>

                <center>
                    <img src="/assets/admin/email.png" style="width:160px; height:145px;">
                </center>

                <br>

                <center>
                    <a href="/dashboard/admin/role/1">
                        <button class="button btn btn-alert menu-dashboard">ROLE</button>
                    </a>
                </center>
                <center>
                    <button class="button btn btn-alert menu-dashboard">USER</button>
                </center>
                <center>
                    <button class="button btn btn-alert menu-dashboard">COUNTRY</button>
                </center>
                <center>
                    <button class="button btn btn-alert menu-dashboard">CITY</button>
                </center>
                <center>
                    <button class="button btn btn-alert menu-dashboard">DISTRICT</button>
                </center>
                <center>
                    <button class="button btn btn-alert menu-dashboard">SUB DISTRICT</button>
                </center>
            </div>
        </div>

        <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </body>
</html>
