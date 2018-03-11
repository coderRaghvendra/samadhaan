package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(VerifyEmailResponse.class);

    protected VerifyEmailResponse(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<VerifyEmailResponse, Builder>{

        public Builder() {
            super();
        }

        @Override
        public VerifyEmailResponse build() {
            return new VerifyEmailResponse(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
