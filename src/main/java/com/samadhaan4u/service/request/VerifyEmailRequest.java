package com.samadhaan4u.service.request;

import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.service.response.VerifyEmailResponse;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailRequest extends AbstractRequest{

    private String emailVerificationkey;

    public VerifyEmailRequest(String emailVerificationkey) {
        this.emailVerificationkey = emailVerificationkey;
    }

    public VerifyEmailResponse process(){
        VerifyEmailResponse.Builder builder = new VerifyEmailResponse.Builder();
        UserDao userDao = new UserDao();
        if(userDao.verifyEmail(emailVerificationkey)){
            builder.message("email verified").s;
        }

        return ;
    }
}
