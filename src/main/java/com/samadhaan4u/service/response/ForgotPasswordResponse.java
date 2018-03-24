package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 11/03/18.
 */
public class ForgotPasswordResponse extends AbstractResponse{
    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordResponse.class);

    public ForgotPasswordResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<ForgotPasswordResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public ForgotPasswordResponse build(){ return new ForgotPasswordResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
