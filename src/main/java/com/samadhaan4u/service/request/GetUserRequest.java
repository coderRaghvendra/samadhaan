package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.GetUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class GetUserRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(GetUserRequest.class);
    private String email;

    protected GetUserRequest(Builder builder){
        super(builder);
        this.email = builder.email;
    }

    public static class Builder extends AbstractRequest.Builder<GetUserRequest, GetUserRequest.Builder>{
        private String email;

        public Builder() {
            super();
        }

        public Builder(long userId) {
            super();
            userId(userId);
        }

        public Builder(String email) {
            super();
            email(email);
        }

        @Override
        public GetUserRequest build(){ return new GetUserRequest(this);}

        @Override
        public GetUserRequest.Builder self() {
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }
    }

    @Override
    public Response process(){
        logger.info("GetUserRequest received, being processed.");
        GetUserResponse.Builder gurBuilder = new GetUserResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            if(email != null) {
                gurBuilder.userDto(new UserDto.Builder(DaoManager.userDao().get(this.email)
                        .orElse(null)).build());
            } else {
                gurBuilder.userDto(new UserDto.Builder(DaoManager.userDao().get(this.userId)
                        .orElse(null)).build());
            }
            success = true;
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED.NO_ERROR);
            logger.info("GetUserRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing GetUserRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return gurBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}
