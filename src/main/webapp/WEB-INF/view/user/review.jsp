<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Halimyr8
  Date: 12/25/2017
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<html>
<head>
    <title>www.CariKado.com</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"/>">
    <script src="<c:url value="/bootstrap-3.3.7-dist/jquery/jquery-3.1.1.min.js"/>"></script>
    <script src="<c:url value="/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/text.css"/>">
    <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>">
</head>


<body>
<div class="container" style="background: #ffffff; margin-top: 100px; margin-left: 150px;">
    <br>
    <br>
    <br>
    <div class="row">
        <div class="col-md-6">
            <img src="<c:url value="logine2.png"/>" style="width:358px; height:454px;">
        </div>
        <div class="col-md-6">
            <center><img src="<c:url value="kepuasan.png"/>" style="width:409px; height:121px;"></center>
            <br>
            <center><b>Please rate our apps in here.</center>
            <br>
            <center><b>Nama</center>
            <input type="text" class="form-control" id="email">
            <br>
            <center><b>Email</center>
            <input type="email" class="form-control" id="email">
            <br>
            <center><b>Komentar</center>
            <input type="email" class="form-control" id="email">
            <br>
            <center><b>Rate</center>
            <center><span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span></center>
            <br>
            <center>
                <button class="button" type="submit" style="border-radius: 15px;" class="btn btn-alert"><i>Submit
                </button>
            </center>
        </div>
    </div>
</div>
</body>
</html>
