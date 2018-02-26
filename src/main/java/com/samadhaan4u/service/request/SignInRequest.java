package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.dao.DocumentDao;
import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SignInResponse;
import com.samadhaan4u.service.response.SignUpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignInRequest extends AbstractRequest{

    private static final Logger logger = LoggerFactory.getLogger(SignInRequest.class);
    private String email;
    private String password;

    protected SignInRequest(Builder builder){
        super(builder);
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder extends AbstractRequest.Builder<SignInRequest, Builder>{

        private String email;
        private String password;

        public Builder() {
            super();
        }

        @Override
        public SignInRequest build(){ return new SignInRequest(this);}

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

        SignInResponse.Builder srBuilder = new SignInResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        User user = DaoManager.userDao().select(email);
        if(user != null){
            if(user.getPassword() == password){
                if(user.isEmailVerified()){
                    result = rBuilder.success(true).build();
                    //get documents
                    srBuilder.user(user);
                }else{
                    result = rBuilder.success(false).message(ResponseMessage.EMAIL_NOT_VERIFIED.getDescription()).build();
                }
            } else{
                result = rBuilder.success(false).message(ResponseMessage.WRONG_PASSWORD.getDescription()).build();
            }
        }else{
            result = rBuilder.success(false).message(ResponseMessage.EMAIL_NOT_REGISTERED.getDescription()).build();
        }

        return srBuilder.result(result).build();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
