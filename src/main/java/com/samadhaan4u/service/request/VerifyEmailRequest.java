package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.VerifyEmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailRequest extends AbstractRequest{
    private static final Logger logger = LoggerFactory.getLogger(VerifyEmailRequest.class);
    private String emailKey;

    protected VerifyEmailRequest(Builder builder) {
        super(builder);
        this.emailKey = builder.emailKey;
    }

    public static class Builder extends AbstractRequest.Builder<VerifyEmailRequest, Builder>{

        private String emailKey;

        public Builder() {
            super();
        }

        public Builder(String emailKey) {
            super();
            emailKey(emailKey);
        }

        @Override
        public VerifyEmailRequest build(){ return new VerifyEmailRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder emailKey(String emailKey) {
            this.emailKey = emailKey;
            return self();
        }

    }

    public Response process(){
        logger.info("VerifyEmailRequest received, being processed.");
        VerifyEmailResponse.Builder verBuilder = new VerifyEmailResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            if(DaoManager.userDao().verifyEmail(emailKey)){
                success = true;
                rBuilder.message(ResponseMessage.EMAIL_VERIFIED);
            } else{
                rBuilder.message(ResponseMessage.INTERNAL_ERROR);
            }
            logger.info("VerifyEmailRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing VerifyEmailRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return verBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}
