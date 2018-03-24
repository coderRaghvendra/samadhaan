package com.samadhaan4u.web;

import com.samadhaan4u.service.request.ForgotPasswordRequest;
import com.samadhaan4u.service.request.SignInRequest;
import com.samadhaan4u.service.request.SignUpRequest;
import com.samadhaan4u.service.request.VerifyEmailRequest;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SignInResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String signUp(@ModelAttribute("signUpRequest")SignUpRequest request, BindingResult result,
                         HttpSession session, Model model) {
        logger.info("sign up request received");
        if(session.getAttribute("userId") != null)
            return "forward:/";
        if (result.hasErrors()) {
            logger.info("errors found");
            return "jsp/homepage/actionMessage";
        }
        Response response = request.process();
        model.addAttribute("responseDto", response);
        return "jsp/homepage/actionMessage";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("signInRequest")SignInRequest request, BindingResult result,
                         HttpSession session, Model model) {
        logger.debug("in sign in controller");
        if(session.getAttribute("userId") != null){
            logger.info("user id set redirecting");
            return "forward:/";
        }
        if (result.hasErrors()) {
            logger.info("errors found");
            return "jsp/homepage/actionMessage";
        }
        Response response = request.process();
        model.addAttribute("responseDto", response);
        if(response.getResult().isSuccess()){
            session.setAttribute("userId", ((SignInResponse)response).getUser().getId());
            logger.info("setting session variable = " + ((SignInResponse)response).getUser().getId());
            logger.info("session variable set, userId = " + session.getAttribute("userId"));
            return "forward:/";
        }else{
            logger.info("response dto = " + response.getResult().getMessage());
            return "jsp/homepage/actionMessage";
        }
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        ForgotPasswordRequest.Builder fpBuilder = new ForgotPasswordRequest.Builder();
        Response response = fpBuilder.email(email).build().process();
        model.addAttribute("responseDto", response);
        return "jsp/homepage/actionMessage";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        return "forward:/homepage";
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
