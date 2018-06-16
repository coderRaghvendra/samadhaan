package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by raghvendra.mishra on 10/06/18.
 */
public class DownloadFileResponse extends AbstractResponse{
    private static final Logger logger = LoggerFactory.getLogger(DownloadFileResponse.class);

    public DownloadFileResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<DownloadFileResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public DownloadFileResponse build(){ return new DownloadFileResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
