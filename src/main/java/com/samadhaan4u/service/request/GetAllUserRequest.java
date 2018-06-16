package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.GetAllUserResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class GetAllUserRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(GetAllUserRequest.class);

    public GetAllUserRequest() { super(); }

    protected GetAllUserRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<GetAllUserRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public GetAllUserRequest build(){ return new GetAllUserRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        logger.info("GetAllUserRequest received, being processed.");
        GetAllUserResponse.Builder gaurBuilder = new GetAllUserResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            List<User> userList = DaoManager.userDao().get().orElse(new ArrayList<>());
            if(!userList.isEmpty()){
                List<UserDto> userDtoList = userList.stream().map(user -> new UserDto.Builder(user).build())
                        .collect(Collectors.toList());
                gaurBuilder.users(userDtoList);
            } else{
                logger.info("No User found.");
            }
            success = true;
            rBuilder.message(ResponseMessage.NO_ERROR);
            logger.info("GetAllUserRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing GetAllUserRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return gaurBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}
