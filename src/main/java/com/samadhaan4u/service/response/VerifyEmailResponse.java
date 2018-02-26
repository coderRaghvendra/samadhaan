package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class VerifyEmailResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(VerifyEmailResponse.class);
    private String email;

    protected VerifyEmailResponse(Builder builder) {
        super(builder);
        this.email = builder.email;
    }

    public static class Builder extends AbstractResponse.Builder<VerifyEmailResponse, Builder>{

        private String email;

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

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }

    public String getEmail() {
        return email;
    }
}
