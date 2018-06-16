package com.samadhaan4u.web;

import com.samadhaan4u.service.request.DeleteFileRequest;
import com.samadhaan4u.service.request.DownloadFileRequest;
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
public class DocumentController extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute(UPLOAD_FILE_REQUEST)UploadFileRequest request, BindingResult result,
                             Model model, HttpSession session) {
        if(!isUserLoggedIn(session)) {
            return FORWARD_HOMEPAGE;
        }
        if (result.hasErrors()) {
            System.out.println("validation errors");
        } else {
            Response response = request.process();
            model.addAttribute("response", response);
        }
        return FORWARD_HOME;
    }

    @RequestMapping(value="/downloadFile", method = RequestMethod.GET)
    public String downloadFile(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        if(!isUserLoggedIn(session)) {
            return FORWARD_HOMEPAGE;
        }
            DownloadFileRequest.Builder dfrBuilder = new DownloadFileRequest.Builder();
            dfrBuilder.userId(getUserIdFromSession(session)).id(Long.parseLong(request.getParameter("id")))
                    .response(response).build().process();
        return FORWARD_HOME;
    }

    @RequestMapping(value="/deleteFile", method = RequestMethod.POST)
    public String deleteFile(@ModelAttribute(DELETE_FILE_REQUEST)DeleteFileRequest request, BindingResult result,
                             Model model, HttpSession session) {
        if(!isUserLoggedIn(session)) {
            return FORWARD_HOMEPAGE;
        }
        if (result.hasErrors()) {
            System.out.println("validation errors");
        } else {
            Response response = request.process();
            model.addAttribute("response", response);
        }
        return FORWARD_HOME;
    }
}
