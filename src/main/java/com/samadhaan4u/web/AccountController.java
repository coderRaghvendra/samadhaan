package com.samadhaan4u.web;

import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.request.*;
import com.samadhaan4u.service.response.GetUserResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class AccountController extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("signUpRequest")SignUpRequest request, BindingResult result,
                         HttpSession session, RedirectAttributes redirectAttributes) {
        logger.info("In signUp controller");
        if(isUserLoggedIn(session)) {
            return FORWARD_HOME;
        }
//        if (result.hasErrors()) {
//            logger.info("errors found");
//            return "jsp/homepage/actionMessage";
//        }
        Response response = request.process();
        redirectAttributes.addFlashAttribute(MESSAGE_KEY, response.getResult().getMessage());
        return FORWARD_HOMEPAGE;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("signInRequest")SignInRequest request, BindingResult result,
                         HttpSession session, RedirectAttributes redirectAttributes) {
        logger.debug("In signIn controller");
        if(isUserLoggedIn(session)) {
            return FORWARD_HOME;
        }
//        if (result.hasErrors()) {
//            logger.info("errors found");
//            return "jsp/homepage/actionMessage";
//        }
        Response response = request.process();
        if(response.getResult().isSuccess()){
            UserDto userDto = ((GetUserResponse)new GetUserRequest.Builder(request.getEmail()).build()
                    .process()).getUserDto();
            session.setAttribute(USER_ID_KEY, userDto.getId());
            session.setAttribute(USER_TYPE_KEY, userDto.getType());
            return FORWARD_HOME;
        } else{
            redirectAttributes.addFlashAttribute(MESSAGE_KEY, response.getResult().getMessage());
            return FORWARD_HOMEPAGE;
        }
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String email = request.getParameter(EMAIL_PARAM);
        Response response = new ForgotPasswordRequest.Builder(email).build().process();
        redirectAttributes.addFlashAttribute(MESSAGE_KEY, response.getResult().getMessage());
        return FORWARD_HOMEPAGE;
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID_KEY, null);
        session.invalidate();
        return FORWARD_HOMEPAGE;
    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.GET)
    public String verifyEmail(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String emailKey = request.getParameter(EMAIL_KEY_PARAM);
        Response response = new VerifyEmailRequest.Builder(emailKey).build().process();
        redirectAttributes.addFlashAttribute(RESPONSE_KEY, response);
        return FORWARD_HOMEPAGE;
    }
}
