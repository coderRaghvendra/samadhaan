package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 03/05/18.
 */
public class ResourceResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(ResourceResponse.class);

    protected ResourceResponse(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<ResourceResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public ResourceResponse build(){ return new ResourceResponse(this);}

        @Override
        public Builder self() {
            return this;
        }
    }
}
