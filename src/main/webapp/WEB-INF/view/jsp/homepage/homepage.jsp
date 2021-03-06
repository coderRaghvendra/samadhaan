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
    <link rel="stylesheet" type="text/css" href="/resources/core/css/homepage.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<!--modals-->
<!-- sign up modal start -->
<div class="modal fade" id="signUpModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style=";">
            <!-- Modal body start-->
            <div class="modal-body" style="text-align: center; padding: 50px 10px 10px 10px;">
                <div><img src="/resources/image/application/fin_icon.svg"
                          style="height: 50px; width: 50px;"/>
                </div><br/>
                <div style="opacity: 0.8;"><h3>Sign Up</h3></div><br/>
                <form:form method="POST" action="/signUp" modelAttribute="signUpRequest">
                    <img src="/resources/image/application/close-envelope.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
                    <form:input type="text" path="email" cssClass="input-ovd" placeholder="Email"/><br/><br/>
                    <img src="/resources/image/application/lock.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
                    <form:input type="password" path="password" cssClass="input-ovd" placeholder="Password"/><br/><br/>
                    <input type="submit" class="inp-submit" value="GET STARTED"/>
                </form:form>
                <div style="font-size: 13px; padding-top: 20px;">
                    <span style="opacity: 0.6;">Already have an account. Click here to</span>
                    <a href="" style="color: #cc6600; opacity: 0.9;"> SIGN IN </a>
                    <span style="opacity: 0.6;">!!</span>
                </div>
            </div>
            <!-- Modal body end-->
        </div>
    </div>
</div>
<!-- sign up modal end -->
<!-- sign in modal start -->
<div class="modal fade" id="signInModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="">
            <!-- Modal body start-->
            <div class="modal-body" style="text-align: center; padding: 50px 10px 10px 10px;">
                <div><img src="/resources/image/application/fin_icon.svg"
                          style="height: 50px; width: 50px;"/>
                </div><br/>
                <div style="opacity: 0.8;"><h3>Welcome Back !!</h3></div><br/>
                <form:form method="POST" action="/signIn" modelAttribute="signInRequest">
                    <img src="/resources/image/application/close-envelope.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
                    <form:input type="text" path="email" cssClass="input-ovd" placeholder="Email"/><br/><br/>
                    <img src="/resources/image/application/lock.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
                    <form:input type="password" path="password" cssClass="input-ovd" placeholder="Password"/><br/><br/>
                    <input type="submit" class="inp-submit" value="SIGN IN"/>
                </form:form>
                <div style="font-size: 13px; padding-top: 20px;">
                    <a href="javascript:()" style="font-size: 13px;" class="" id="forgotPasswordLink">
                        Forgot your password?
                    </a><br/>
                    <%--forgot password form--%>
                    <div id="forgotPasswordForm" class="hidden">
                        <form:form method="POST" action="/forgotPassword" modelAttribute="forgotPasswordRequest">
                            Enter your email address, we will send temporary password<br/>
                            <img src="/resources/image/application/lock.png" style="opacity: 0.7;"/>&nbsp;&nbsp;
                            <form:input path="email" cssClass="input-ovd" placeholder="Email"/><br/><br/>
                            <input type="submit" class="inp-submit" value="SEND PASSWORD"/>
                        </form:form><br/><br/>
                    </div>
                    <span style="opacity: 0.6;">Do not have an account. Click here to</span>
                    <a href="" style="color: #cc6600; opacity: 0.9;"> SIGN UP </a>
                    <span style="opacity: 0.6;">!!</span>
                </div>
            </div>
            <!-- Modal body end-->
        </div>
    </div>
</div>
<!-- sign in modal end -->

<!--header start-->
<nav class="navbar navbar-expand-md fixed-top navbar-dark"
     style="" id="navHeader">
    <a class="navbar-brand" href="#">
        Samadhaan
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon" style=""></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <div class="mr-auto"></div>
        <ul class="navbar-nav" style="">
            <li class="nav-item">
                <a class="nav-link" href="#">ABOUT</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">SERVICES</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">CONTACT</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal"
                   data-target="#signUpModal">SIGN UP</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" data-toggle="modal"
                   data-target="#signInModal">SIGN IN</a>
            </li>
        </ul>
    </div>

</nav>
<!--header end-->

<!--body start-->
<div class="container-fluid" style="">
    <div class="row bg-img">
        <div class="col bg">
            <div class="brand" style="color: white">
                <div style="height: 200px;"></div>
                <div><img src="/resources/image/application/fin_icon.svg"
                          style="height: 50px; width: 50px;"/>
                </div>
                <div style="font-size: 50px;">
                    Samadhaan
                </div>
                <div class="">
                    One Solution to all.Taxes, ITR, GST and more.
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-lg-8">
            <div class="about">
                <div class="" style="opacity: 0.8;">ABOUT US</div>
                <div class=""><h2>What is samadhaan4u?</h2></div><br/>
                <div style="opacity: 0.6;">
                    Samadhaan rendering comprehensive professional services which
                    include Book-Keeping of Accounts, Filling of Income Tax Return
                    & GST Return, Legal and Secretarial Support, Business advisory
                    services including implementation of accounting systems and controls,
                    Legal and Secretarial Support, Management Consulting, Advice on
                    Search and Seizure Matters, Tax Audit and advice on Indirect Taxes.
                    Our firm has experience of over 6 years of professional practice.
                    We have top of the line infrastructure to cater to the needs of our
                    diversified clientele. Our high quality services can be attributed to the
                    timely communication with the clients, personalized proactive service in
                    response to their needs and regular internal reviews.
                </div>
            </div>
        </div>
        <div class="col-md-12 col-lg-4">
            <div style="padding-top: 50px;">
            <img src="/resources/image/application/fin_icon.svg"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col" style="padding: 0px;">
            <div class="service">
                <div class="serv-head">SERVICES</div>
                <div class="serv-head"><h2>We provide solutions for </h2></div><br/>
                <div class="row">
                    <c:forEach items="${serviceArray}" var="service">
                        <div class="col-sm-4 col-md-3 col-lg-3"
                             style="padding: 6px;">
                            <div class="serv-card">
                                <div style="padding: 30px 10px; background: #cc6600; border-radius: 3px 3px 0px 0px;">
                                    <img src="/resources/image/application/${service.imagePath}">
                                </div>
                                <div style="padding-top: 10px; opacity: 0.8;">
                                        ${service.heading}
                                </div>
                                <div style="padding: 10px; opacity: 0.6; font-size: 13px; color: #cc6600;">
                                        ${service.description}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!--footer start-->
    <div class="row contact-bg">
        <div class="col-sm-12 col-md-12 col-lg-6 footer" style="">
            <div class="contact">
                <img src="/resources/image/application/user-24px.png" class="img-16">&nbsp;
                Dhananjay Mishra</b>
            </div>
            <div class="contact">
                <img src="/resources/image/application/address-24px.png" class="img-16">&nbsp;
                Gehumi Mabbi, shivdhara road, darbhanga, BIHAR
            </div>
            <div class="contact">
                <img src="/resources/image/application/address-24px.png" class="img-16">&nbsp;
                Janki Palace, kiran chowk, opp LIC building, sitamadhi, BIHAR
            </div>
            <div class="contact">
                <img src="/resources/image/application/call-24px.png" class="img-16">&nbsp;
                9650346030
            </div>
            <div class="contact">
                <img src="/resources/image/application/email.png" class="img-16">&nbsp;
                info@samadhaan4u.com
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-6 footer">
            <div style="padding: 5px 5%;">
                <div style="padding-bottom: 5px; opacity: 0.8;">
                    <h4>Have Query ?? Send us.</h4>
                </div>
                <div>
                    <form>
                        <div>
                            <input type="text" name="name" class="input-contact-ovd"
                                   style="width: 100%;" placeholder="Full Name"/>
                        </div><br/>
                        <div>
                            <input type="text" name="email" class="input-contact-ovd"
                                   style="width: 48%" placeholder="Email"/>
                            <input type="number" name="phoneNo" class="input-contact-ovd"
                                   placeholder="Phone no" style="width: 48%; float: right;"/>
                        </div><br/>
                        <div>
                            <input type="text" name="message" placeholder="Write your message..."
                                   class="input-contact-ovd" style="width: 100%;"/>
                        </div><br/>
                        <div>
                            <input type="submit" style="float: right;" class="inp-contact-submit"
                                   value="SEND"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-12 footer" style="">
            <div style="border-top: 1px solid white; text-align: center;
            padding: 20px;">
                <br/>
                <div>
                    <img src="/resources/image/application/facebook-logo-32.png">&nbsp;&nbsp;&nbsp;
                    <img src="/resources/image/application/twitter-logo-32.png">&nbsp;&nbsp;&nbsp;
                    <img src="/resources/image/application/google-plus-32.png">&nbsp;&nbsp;&nbsp;
                </div><br/>
                <div>Copyright @ 2018 Samadhaan</div><br/>
            </div>
        </div>
    </div>
    <!--footer end-->
</div>
<!--body end-->
<script>
    $(document).ready(function(){
        var scroll_pos = 0;
        $(document).scroll(function() {
            scroll_pos = $(this).scrollTop();
            if(scroll_pos > 210) {
                $("#navHeader").css('background-color', 'rgba(0,0,0,0.9)');
            } else {
                $("#navHeader").css('background', 'transparent');
            }
        });

        $("#forgotPasswordLink").click(function(){
            $("#forgotPasswordForm").toggle();
        });
    });
</script>
</body>
</html>