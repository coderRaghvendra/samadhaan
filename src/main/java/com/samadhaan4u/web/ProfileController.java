package com.samadhaan4u.web;

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

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("updateUserRequest")UpdateUserRequest request, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "forward:/";
        } else {

            if(request == null)
                logger.info("update use request is null");
            else{
                if(request.getUser() == null)
                    logger.info("update user request user is null");
                else
                    logger.info("update user request not nill, fname = " + request.getUser().getFname());
            }
            Response response = request.process();
            model.addAttribute("responseDto", response);
            return "forward:/";
        }
    }
}
