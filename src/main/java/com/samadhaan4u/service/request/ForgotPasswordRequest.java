package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.service.response.ForgotPasswordResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Created by raghvendra.mishra on 11/03/18.
 */
public class ForgotPasswordRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordRequest.class);
    private String email;

    public ForgotPasswordRequest(){ super();}

    protected ForgotPasswordRequest(Builder builder){
        super(builder);
        this.email = builder.email;
    }

    public static class Builder extends AbstractRequest.Builder<ForgotPasswordRequest, Builder>{

        private String email;

        public Builder() {
            super();
        }

        public Builder(String email) {
            super();
            email(email);
        }

        @Override
        public ForgotPasswordRequest build(){ return new ForgotPasswordRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return self();
        }
    }

    public Response process(){
        logger.info("ForgotPasswordRequest received, being processed.");
        ForgotPasswordResponse.Builder fprBuilder = new ForgotPasswordResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            String newPassword = getNewPassword(email);
            String subject = "New Temporary Password";
            String content = "New Temporary Password = " + newPassword;
            Response mailResponse = new SendMailRequest.Builder(email, subject, content).build().process();
            if(mailResponse.getResult().isSuccess() && DaoManager.userDao().updatePassword(email, newPassword)){
                logger.info("New Password mail sent successfully.");
                rBuilder.message(ResponseMessage.NEW_PASSWORD_SENT);
                success = true;
            } else{
                rBuilder.message(ResponseMessage.INTERNAL_ERROR);
            }
            logger.info("ForgotPasswordRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing ForgotPasswordRequest for user id {}.", this.userId);
            rBuilder.success(success).message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return fprBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }

    public String getEmail() {
        return email;
    }

    private String getNewPassword(String email){
        Random random = new Random();
        return email.substring(0,4) + String.valueOf(random.nextInt(100_000));
    }
}
