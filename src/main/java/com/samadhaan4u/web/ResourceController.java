package com.samadhaan4u.web;

import com.samadhaan4u.dto.ServiceType;
import com.samadhaan4u.service.request.*;
import com.samadhaan4u.service.response.Response;
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
 * Created by raghvendra.mishra on 02/02/18.
 */
@Controller
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(HttpSession session, Model model) {
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        logger.info("user id in resource controller = " +  session.getAttribute("userId"));
        long userId = (long)session.getAttribute("userId");
        UserDocumentRequest.Builder udrBuilder = new UserDocumentRequest.Builder();
        Response response = udrBuilder.userId(userId).build().process();
        model.addAttribute("responseDto", response);
        model.addAttribute("uploadFileRequest", new UploadFileRequest());
        logger.info("response dto = " + response.getResult().getMessage());
        return "jsp/home/home";
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(HttpSession session, Model model) {
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        long userId = (Long)session.getAttribute("userId");
        UserProfileRequest.Builder udrBuilder = new UserProfileRequest.Builder();
        Response response = udrBuilder.userId(userId).build().process();
        model.addAttribute("responseDto", response);
        model.addAttribute("updateUserRequest", new UpdateUserRequest());
        model.addAttribute("updatePasswordRequest", new UpdatePasswordRequest());
        logger.info("response dto = " + response.getResult().getMessage());
        return "jsp/home/profile";
    }

    @RequestMapping(value = "/homepage", method = {RequestMethod.GET, RequestMethod.POST})
    public String homepage(HttpSession session, Model model) {
        if(session.getAttribute("userId") != null)
            return "forward:/";
        model.addAttribute("signInRequest", new SignInRequest());
        model.addAttribute("signUpRequest", new SignUpRequest());
        model.addAttribute("forgotPasswordRequest", new ForgotPasswordRequest());
        return "jsp/homepage/homepage";
    }

    @RequestMapping(value = "/getUserData", method = {RequestMethod.POST})
    public String getUserData(@ModelAttribute("userDocumentRequest")UserDocumentRequest request,
                              BindingResult result, HttpSession session, Model model) {
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        long userId = request.getUserId();
        UserDocumentRequest.Builder udrBuilder = new UserDocumentRequest.Builder();
        Response response = udrBuilder.userId(userId).build().process();
        model.addAttribute("responseDto", response);
        model.addAttribute("userDocumentRequest", new UserDocumentRequest());
        logger.info("response dto = " + response.getResult().getMessage());
        return "jsp/home/search";
    }

    @RequestMapping(value = "/viewUsers", method = {RequestMethod.GET})
    public String viewUsers(HttpSession session, Model model){
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        GetUsersRequest.Builder gurBuilder = new GetUsersRequest.Builder();
        Response response = gurBuilder.build().process();
        model.addAttribute("responseDto", response);
        return "jsp/home/viewUsers";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public String search(HttpSession session, Model model){
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        model.addAttribute("userDocumentRequest", new UserDocumentRequest());
        return "jsp/home/search";
    }
}