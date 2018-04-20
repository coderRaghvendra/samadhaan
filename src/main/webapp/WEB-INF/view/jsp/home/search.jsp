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
    <link rel="stylesheet" type="text/css" href="/resources/core/css/search.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<!--header start-->
<nav class="navbar navbar-expand-md fixed-top navbar-light"
     style="padding-bottom: 0px; border-bottom: 1px solid #cc6600;" id="navHeader">
    <a class="navbar-brand" href="#" style="">
        Samadhaan
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon" style=""></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <div class="mr-auto"></div>
        <ul class="navbar-nav" style="">
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/viewUsers" style="font-size: 14px; text-decoration: underline;">
                    view users
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/search" style="font-size: 14px; text-decoration: underline;
                    color: #cc6600;">
                    search
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/" style="">
                    <img src="/resources/image/application/home-bl.png">
                    Home
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/profile">
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
<div class="container">
    <div class="row">
        <div class="col-12" style="">
            <div style="height: 80px;"></div>
        </div>
        <div class="col">
            <div>Search User : </div>
            <div>
                <form:form method="POST" action="/getUserData" modelAttribute="userDocumentRequest">
                    <form:input type="text" path="userId" cssClass="input-ovd" placeholder="Enter User Id"/>
                    <input type="submit" value="get user">
                </form:form>
            </div>
            <c:if test="${not empty responseDto and not empty responseDto.user}">
                <c:set var="user" scope = "page" value = "${responseDto.user}"/>
                <div>User Profile</div>
                <div>Name : ${user.fname} ${user.lname}</div>
                <div>Email : ${user.email}</div>
                <div>Phone No : ${user.phoneNo}</div>
                <div>Phone No : ${user.phoneNo}</div>
                <div>User Documents</div>

                <div>Documents</div>
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>File Name</th>
                            <th>Uploaded Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1" scope="page" />
                        <c:forEach items="${responseDto.documentList}" var="document">
                            <tr>
                                <th scope="row">${count}</th>
                                <td>${document.name}</td>
                                <td>${document.uploadDate}</td>
                                <td>${document.description}</td>
                            </tr>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){
    });
</script>
</html>
