<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Halimyr8
  Date: 12/18/2017
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Admin - Sub District</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css">
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

        <br>

        <div class="container-fluid" style="background: #ffffff; margin-top: 120px; margin-left: 20px; margin-right: 20px;">
            <div class="row">
                <div class="col-md-12">
                    <p style="font-size: 35px; color: #66060b;">
                        <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - SUB DISTRICT</b>
                    </p>

                    <hr>

                    <c:if test="${not empty message}">
                        <div class="alert alert-info">
                            <strong>Info!</strong> ${message}
                        </div>
                    </c:if>

                    <br>

                    <a href="/dashboard/admin/sub-district/add">
                        <button class="btn btn-success btn-xs" style="width: 80px; height: 30px;">
                            <i aria-hidden="true" class="fa fa-plus"></i> Tambah
                        </button>
                    </a>
                </div>

                <%--<form method="GET" action="#" accept-charset="UTF-8" role="search" class="navbar-form navbar-right">--%>
                <%--<div class="input-group">--%>
                <%--<input type="text" name="search" placeholder="Search..." value="" class="form-control">--%>

                <%--<span class="input-group-btn">--%>
                <%--<button type="submit" class="btn btn-default">--%>
                <%--<i class="fa fa-search"></i>--%>
                <%--</button>--%>
                <%--</span>--%>
                <%--</div>--%>
                <%--</form>--%>
            </div>

            <br>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Kecamatan</th>
                            <th>Kelurahan</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty subDistricts}">
                                <tr>
                                    <td colspan="4">
                                        Tidak ada data
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${subDistricts}" var="district" varStatus="each">
                                    <tr>
                                        <td>
                                                ${((page - 1) * 10) + each.index + 1}
                                        </td>
                                        <td>
                                                ${district.district.name}
                                        </td>
                                        <td>
                                                ${district.name}
                                        </td>
                                        <td>
                                            <a href="/dashboard/admin/sub-district/add/${district.id}">
                                                <button class="btn btn-primary btn-xs" style="width: 70px; height: 28px;">
                                                    <i aria-hidden="true" class="fa fa-pencil-square-o"></i> Update
                                                </button>
                                            </a>
                                            <a href="/dashboard/admin/sub-district/delete/${district.id}">
                                                <button class="btn btn-danger btn-xs" style="width: 70px; height: 28px;">
                                                    <i aria-hidden="true" class="fa fa-trash-o"></i> Delete
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <ul class="pagination">
                <c:if test="${page != 1}">
                    <li>
                        <a href="/dashboard/admin/sub-district/1">&laquo; first</a>
                    </li>
                    <li>
                        <a href="/dashboard/admin/sub-district/${page - 1}">&lsaquo; previous</a>
                    </li>
                </c:if>

                <li class="active">
                    <a href="/dashboard/admin/sub-district/${page}">${page}</a>
                </li>

                <c:if test="${lastPage != page}">
                    <li>
                        <a href="/dashboard/admin/sub-district/${page + 1}">next &rsaquo;</a>
                    </li>
                    <li>
                        <a href="/dashboard/admin/sub-district/${lastPage}">last &raquo;</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </body>
</html>
