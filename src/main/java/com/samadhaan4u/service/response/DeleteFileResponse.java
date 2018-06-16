package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 10/06/18.
 */
public class DeleteFileResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFileResponse.class);

    public DeleteFileResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<DeleteFileResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public DeleteFileResponse build(){ return new DeleteFileResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }}