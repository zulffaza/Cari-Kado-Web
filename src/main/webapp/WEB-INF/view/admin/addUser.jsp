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
            <c:when test="${empty userModel.id}">
                <title>Admin - Add User</title>
            </c:when>
            <c:otherwise>
                <title>Admin - Edit User</title>
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
                <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - <a href="/dashboard/admin/user/1">USER</a></b>
            </p>

            <hr>

            <c:if test="${not empty message}">
                <div class="alert alert-info">
                    <strong>Info!</strong> ${message}
                </div>
            </c:if>

            <br>

            <form:form class="form-horizontal" name="addUser" method="post" action="/dashboard/admin/user/add">
                <input name="userId" type="number" value="${userModel.id}" hidden="hidden" />
                <input name="userNameId" type="number" value="${userModel.userName.id}" hidden="hidden" />
                <input name="userAddressId" type="number" value="${userModel.userAddress.id}" hidden="hidden" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-first-name">First Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user-first-name" name="userFirstName" placeholder="*Enter user first name" value="${userModel.userName.firstName}"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-middle-name">Middle Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user-middle-name" name="userMiddleName" placeholder="Enter user middle name" value="${userModel.userName.middleName}"
                            <c:if test="${not empty userModel.id}">
                                   readonly="readonly"
                            </c:if>
                        >
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-last-name">Last Name :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user-last-name" name="userLastName" placeholder="*Enter user last name" value="${userModel.userName.lastName}"
                            <c:if test="${not empty userModel.id}">
                                   readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <br>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-address-country">Negara :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-address-country" name="userAddressCountryId" onchange="onCountriesChange(${userModel.id})"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
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
                    <label class="control-label col-sm-2" for="user-address-province">Provinsi :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-address-province" name="userAddressProvinceId" onchange="onProvincesChange(${userModel.id})"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
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
                    <label class="control-label col-sm-2" for="user-address-city">Kota :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-address-city" name="userAddressCityId" onchange="onCitiesChange(${userModel.id})"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
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
                    <label class="control-label col-sm-2" for="user-address-district">Kecamatan :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-address-district" name="userAddressDistrictId" onchange="onDistrictsChange(${userModel.id})"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
                            <c:forEach items="${districts}" var="district" varStatus="each">
                                <c:choose>
                                    <c:when test="${districtId == district.id}">
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
                    <label class="control-label col-sm-2" for="user-address-sub-district">Kelurahan :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-address-sub-district" name="userAddressSubDistrictId"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
                            <c:forEach items="${subDistricts}" var="subDistrict" varStatus="each">
                                <c:choose>
                                    <c:when test="${userModel.userAddress.subDistrict.id == subDistrict.id}">
                                        <option value="${subDistrict.id}" selected="selected">${subDistrict.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${subDistrict.id}">${subDistrict.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-address-street">Alamat :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user-address-street" name="userAddressStreet" placeholder="*Enter user address street" value="${userModel.userAddress.street}"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-address-hamlet">RW :</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control" id="user-address-hamlet" name="userAddressHamlet" placeholder="Enter user address hamlet" value="${userModel.userAddress.hamlet}"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-address-neighbourhood">RT :</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control" id="user-address-neighbourhood" name="userAddressNeighbourhood" placeholder="Enter user address neighbourhood" value="${userModel.userAddress.neighbourhood}"
                            <c:if test="${not empty userModel.id}">
                                readonly="readonly"
                            </c:if>
                        >
                    </div>
                </div>

                <br>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-email">Email :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="user-email" name="userEmail" placeholder="*Enter user email" value="${userModel.email}"
                            <c:if test="${not empty userModel.id}">
                                   readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-password">Password :</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="user-password" name="userPassword" placeholder="*Enter user password" value="${userModel.password}"
                            <c:if test="${not empty userModel.id}">
                               readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-phone">Phone :</label>
                    <div class="col-sm-10">
                        <input type="number" min="0" class="form-control" id="user-phone" name="userPhone" placeholder="*Enter user phone" value="${userModel.phone}"
                            <c:if test="${not empty userModel.id}">
                               readonly="readonly"
                            </c:if>
                        required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-role">Role :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-role" name="userRoleId">
                            <c:forEach items="${roles}" var="role" varStatus="each">
                                <c:choose>
                                    <c:when test="${userModel.role.id == role.id}">
                                        <option value="${role.id}" selected="selected">${role.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${role.id}">${role.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="user-status">Status :</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="user-status" name="userStatus">
                            <c:forEach items="${userStatuses}" var="userStatus" varStatus="each">
                                <c:choose>
                                    <c:when test="${userModel.status == userStatus}">
                                        <option value="${userStatus}" selected="selected">${userStatus}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${userStatus}">${userStatus}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <br>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${empty countries || empty provinces || empty cities || empty districts
                             || empty subDistricts || empty roles || empty userStatuses}">
                                <div class="alert alert-warning">
                                    Tidak dapat menambahkan user baru
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty userModel.id}">
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
        <script src="/js/admin/addUser.js"></script>
    </body>
</html>
