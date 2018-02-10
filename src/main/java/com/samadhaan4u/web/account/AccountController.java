package com.samadhaan4u.web.account;

import com.samadhaan4u.service.request.SignInRequest;
import com.samadhaan4u.service.request.SignUpRequest;
import com.samadhaan4u.service.request.VerifyEmailRequest;
import com.samadhaan4u.service.response.SignInResponse;
import com.samadhaan4u.service.response.SignUpResponse;
import com.samadhaan4u.service.response.VerifyEmailResponse;
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

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("email") != null)
            return "jsp/home/home";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignInRequest req = new SignInRequest(email, password);
        SignInResponse response = req.process();
        session.setAttribute("email", email);
        model.addAttribute("response", response);
        return "jsp/home/home";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("email") != null)
            return "jsp/home/home";
        logger.info("email = " + request.getParameter("email"));

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignUpRequest req = new SignUpRequest(email, password);
        SignUpResponse response = req.process();
        session.setAttribute("email", email);
        model.addAttribute("response", response);
        return "jsp/home/home";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("email") != null)
            return "jsp/home/home";
        logger.info("email = " + request.getParameter("email"));
        return "jsp/home/home";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("email", null);
        session.invalidate();
        return "jsp/homepage/homepage";
    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.GET)
    public String verifyEmail(HttpServletRequest request) {

        String key = request.getParameter("emailKey");
        VerifyEmailRequest req = new VerifyEmailRequest(key);
        VerifyEmailResponse response = req.process();
        HttpSession session = request.getSession();
        session.setAttribute("email", null);
        session.invalidate();
        return "jsp/homepage/emailVerified";
    }
}
