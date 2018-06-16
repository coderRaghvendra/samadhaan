package com.samadhaan4u.service.response;

import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class GetUserResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(GetUserResponse.class);
    private UserDto userDto;

    protected GetUserResponse(GetUserResponse.Builder builder){
        super(builder);
        this.userDto = builder.userDto;
    }

    public static class Builder extends AbstractResponse.Builder<GetUserResponse, GetUserResponse.Builder>{

        private UserDto userDto;

        public Builder() {
            super();
        }

        @Override
        public GetUserResponse build(){ return new GetUserResponse(this);}

        @Override
        public GetUserResponse.Builder self() {
            return this;
        }

        public GetUserResponse.Builder userDto(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
