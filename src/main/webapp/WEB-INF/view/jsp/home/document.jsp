<%--
  Created by IntelliJ IDEA.
  User: raghvendra.mishra
  Date: 02/02/18
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>www.samadhaan4u.com</title>
</head>
<body>

<%-- header --%>
<div>
    <div style="align:left">SAMADHAAN</div>
    <div style="align:left">
        <a href="home">Home</a>
    </div>
    <div style="align:left"><b>Document</b></div>
    <div style="align:left">
        <a href="signOut">Sign Out</a>
    </div>
    <div style="clear: both"></div>
</div>

Welcome to home !<br/>
registration sucessful !<br/>
user details : <br/>
name : <c:out value = "${response.user.email}"/><br/>
message : ${response.message}
edit profile
</body>
</html>
