package com.samadhaan4u.web;

import com.samadhaan4u.dto.ServiceType;
import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.dto.constant.Review;
import com.samadhaan4u.service.request.*;
import com.samadhaan4u.service.response.GetUserDocumentResponse;
import com.samadhaan4u.service.response.GetUserResponse;
import com.samadhaan4u.service.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
/**
 * Created by raghvendra.mishra on 02/02/18.
 */
@Controller
public class ResourceController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @RequestMapping(value = "/homepage", method = {RequestMethod.GET, RequestMethod.POST})
    public String homepage(HttpSession session, Model model) {
        if(isUserLoggedIn(session))
            return "forward:/";
        if(model.asMap().get("message") != null){
            model.addAttribute(MESSAGE_KEY, model.asMap().get("message"));
        }
        model.addAttribute("signInRequest", new SignInRequest());
        model.addAttribute("signUpRequest", new SignUpRequest());
        model.addAttribute("forgotPasswordRequest", new ForgotPasswordRequest());
        model.addAttribute("services", ServiceType.values());
        model.addAttribute("reviews", Review.values());
        return HOMEPAGE;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(HttpSession session, Model model) {
        logger.info("In home controller");
        if(!isUserLoggedIn(session)) {
            return FORWARD_HOMEPAGE;
        }
        if(model.asMap().get(MESSAGE_KEY) != null){
            model.addAttribute(MESSAGE_KEY, model.asMap().get(MESSAGE_KEY));
        }
        GetUserDetailRequest.Builder udrBuilder = new GetUserDetailRequest.Builder();
        Response response = udrBuilder.userId(getUserIdFromSession(session)).build().process();
        model.addAttribute(RESPONSE_KEY, response);
        model.addAttribute(UPLOAD_FILE_REQUEST, new UploadFileRequest());
        return HOME;
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(HttpSession session, Model model) {
        if(!isUserLoggedIn(session))
            return FORWARD_HOMEPAGE;
        long userId = (Long)session.getAttribute("userId");
        logger.info("Profile requested by user id = " + userId);
        GetUserRequest.Builder udrBuilder = new GetUserRequest.Builder();
        Response response = udrBuilder.userId(userId).build().process();
        model.addAttribute("response", response);
        model.addAttribute("updateUserRequest", new UpdateUserRequest());
        model.addAttribute("updatePasswordRequest", new UpdatePasswordRequest());
        return "jsp/home/profile";
    }

    @RequestMapping(value = "/getUserData", method = {RequestMethod.POST})
    public String getUserData(@ModelAttribute("userDocumentRequest")GetUserDocumentRequest request,
                              BindingResult result, HttpSession session, Model model) {
        if(!isUserLoggedIn(session))
            return "forward:/homepage";
        long userId = request.getUserId();
        GetUserDocumentRequest.Builder udrBuilder = new GetUserDocumentRequest.Builder();
        Response response = udrBuilder.userId(userId).build().process();
        model.addAttribute("responseDto", response);
        model.addAttribute("userDocumentRequest", new GetUserDocumentRequest());
        logger.info("response dto = " + response.getResult().getMessage());
        return "jsp/home/search";
    }

    @RequestMapping(value = "/viewUsers", method = {RequestMethod.GET})
    public String viewUsers(HttpSession session, Model model){
        if(!isUserLoggedIn(session)){
            return "forward:/homepage";
        }
        if(!isUserAdmin(session)){
            ResourceRequest.Builder rrBuilder = new ResourceRequest.Builder();
            Response response = rrBuilder.userId(getUserIdFromSession(session)).build().process();
            model.addAttribute("responseDto", response);
            return "jsp/actionMessage";
        }
        GetAllUserRequest.Builder gurBuilder = new GetAllUserRequest.Builder();
        Response response = gurBuilder.userId(getUserIdFromSession(session)).build().process();
        model.addAttribute("responseDto", response);
        return "jsp/home/viewUsers";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public String search(HttpSession session, Model model){
        if(!isUserLoggedIn(session))
            return "forward:/homepage";
        model.addAttribute("userDocumentRequest", new GetUserDocumentRequest());
        return "jsp/home/search";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    public String user(HttpSession session, Model model){
        if(!isUserLoggedIn(session))
            return "forward:/homepage";
        model.addAttribute("userDocumentRequest", new GetUserDocumentRequest());
        return "jsp/home/search";
    }
}