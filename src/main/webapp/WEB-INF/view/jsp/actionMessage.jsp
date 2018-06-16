<%--
  Created by IntelliJ IDEA.
  User: raghvendra.mishra
  Date: 02/02/18
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>www.samadhaan.com</title>
    <link rel="stylesheet" type="text/css" href="/resources/core/css/common.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<!--header start-->
<nav class="navbar navbar-expand-md fixed-top navbar-light"
     style="border-bottom: 2px solid #cc6600;">
    <a class="navbar-brand" href="#">
        Samadhaan
    </a>
</nav>
<!--header end-->

<div class="container">
    <div class="row">
        <div class="col" style="text-align: center;">
            <div style="height: 100px;"></div>
            <div>
                <img src="${responseDto.result.message.imgPath}" style="height: 200px; width: 200px;"/>
            </div><br/>
            <div style="opacity: 0.8;"><h2>${responseDto.result.message.heading} !!</h2><br/></div>
            <div><h4>${responseDto.result.message.description}.</h4></div><br/>
            <div>
                <a href="/" style="">
                    <span class="theme-button">Go to homepage</span>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
