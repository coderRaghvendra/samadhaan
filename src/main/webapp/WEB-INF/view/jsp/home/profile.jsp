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
    <link rel="stylesheet" type="text/css" href="/resources/core/css/profile.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<!--header start-->
<nav class="navbar navbar-expand-md fixed-top navbar-light"
     style="padding-bottom: 0px; border-bottom: 1px solid #cc6600; background: white;
     box-shadow: 0px 0px 10px #bfbfbf;"
     id="navHeader">
    <a class="navbar-brand" href="#" style="">
        Samadhaan
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon" style=""></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <div class="mr-auto"></div>
        <ul class="navbar-nav" style="">
            <c:if test="${responseDto.user.type == 'ADMIN'}">
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
            <li class="nav-item" style="padding-right: 10px;">
                <a class="nav-link" href="/" style="">
                    <img src="/resources/image/application/home-bl.png">
                    Home
                </a>
            </li>
            <li class="nav-item" style="padding-right: 10px; border-bottom: 3px solid #cc6600;">
                <a class="nav-link" href="/profile" style="color: #cc6600;">
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
<div class="container">
    <div class="row">
        <div class="col" style="">
            <div style="height: 100px;"></div>
        </div>
    </div>
    <div class="row">
        <c:set var="user" scope = "page" value = "${responseDto.user}"/>
        <div class="col-sm-12 col-md-12 col-lg-12">
            <div style="border: 1px solid #cccccc; padding: 10px 20px; box-shadow: 0px 0px 10px #bfbfbf;">
                <div class="row" style="padding: 5px 0px; border-bottom: 1px solid #cccccc; color: #cc6600;">
                    <div class="col-12">My Profile</div>
                </div>
                <div class="row" style="padding: 5px 0px;">
                    <div class="col-sm-12 col-md-4 col-lg-4">
                        <img src="/resources/image/application/mail-orange-32.png" class="sm-img">&nbsp;&nbsp;&nbsp;
                        <span class="low-opac">Email</span>
                    </div>
                    <div class="col-sm-12 col-md-8 col-lg-8">
                        <div>
                            ${user.email}
                        </div>
                    </div>
                </div>
                <form:form method="POST" action="/updateUser" modelAttribute="updateUserRequest"
                           id="updateUserForm" cssStyle="margin-bottom: 0px;">
                    <div class="row" style="padding: 5px 0px;">
                        <div class="col-sm-12 col-md-4 col-lg-4">
                            <img src="/resources/image/application/name-orange-32.png" class="sm-img">&nbsp;&nbsp;&nbsp;
                            <span class="low-opac">Name</span>
                        </div>
                        <div class="col-sm-12 col-md-8 col-lg-8">
                            <div id="nameDiv">
                                <c:if test="${empty user.fname and empty user.lname}">
                                    <div id="addNameLink">Add your name</div>
                                </c:if>
                                <c:if test="${not empty user.fname or not empty user.lname}">
                                    <div style="float: left;">
                                            ${user.fname} ${user.lname}
                                    </div>
                                    <div style="float: right;">
                                        <img src="/resources/image/application/edit-32.png" id="nameEditLink"
                                             class="sm-img"/>
                                    </div>
                                    <div style="clear: both;"></div>
                                </c:if>
                            </div>
                            <div class="hidden" id="nameEditForm">
                                <div style="float: left;">
                                    <form:input type="text" path="user.fname" cssClass="input-ovd" placeholder="${user.fname}"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <form:input type="text" path="user.lname" cssClass="input-ovd" placeholder="${user.lname}"/>
                                </div>
                                <div style="float: right;">
                                        <%--<input type="submit" value="save" align="right">--%>
                                    <a href="#" id="nameEditSave">
                                        <img src="/resources/image/application/save-32.png" class="sm-img"/>
                                    </a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" id="nameEditCancel" align="right">
                                        <img src="/resources/image/application/cancel-32.png" class="sm-img"/>
                                    </a>
                                </div>
                                <div style="clear: both;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding: 5px 0px;">
                        <div class="col-sm-12 col-md-4 col-lg-4">
                            <img src="/resources/image/application/mobile-orange-32.png" class="sm-img">&nbsp;&nbsp;&nbsp;
                            <span class="low-opac">Phone No</span>
                        </div>
                        <div class="col-sm-12 col-md-8 col-lg-8">
                            <div id="phoneNoDiv">
                                <c:if test="${empty user.phoneNo}">
                                    <div id="addPhoneNoLink">Add your phone no</div>
                                </c:if>
                                <c:if test="${not empty user.phoneNo}">
                                    <div style="float: left;">
                                            ${user.phoneNo}
                                    </div>
                                    <div style="float: right;">
                                        <img src="/resources/image/application/edit-32.png" id="phoneNoEditLink"
                                             class="sm-img"/>
                                    </div>
                                    <div style="clear: both;"></div>
                                </c:if>
                            </div>
                            <div class="hidden" id="phoneNoEditForm">
                                <div style="float: left;">
                                    <form:input type="text" path="user.phoneNo" cssClass="input-ovd"
                                                placeholder="${user.phoneNo}"/>
                                </div>
                                <div style="float: right;">
                                        <%--<input type="submit" value="save" align="right">--%>
                                    <a href="#" id="phoneNoEditSave">
                                        <img src="/resources/image/application/save-32.png" class="sm-img"/>
                                    </a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" id="phoneNoEditCancel" align="right">
                                        <img src="/resources/image/application/cancel-32.png" class="sm-img"/>
                                    </a>
                                </div>
                                <div style="clear: both;"></div>
                            </div>
                        </div>
                    </div>
                </form:form>
                <form:form method="POST" action="/updatePassword" modelAttribute="updatePasswordRequest"
                           id="updatePasswordForm">
                    <div class="row" style="padding: 5px 0px;">
                        <div class="col-sm-12 col-md-4 col-lg-4">
                            <img src="/resources/image/application/password-orange-32.png" class="sm-img">&nbsp;&nbsp;&nbsp;
                            <span class="low-opac">Password</span>
                        </div>
                        <div class="col-sm-12 col-md-8 col-lg-8">
                            <div id="passwordDiv">

                                <div style="float: left;">
                                    *******
                                </div>
                                <div style="float: right;">
                                    <img src="/resources/image/application/edit-32.png" id="passwordEditLink"
                                         class="sm-img"/>
                                </div>
                                <div style="clear: both;"></div>
                            </div>
                            <div class="hidden" id="changePasswordDiv">
                                <div style="float: left;">
                                    <form:input type="password" path="oldPassword" cssClass="input-ovd"
                                                placeholder="old password"/><br/>
                                    <form:input type="password" path="newPassword" cssClass="input-ovd"
                                                placeholder="new password"/><br/>
                                    <form:input type="password" path="newPasswordConfirm" cssClass="input-ovd"
                                                placeholder="confirm new password"/><br/>
                                </div>
                                <div style="float: right;">
                                    <a href="#" id="passwordEditSave">
                                        <img src="/resources/image/application/save-32.png" class="sm-img"/>
                                    </a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" id="passwordEditCancel" align="right">
                                        <img src="/resources/image/application/cancel-32.png" class="sm-img"/>
                                    </a>
                                </div>
                                <div style="clear: both;"></div>
                            </div>
                        </div>
                    </div>
                </form:form>

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
<script>
    $(document).ready(function(){
        //for name
        $("#addNameLink").click(function(){
            $("#nameEditForm").show();
            $("#nameDiv").hide();
        });
        $("#nameEditLink").click(function(){
            $("#nameEditForm").show();
            $("#nameDiv").hide();
        });
        $("#nameEditCancel").click(function(){
            $("#nameEditForm").hide();
            $("#nameDiv").show();
        });
        $( "#nameEditSave" ).click(function() {
            $( "#updateUserForm" ).submit();
        });

        //for phone no
        $("#addPhoneNoLink").click(function(){
            $("#phoneNoEditForm").show();
            $("#phoneNoDiv").hide();
        });
        $("#phoneNoEditLink").click(function(){
            $("#phoneNoEditForm").show();
            $("#phoneNoDiv").hide();
        });
        $("#phoneNoEditCancel").click(function(){
            $("#phoneNoEditForm").hide();
            $("#phoneNoDiv").show();
        });
        $( "#phoneNoEditSave" ).click(function() {
            $( "#updateUserForm" ).submit();
        });

        //for password
        $("#passwordEditLink").click(function(){
            $("#passwordDiv").hide();
            $("#changePasswordDiv").show();
        });

        $("#passwordEditCancel").click(function(){
            $("#changePasswordDiv").hide();
            $("#passwordDiv").show();
        });

        $( "#passwordEditSave" ).click(function() {
            $( "#updatePasswordForm" ).submit();
        });
    });
</script>
</html>
