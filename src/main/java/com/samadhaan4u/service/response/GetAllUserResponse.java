package com.samadhaan4u.service.response;

import com.samadhaan4u.dto.UserDataDto;
import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class GetAllUserResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(GetAllUserResponse.class);
    private List<UserDto> users;

    protected GetAllUserResponse(Builder builder){
        super(builder);
        this.users = builder.users;
    }

    public static class Builder extends AbstractResponse.Builder<GetAllUserResponse, Builder>{

        private List<UserDto> users;

        public Builder() {
            super();
        }

        @Override
        public GetAllUserResponse build(){ return new GetAllUserResponse(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder users(List<UserDto> users) {
            this.users = users;
            return this;
        }
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
