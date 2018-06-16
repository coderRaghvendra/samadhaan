package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignInResponse extends AbstractResponse{
    private static final Logger logger = LoggerFactory.getLogger(SignInResponse.class);

    protected SignInResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<SignInResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public SignInResponse build(){ return new SignInResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
