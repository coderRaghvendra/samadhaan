package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.VerifyEmailResponse;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailRequest extends AbstractRequest{

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

        @Override
        public VerifyEmailRequest build(){ return new VerifyEmailRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder emailKey(String emailKey) {
            this.emailKey = emailKey;
            return this;
        }

    }

    public Response process(){
        VerifyEmailResponse.Builder builder = new VerifyEmailResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        if(DaoManager.userDao().verifyEmail(emailKey)){
            builder.result(rBuilder.success(true)
                    .message(ResponseMessage.EMAIL_VERIFIED.getDescription()).build());
        }else{
            builder.result(rBuilder.success(false)
                    .message(ResponseMessage.INTERNAL_ERROR.getDescription()).build());
        }
        return builder.build();
    }
}
