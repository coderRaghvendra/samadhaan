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
                <a class="nav-link" href="/viewUsers" style="font-size: 14px; text-decoration: underline;
                    color: #cc6600;">
                    view users
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/search" style="font-size: 14px; text-decoration: underline;">
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
            <div style="height: 100px;"></div>
        </div>
        <div class="col">
            <div>Users</div>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone No</th>
                        <th>Created on</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="count" value="1" scope="page" />
                    <c:forEach items="${responseDto.userList}" var="user">
                        <tr>
                            <th scope="row">${count}</th>
                            <td><a href="/user?userId=${user.id}">${user.id}</a></td>
                            <td>${user.fname} ${user.lname}</td>
                            <td>${user.email}</td>
                            <td>${user.phoneNo}</td>
                            <td>${user.creationTime}</td>
                        </tr>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){
    });
</script>
</html>
