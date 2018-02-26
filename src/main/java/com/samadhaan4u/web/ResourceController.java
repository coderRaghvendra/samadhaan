package com.samadhaan4u.web;

import com.samadhaan4u.dto.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        logger.info("session value" +  session.getAttribute("email"));
        if(session.getAttribute("email") != null)
            return "jsp/home/home";
        model.addAttribute("serviceArray", ServiceType.values());
        return "jsp/homepage/homepage";
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public String document(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("email") == null)
            return "jsp/homepage/homepage";
        return "jsp/home/document";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if(session.getAttribute("email") == null)
            return "jsp/homepage/homepage";
        return "jsp/home/home";
    }
}
