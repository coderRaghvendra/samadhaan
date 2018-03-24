package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class UserProfileResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileResponse.class);
    private User user;

    protected UserProfileResponse(UserProfileResponse.Builder builder){
        super(builder);
        this.user = builder.user;
    }

    public static class Builder extends AbstractResponse.Builder<UserProfileResponse, UserProfileResponse.Builder>{

        private User user;

        public Builder() {
            super();
        }

        @Override
        public UserProfileResponse build(){ return new UserProfileResponse(this);}

        @Override
        public UserProfileResponse.Builder self() {
            return this;
        }

        public UserProfileResponse.Builder user(User user) {
            this.user = user;
            return this;
        }
    }

    public User getUser() {
        return user;
    }
}
