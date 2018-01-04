<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Halimyr8
  Date: 12/19/2017
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <c:choose>
            <c:when test="${empty giftInfo.id}">
                <title>Author - Add Gift Info</title>
            </c:when>
            <c:otherwise>
                <title>Author - Edit Gift Info</title>
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
                <b>${user.userName.firstName} ${user.userName.middleName} ${user.userName.lastName} - <a href="/dashboard/author/gift-info/1">GIFT INFO</a></b>
            </p>

            <hr>

            <c:if test="${not empty message}">
                <div class="alert alert-info">
                    <strong>Info!</strong> ${message}
                </div>
            </c:if>

            <br>

            <form:form class="form-horizontal" name="addGiftInfo" method="post" action="/dashboard/author/gift-info/add">
                <input name="giftInfoId" type="number" value="${giftInfo.id}" hidden="hidden" />
                <input name="giftInfoAgeId" type="number" value="${giftInfo.giftInfoAge.id}" hidden="hidden" />
                <input name="giftInfoBudgetId" type="number" value="${giftInfo.giftInfoBudget.id}" hidden="hidden" />

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-categories">Kategori :</label>
                    <div id="gift-info-categories" class="col-sm-10">
                        <c:forEach items="${giftInfoCategories}" var="giftInfoCategory" varStatus="each">
                            <c:choose>
                                <c:when test="${giftInfo.giftInfoCategories.contains(giftInfoCategory)}">
                                    <div class="checkbox">
                                        <input type="checkbox" name="giftInfoCategoryId" value="${giftInfoCategory.id}" checked="checked">
                                            ${giftInfoCategory.name}
                                        <br>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="checkbox">
                                        <input type="checkbox" name="giftInfoCategoryId" value="${giftInfoCategory.id}">
                                            ${giftInfoCategory.name}
                                        <br>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>

                <br>
                <br>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-title">Judul :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="gift-info-title" name="giftInfoTitle" placeholder="Enter gift info title" value="${giftInfo.title}" required="required">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-description">Deskripsi :</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="gift-info-description" name="giftInfoDescription" required="required">${giftInfo.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-essence">Esensi :</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="gift-info-essence" name="giftInfoEssence" required="required">${giftInfo.essence}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-ageFrom">Umur :</label>
                    <div id="gift-info-ageFrom">
                        <div class="col-sm-4">
                            <input type="number" min="1" class="form-control" name="giftInfoAgeFrom" value="${giftInfo.giftInfoAge.from}" required="required">
                        </div>
                        <div class="col-sm-1">
                            sampai
                        </div>
                        <div class="col-sm-4">
                            <input type="number" min="1" class="form-control" name="giftInfoAgeTo" value="${giftInfo.giftInfoAge.to}" required="required">
                        </div>
                        <div class="col-sm-1">
                            tahun
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="gift-info-budget">Harga :</label>
                    <div id="gift-info-budget">
                        <div class="col-sm-4">
                            <input type="number" min="1" class="form-control" name="giftInfoBudgetFrom" value="${giftInfo.giftInfoBudget.from}" required="required">
                        </div>
                        <div class="col-sm-1">
                            sampai
                        </div>
                        <div class="col-sm-4">
                            <input type="number" min="1" class="form-control" name="giftInfoBudgetTo" value="${giftInfo.giftInfoBudget.to}" required="required">
                        </div>
                        <div class="col-sm-1">
                            rupiah
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:choose>
                            <c:when test="${empty giftInfoCategories}">
                            <div class="alert alert-warning">
                                Tidak dapat menambahkan info kado baru
                            </div>
                        </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty giftInfo.id}">
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
    </body>
</html>
