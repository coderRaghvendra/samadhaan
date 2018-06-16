package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.DocumentDto;
import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.UserDto;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.GetUserDetailResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by raghvendra.mishra on 12/06/18.
 */
public class GetUserDetailRequest extends AbstractRequest{
    private static final Logger logger = LoggerFactory.getLogger(GetUserDetailRequest.class);
    private String email;

    protected GetUserDetailRequest(Builder builder){
        super(builder);
        this.email = builder.email;
    }

    public static class Builder extends AbstractRequest.Builder<GetUserDetailRequest, Builder>{
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
        public GetUserDetailRequest build(){ return new GetUserDetailRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }
    }

    @Override
    public Response process(){
        logger.info("GetUserDetailRequest received, being processed.");
        GetUserDetailResponse.Builder gudrBuilder = new GetUserDetailResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            User user;
            if(email != null) {
                user = DaoManager.userDao().get(this.email).orElse(null);
            } else {
                user = DaoManager.userDao().get(this.userId).orElse(null);
            }
            gudrBuilder.userDto(new UserDto.Builder(user).build());
            gudrBuilder.documents(DaoManager.documentDao().getByUserId(user.getId()).orElse(new ArrayList<>())
                    .stream().map(d -> new DocumentDto.Builder(d).build()).collect(Collectors.toList()));
            success = true;
            rBuilder.message(ResponseMessage.NO_ERROR);
            logger.info("GetUserDetailRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing GetUserDetailRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return gudrBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}
