<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Author</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="/css/index.css"/>
</head>
<body>
<nav class="navbar navbar-fixed-top" style="background: #A91E31;">
    <div class="navbar-brand">
        <img src="/assets/admin/fullicon1.png" width="160" height="30" style="margin:1px">
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right">
            <li style="margin: 4px">
                <a href="/dashboard/author">HOME<span class="glyphicon"></span></a>
            </li>
            <li style="margin: 4px; background-color: #fbcd30;">
                <a href="/logout">LOGOUT<span class="glyphicon"></span></a>
            </li>
        </ul>
    </div>
</nav>

<div class="container" style="background: #ffffff; margin-top: 80px; margin-left: 80px;">
    <br>
    <br>

    <div class="row">
        <div align="center">
            <img src="/assets/author/ic_icon_author_welcome.png" style="width:300px; height:100px;">
        </div>
        <br>
        <div align="center">
            <img src="/assets/author/ic_icon_author.png" style="width:300px; height:350px;">
        </div>

        <br>

        <center>
            <a href="/dashboard/author/gift-info-category/1">
                <button class="button btn btn-alert menu-dashboard">GIFT INFO CATEGORY</button>
            </a>
        </center>
        <center>
            <a href="/dashboard/author/gift-info/1">
                <button class="button btn btn-alert menu-dashboard">GIFT INFO</button>
            </a>
        </center>
    </div>
</div>

<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
