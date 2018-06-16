package com.samadhaan4u.web;

import com.samadhaan4u.dto.constant.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * Created by raghvendra.mishra on 27/04/18.
 */
public class AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
    public static final String MESSAGE_KEY = "message";
    public static final String EMAIL_KEY = "email";
    public static final String USER_ID_KEY = "userId";
    public static final String USER_TYPE_KEY = "userType";
    public static final String RESPONSE_KEY = "response";
    //requests
    public static final String SIGN_IN_REQUEST = "signInRequest";
    public static final String SIGN_UP_REQUEST = "signUpRequest";
    public static final String FORGOT_PASSWORD_REQUEST = "forgotPasswordRequest";
    public static final String GET_ALL_USER_REQUEST = "getAllUserRequest";
    public static final String GET_USER_DOCUMENT_REQUEST = "getUserDocumentRequest";
    public static final String GET_USER_REQUEST = "getUserRequest";
    public static final String UPDATE_PASSWORD_REQUEST = "updatePasswordRequest";
    public static final String UPDATE_USER_REQUEST = "updateUserRequest";
    public static final String UPLOAD_FILE_REQUEST = "uploadFileRequest";
    public static final String DOWNLOAD_FILE_REQUEST = "downloadFileRequest";
    public static final String DELETE_FILE_REQUEST = "deleteFileRequest";
    //request parameters
    public static final String EMAIL_PARAM = "email";
    public static final String EMAIL_KEY_PARAM = "emailKey";
    public static final String PASSWORD_PARAM = "password";
    public static final String MESSAGE_PARAM = "message";
    //forward
    public static final String FORWARD_HOMEPAGE = "forward:/homepage/";
    public static final String FORWARD_HOME = "forward:/";
    //return
    public static final String HOME = "jsp/home/home";
    public static final String HOMEPAGE = "jsp/homepage/homepage";

    protected boolean isUserLoggedIn(HttpSession session){
        if(session.getAttribute(USER_ID_KEY) == null)
            return false;
        return true;
    }

    protected boolean isUserAdmin(HttpSession session){

        if(session.getAttribute(USER_TYPE_KEY) != null && session.getAttribute(USER_TYPE_KEY) == UserType.ADMIN)
            return true;
        return false;
    }

    protected long getUserIdFromSession (HttpSession session){
        return (long)session.getAttribute(USER_ID_KEY);
    }
}
