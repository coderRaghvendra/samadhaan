package com.samadhaan4u.service.request;

import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.SignUpResponse;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignUpRequest extends AbstractRequest{

    private String email;
    private String password;

    public SignUpRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignUpResponse process(){

        SignUpResponse response = new SignUpResponse();
        UserDao userDao = new UserDao();
        //check if email already exist
        if(userDao.isEmailExist(email)){
            response.setMessage("Email already exist");
        }else if(userDao.insert(new User(email,password))){

            String link = "localhost:8080/samadhaan-1.0-SNAPSHOT/verifyEmail?";
            String subject = "Email verification from  www.samadhaan.com";
            String mailContent = "please click on this link to verify your email" + link;
            //send verification mail
            SendMailRequest request = new SendMailRequest("qwertyraghav@gmail.com", "_Raghav@385848",
                    email, subject, mailContent);
            response.setMessage("User registered sucessfully");
            response.setUser(userDao.select(email, password));
        }else
            response.setMessage("Error ocuurred.");
        return response;
    }
}
