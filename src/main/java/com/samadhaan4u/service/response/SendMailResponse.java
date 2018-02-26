package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 26/02/18.
 */
public class SendMailResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(SendMailResponse.class);

    protected SendMailResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<SendMailResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public SendMailResponse build(){ return new SendMailResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
