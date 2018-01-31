package com.samadhaan4u.web.account;

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

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(HttpServletRequest request, Model model) {

        return "jsp/home/home";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request, Model model) {

        logger.info("username = " + request.getParameter());
        return "jsp/home/home";
    }
}
