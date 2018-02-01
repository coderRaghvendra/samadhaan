package com.samadhaan4u.web.account;

import com.samadhaan4u.dto.UserDataDto;
import com.samadhaan4u.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model) {

        return "jsp/home/home";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request, Model model) {

        logger.info("email = " + request.getParameter("email"));

        UserService userService = new UserService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDataDto userDataDto = userService.getUserDataDto(email, password);
        model.addAttribute("userDataDto", userDataDto);
        return "jsp/home/home";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request, Model model) {

        logger.info("email = " + request.getParameter("email"));
        return "jsp/home/home";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request, Model model) {

        logger.info("email = " + request.getParameter("email"));
        return "jsp/homepage/homepage";
    }
}
