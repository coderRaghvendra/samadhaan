package com.samadhaan4u.web;

import com.samadhaan4u.service.request.SignInRequest;
import com.samadhaan4u.service.request.SignUpRequest;
import com.samadhaan4u.service.request.VerifyEmailRequest;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SignInResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request, Model model) {
        logger.info("sign up request received");
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null)
            return "jsp/home/home";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignUpRequest.Builder suBuilder = new SignUpRequest.Builder();
        Response response = suBuilder.email(email).password(password).build().process();
        model.addAttribute("responseDto", response);
        return "jsp/homepage/actionMessage";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null)
            return "jsp/home/home";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignInRequest.Builder siBuilder = new SignInRequest.Builder();
        Response response = siBuilder.email(email).password(password).build().process();
        model.addAttribute("responseDto", response);
        if(response.getResult().isSuccess()){
            session.setAttribute("userId", ((SignInResponse)response).getUser().getId());
            return "jsp/home/home";
        }else{
            logger.info("response dto = " + response.getResult().getMessage());
            return "jsp/homepage/actionMessage";
        }
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null)
            return "jsp/home/home";
        logger.info("email = " + request.getParameter("email"));
        return "jsp/homepage/homepage";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        return "jsp/homepage/homepage";
    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.GET)
    public String verifyEmail(HttpServletRequest request, Model model) {

        String emailKey = request.getParameter("emailKey");
        VerifyEmailRequest.Builder builder = new VerifyEmailRequest.Builder();
        Response response = builder.emailKey(emailKey).build().process();
        model.addAttribute("responseDto", response);
        return "jsp/homepage/actionMessage";
    }
}
