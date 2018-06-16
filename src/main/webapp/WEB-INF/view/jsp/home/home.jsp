<%--
  Created by IntelliJ IDEA.
  User: raghvendra.mishra
  Date: 31/01/18
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>www.samadhaan.com</title>
    <link rel="stylesheet" type="text/css" href="/resources/core/css/home.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<!--header start-->
<nav class="navbar navbar-expand-md fixed-top navbar-light"
     style="padding-bottom: 0px; border-bottom: 1px solid #cc6600; background: white;
     box-shadow: 0px 0px 10px #bfbfbf;" id="navHeader">
    <a class="navbar-brand" href="#" style="">
        Samadhaan
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon" style=""></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <div class="mr-auto"></div>
        <ul class="navbar-nav" style="">
            <c:if test="${response.user.type == 'ADMIN'}">
                <li class="nav-item" style="padding-right: 10px;">
                    <a class="nav-link" href="/viewUsers" style="font-size: 14px; text-decoration: underline;">
                        view users
                    </a>
                </li>
                <li class="nav-item" style="padding-right: 10px;">
                    <a class="nav-link" href="/search" style="font-size: 14px; text-decoration: underline;">
                        search
                    </a>
                </li>
            </c:if>
            <li class="nav-item" style="padding-right: 10px; border-bottom: 3px solid #cc6600;">
                <a class="nav-link" href="/" style="color: #cc6600;">
                    <img src="/resources/image/application/home-or.png">
                    Home
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/profile" style="">
                    <img src="/resources/image/application/document-bl.png">
                    Profile
                </a>
            </li>
            <li class="nav-item" style="">
                <a class="nav-link" href="/signOut">
                    <img src="/resources/image/application/face-bl.png">
                    Sign Out
                </a>
            </li>
        </ul>
    </div>

</nav>
<!--header end-->
<div style="height: 100px;"></div>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-8 col-lg 8">
            <div>
                <div>
                    <h2>Hi ${response.user.fname} !!</h2>
                </div>
                <h3> Welcome to <span style="color: #cc6600;">samadhaan</span></h3>
            </div>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4">
            <div style="text-align: right;">
                <div>Upload new file</div>
                <div>
                    <form:form method="POST" action="/uploadFile" modelAttribute="uploadFileRequest"
                               enctype="multipart/form-data">
                        <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                        <form:input type="hidden" path="userId" id="userId" class="form-control input-sm"
                                    value="${response.userId}"/>
                        <input type="submit" class="inp-submit" value="UPLOAD"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div>Documents</div>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>File</th>
                        <th>Uploaded Date</th>
                        <th>Download</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="1" scope="page" />
                    <c:forEach items="${response.documents}" var="document">
                        <tr>
                            <th scope="row">${count}</th>
                            <td>${document.description}</td>
                            <td>${document.uploadDate}</td>
                            <td><a href="/downloadFile?id=${document.id}"><img src="/resources/image/application/download.png"> Download</a></td>
                            <td><a href="/deleteFile?id=${document.id}"><img src="/resources/image/application/delete.png"> Delete</a></td>
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div style="height: 200px;"></div>
        </div>
    </div>
</div>

</body>
</html>