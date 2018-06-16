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

    public SignInRequest(){super();}

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
        logger.info("SignInRequest received, being processed.");
        SignInResponse.Builder srBuilder = new SignInResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            User user = DaoManager.userDao().get(email).orElse(null);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    if (user.isEmailVerified()) {
                        success = true;
                        rBuilder.message(ResponseMessage.SIGN_IN_SUCCESS);
                        srBuilder.userId(user.getId());
                    } else {
                        rBuilder.message(ResponseMessage.EMAIL_NOT_VERIFIED).build();
                    }
                } else {
                    rBuilder.message(ResponseMessage.WRONG_PASSWORD).build();
                }
            } else {
                rBuilder.message(ResponseMessage.EMAIL_NOT_REGISTERED).build();
            }
        } catch(Exception e){
            logger.info("Exception occurred while signing in user.");
            rBuilder.message(ResponseMessage.INTERNAL_ERROR).build();
        }
        return srBuilder.result(rBuilder.success(success).build()).build();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
