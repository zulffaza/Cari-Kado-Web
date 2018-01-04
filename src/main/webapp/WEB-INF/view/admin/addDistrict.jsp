<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 10/17/2017
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <c:choose>
            <c:when test="${empty district.id}">
                <title>Admin - Add District</title>
            </c:when>
            <c:otherwise>
                <title>Admin - Edit District</title>
            </c:otherwise>
        </c:choose>

        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/css/index.css" />
    </head>
    <body>
        <nav class="navbar navbar-fixed-top" style="background: #A91E31;">
                <div class="navbar-brand">
                    <img src="/assets/admin/fullicon1.png" width="160" height="30" style="margin:1px">
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
        </nav>

        <br>
        <br>
        <br>
        <br>
        <br>
        <br>

        <div class="container">
            <p style="font-size: 35px; color: #66060b;">
                <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - <a href="/dashboard/admin/district/1">DISTRICT</a></b>
            </p>

            <hr>

            <c:if test="${not empty message}">
                <div class="alert alert-info">
                    <strong>Info!</strong> ${message}
                </div>
            </c:if>

            <br>

            <form:form class="form-horizontal" name="addDistrict" method="post" action="/dashboard/admin/district/add">
                <input name="districtId" type="number" value="${district.id}" hidden="hidden" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="countries">Negara :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="countries" name="countryId" onchange="onCountriesChange(${district.id})">
                            <c:forEach items="${countries}" var="country" varStatus="each">
                                <c:choose>
                                    <c:when test="${countryId == country.id}">
                                        <option value="${country.id}" selected="selected">${country.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${country.id}">${country.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="provinces">Provinsi :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="provinces" name="provinceId" onchange="onProvincesChange(${district.id})">
                            <c:forEach items="${provinces}" var="province" varStatus="each">
                                <c:choose>
                                    <c:when test="${provinceId == province.id}">
                                        <option value="${province.id}" selected="selected">${province.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${province.id}">${province.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="cities">Kota :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="cities" name="cityId">
                            <c:forEach items="${cities}" var="city" varStatus="each">
                                <c:choose>
                                    <c:when test="${district.city.id == city.id}">
                                        <option value="${city.id}" selected="selected">${city.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.id}">${city.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="district-name">Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="district-name" name="districtName" placeholder="Enter district name" value="${district.name}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${empty countries || empty provinces || empty cities}">
                                <div class="alert alert-warning">
                                    Tidak dapat menambahkan kecamatan baru
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty district.id}">
                                        <button type="submit" class="btn btn-success">Add</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-success">Edit</button>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </form:form>
        </div>

        <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        <script src="/js/admin/addDistrict.js"></script>
    </body>
</html>
