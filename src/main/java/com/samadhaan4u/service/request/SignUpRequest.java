package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SignUpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignUpRequest extends AbstractRequest{

    private static final Logger logger = LoggerFactory.getLogger(SignUpRequest.class);
    private String email;
    private String password;

    protected SignUpRequest(Builder builder){
        super(builder);
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder extends AbstractRequest.Builder<SignUpRequest, Builder>{

        private String email;
        private String password;

        public Builder() {
            super();
        }

        @Override
        public SignUpRequest build(){ return new SignUpRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }
    }

    public Response process(){

        SignUpResponse.Builder srBuilder = new SignUpResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        //check if email already exist
        if(DaoManager.userDao().isEmailExist(email)){
            result = rBuilder.success(false).message(ResponseMessage.USER_ALREADY_REGISTERED.getDescription()).build();
        }else if(DaoManager.userDao().insert(email, password)){

            String link = "localhost:8080/samadhaan-1.0-SNAPSHOT/verifyEmail?";
            String subject = "Email verification from  www.samadhaan.com";
            String mailContent = "please click on this link to verify your email" + link;
            //send verification mail
            SendMailRequest.Builder smBuilder = new SendMailRequest.Builder();
            smBuilder.from("qwertyraghav@gmail.com").password("_Raghav@385848").to(email)
                    .sub(subject).mailContent(mailContent).build().process();
            result = rBuilder.success(true).message(ResponseMessage.VERIFY_EMAIL.getDescription()).build();
        }else
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR.getDescription()).build();

        return srBuilder.result(result).build();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
