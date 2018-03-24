package com.samadhaan4u.web;

import com.samadhaan4u.service.request.UploadFileRequest;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by raghvendra.mishra on 31/01/18.
 */
@Controller
public class DocumentController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute("uploadFileRequest")UploadFileRequest request, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "forward:/";
        } else {

            Response response = request.process();
            model.addAttribute("responseDto", response);
            return "forward:/";
        }
    }
}
