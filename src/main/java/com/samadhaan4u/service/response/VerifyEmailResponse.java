package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(VerifyEmailResponse.class);
    private String email;

    public VerifyEmailResponse(){}

    public static class Builder extends AbstractResponse.Builder<VerifyEmailResponse>{

        private String email;

        public Builder() {
            super();
        }

        @Override
        public VerifyEmailResponse build() {
            return new VerifyEmailResponse(this);
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }
}
