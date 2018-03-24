package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 16/03/18.
 */
public class UploadFileResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileResponse.class);

    protected UploadFileResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<UploadFileResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public UploadFileResponse build(){ return new UploadFileResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
