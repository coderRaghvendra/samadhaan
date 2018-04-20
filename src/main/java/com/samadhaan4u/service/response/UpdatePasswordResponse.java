package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class UpdatePasswordResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordResponse.class);

    protected UpdatePasswordResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<UpdatePasswordResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public UpdatePasswordResponse build(){ return new UpdatePasswordResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
