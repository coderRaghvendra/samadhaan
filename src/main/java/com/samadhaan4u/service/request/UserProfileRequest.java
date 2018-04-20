package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UserDocumentResponse;
import com.samadhaan4u.service.response.UserProfileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class UserProfileRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileRequest.class);

    protected UserProfileRequest(UserProfileRequest.Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<UserProfileRequest, UserProfileRequest.Builder>{

        public Builder() {
            super();
        }

        @Override
        public UserProfileRequest build(){ return new UserProfileRequest(this);}

        @Override
        public UserProfileRequest.Builder self() {
            return this;
        }
    }

    @Override
    public Response process(){

        UserProfileResponse.Builder uprBuilder = new UserProfileResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        User user = DaoManager.userDao().select(this.userId);
        if(user != null){
            result = rBuilder.success(true).build();
            uprBuilder.user(user);

        }else{
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
        }
        return uprBuilder.result(result).build();
    }
}
