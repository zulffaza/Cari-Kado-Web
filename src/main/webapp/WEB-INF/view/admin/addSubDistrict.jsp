<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 12/19/2017
  Time: 03:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <c:choose>
            <c:when test="${empty subDistrict.id}">
                <title>Admin - Add Sub District</title>
            </c:when>
            <c:otherwise>
                <title>Admin - Edit Sub District</title>
            </c:otherwise>
        </c:choose>

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
        <br>
        <br>
        <br>
        <br>
        <br>

        <div class="container">
            <p style="font-size: 35px; color: #66060b;">
                <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - <a href="/dashboard/admin/sub-district/1">SUB DISTRICT</a></b>
            </p>

            <hr>

            <c:if test="${not empty message}">
                <div class="alert alert-info">
                    <strong>Info!</strong> ${message}
                </div>
            </c:if>

            <br>

            <form:form class="form-horizontal" name="addSubDistrict" method="post" action="/dashboard/admin/sub-district/add">
                <input name="subDistrictId" type="number" value="${subDistrict.id}" hidden="hidden" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="countries">Negara :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="countries" name="countryId" onchange="onCountriesChange(${subDistrict.id})">
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
                        <select class="form-control" id="provinces" name="provinceId" onchange="onProvincesChange(${subDistrict.id})">
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
                        <select class="form-control" id="cities" name="cityId" onchange="onCitiesChange(${subDistrict.id})">
                            <c:forEach items="${cities}" var="city" varStatus="each">
                                <c:choose>
                                    <c:when test="${cityId == city.id}">
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
                    <label class="control-label col-sm-2" for="districts">Kecamatan :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="districts" name="districtId">
                            <c:forEach items="${districts}" var="district" varStatus="each">
                                <c:choose>
                                    <c:when test="${subDistrict.district.id == district.id}">
                                        <option value="${district.id}" selected="selected">${district.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${district.id}">${district.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="sub-district-name">Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="sub-district-name" name="subDistrictName" placeholder="Enter sub district name" value="${subDistrict.name}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${empty countries || empty provinces || empty cities || empty districts}">
                                <div class="alert alert-warning">
                                    Tidak dapat menambahkan kelurahan baru
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty subDistrict.id}">
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
        <script src="/js/admin/addSubDistrict.js"></script>
    </body>
</html>
