package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class GetUsersResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(GetUsersResponse.class);
    private List<User> userList;

    protected GetUsersResponse(Builder builder){
        super(builder);
        this.userList = builder.userList;
    }

    public static class Builder extends AbstractResponse.Builder<GetUsersResponse, Builder>{

        private List<User> userList;

        public Builder() {
            super();
        }

        @Override
        public GetUsersResponse build(){ return new GetUsersResponse(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder userList(List<User> userList) {
            this.userList = userList;
            return this;
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
