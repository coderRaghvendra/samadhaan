package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignUpResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(SignUpResponse.class);

    public SignUpResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<SignUpResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public SignUpResponse build(){ return new SignUpResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}