package com.samadhaan4u.web.document;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class DocumentController {

    @RequestMapping(value = "/raghav", method = RequestMethod.GET)
    public String showRegister(HttpServletRequest request, HttpServletResponse response) {

        return "jsp/test";
    }
}
