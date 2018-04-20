package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.GetUsersResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class GetUsersRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(GetUsersRequest.class);

    public GetUsersRequest() { super(); }

    protected GetUsersRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<GetUsersRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public GetUsersRequest build(){ return new GetUsersRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        logger.info("in udrProcess : userId = "  + this.userId);
        GetUsersResponse.Builder udrBuilder = new GetUsersResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        List<User> userList = DaoManager.userDao().selectMultiple();
        if(userList != null){
            result = rBuilder.success(true).build();
            udrBuilder.userList(userList);
        }else{
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
        }
        return udrBuilder.userId(this.userId).result(result).build();
    }
}
