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
    <link rel="stylesheet" type="text/css" href="/resources/core/css/document.css">
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
                <a class="nav-link" href="/home" style="">
                    <img src="/resources/image/application/home-bl.png">
                    Home
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px; color: #cc6600; border-bottom: 3px solid #cc6600;">
                <a class="nav-link" href="/profile">
                    <img src="/resources/image/application/document-or.png">
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

<form:form method="POST" action="/updateUser" modelAttribute="updateUserRequest">
    <img src="/resources/image/application/close-envelope.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
    <form:input path="user.fname" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="text" path="user.lname" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="text" path="user.email" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="password" path="user.password" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="text" path="user.phoneNo" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="text" path="user.emailVerified" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <form:input type="text" path="user.emailKey" cssClass="input-ovd" placeholder="Email"/><br/><br/>
    <img src="/resources/image/application/lock.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
    <form:input type="text" path="user.status" cssClass="input-ovd" placeholder="Password"/><br/><br/>
    <input type="submit" class="inp-submit" value="UPDATE USER"/>
</form:form>


</body>
</html>
