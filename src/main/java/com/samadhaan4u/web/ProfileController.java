package com.samadhaan4u.web;

import com.samadhaan4u.service.request.UpdatePasswordRequest;
import com.samadhaan4u.service.request.UpdateUserRequest;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class ProfileController extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("updateUserRequest")UpdateUserRequest request, BindingResult result,
                             HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "forward:/";
        } else {
            request.setUserId((long)session.getAttribute("userId"));
            Response response = request.process();
            return "forward:/profile";
        }
    }

    @RequestMapping(value="/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("updatePasswordRequest")UpdatePasswordRequest request, BindingResult result,
                                 HttpSession session) {
        if(session.getAttribute("userId") == null)
            return "forward:/homepage";
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "forward:/";
        } else {
            request.setUserId((long)session.getAttribute("userId"));
            Response response = request.process();
            return "forward:/profile";
        }
    }
}
