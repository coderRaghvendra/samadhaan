package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 23/03/18.
 */
public class UpdateUserResponse extends AbstractResponse{
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserResponse.class);

    protected UpdateUserResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<UpdateUserResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public UpdateUserResponse build(){ return new UpdateUserResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
