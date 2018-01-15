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
            <c:when test="${empty city.id}">
                <title>Admin - Add City</title>
            </c:when>
            <c:otherwise>
                <title>Admin - Edit City</title>
            </c:otherwise>
        </c:choose>

        <c:url var="form_action" value="/dashboard/admin/city/add"/>

        <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>" />
        <link rel="stylesheet" href="<c:url value="/webjars/font-awesome/4.7.0/css/font-awesome.min.css"/>" />
        <link rel="stylesheet" href="<c:url value="/css/index.css"/>" />
    </head>
    <body>
        <nav class="navbar navbar-fixed-top" style="background: #A91E31;">
                <div class="navbar-brand">
                    <img src="<c:url value="/assets/admin/fullicon1.png"/>" width="160" height="30" style="margin:1px">
                </div>
                <div>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="margin: 4px">
                            <a href="<c:url value="/dashboard/admin"/>">HOME<span class="glyphicon"></span></a>
                        </li>
                        <li style="margin: 4px; background-color: #fbcd30;">
                            <a href="<c:url value="/logout"/>">LOGOUT<span class="glyphicon"></span></a>
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
                <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - <a href="<c:url value="/dashboard/admin/city/1"/>">CITY</a></b>
            </p>

            <hr>

            <c:if test="${not empty message}">
                <div class="alert alert-info">
                    <strong>Info!</strong> ${message}
                </div>
            </c:if>

            <br>

            <form:form class="form-horizontal" name="addCity" method="post" action="${form_action}">
                <input name="cityId" type="number" value="${city.id}" hidden="hidden" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="countries">Negara :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="countries" name="countryId" onchange="onCountriesChange(${city.id})">
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
                        <select class="form-control" id="provinces" name="provinceId">
                            <c:forEach items="${provinces}" var="province" varStatus="each">
                                <c:choose>
                                    <c:when test="${city.province.id == province.id}">
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
                    <label class="control-label col-sm-2" for="city-name">Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="city-name" name="cityName" placeholder="Enter city name" value="${city.name}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${empty countries || empty provinces}">
                                <div class="alert alert-warning">
                                    Tidak dapat menambahkan kota baru
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty city.id}">
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

        <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
        <script src="<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/js/admin/addCity.js"/>"></script>
    </body>
</html>
