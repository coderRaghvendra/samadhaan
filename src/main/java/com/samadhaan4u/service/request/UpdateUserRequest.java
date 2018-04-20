package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UpdateUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by raghvendra.mishra on 23/03/18.
 */
public class UpdateUserRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserRequest.class);
    private User user;

    public UpdateUserRequest() { super(); }

    protected UpdateUserRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<UpdateUserRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public UpdateUserRequest build(){ return new UpdateUserRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        logger.info("in uoload file request, userid = " + this.userId);
        logger.info("user update phone no = " + this.user.getPhoneNo());
        UpdateUserResponse.Builder uurBuilder = new UpdateUserResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        try{
            User oldUser = DaoManager.userDao().select(this.userId);
            boolean updateFlag = false;
            if(StringUtils.isNotBlank(this.user.getFname()) || StringUtils.isNotBlank(this.user.getLname())){
                updateFlag = true;
                oldUser.setFname(this.user.getFname());
                oldUser.setLname(this.user.getLname());
            } else if(this.user.getPhoneNo() > 0){
                updateFlag = true;
                oldUser.setPhoneNo(this.user.getPhoneNo());
            }
            logger.info("user update phone no = " + this.user.getPhoneNo());
            if(updateFlag && DaoManager.userDao().updateUser(oldUser)){
                result = rBuilder.success(true).message(ResponseMessage.USER_UPDATED).build();
            } else{
                result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            }
        } catch(Exception e){
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            e.printStackTrace();
        }
        return uurBuilder.result(result).build();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
