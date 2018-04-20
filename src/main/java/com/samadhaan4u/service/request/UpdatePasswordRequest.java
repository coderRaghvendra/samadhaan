package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UpdatePasswordResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 02/04/18.
 */
public class UpdatePasswordRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordRequest.class);
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public UpdatePasswordRequest() { super(); }

    protected UpdatePasswordRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<UpdatePasswordRequest, Builder>{

        private String oldPassword;
        private String newPassword;
        private String newPasswordConfirm;

        public Builder() {
            super();
        }

        @Override
        public UpdatePasswordRequest build(){ return new UpdatePasswordRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        UpdatePasswordResponse.Builder uurBuilder = new UpdatePasswordResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        try{
            User oldUser = DaoManager.userDao().select(this.userId);
            if(StringUtils.isNotBlank(this.oldPassword) && StringUtils.isNotBlank(this.newPasswordConfirm)
                    && StringUtils.isNotBlank(this.newPassword)){
                if(oldPassword.equals(oldUser.getPassword())){
                    oldUser.setPassword(this.newPassword);
                    if(newPassword.equals(newPasswordConfirm) && DaoManager.userDao().updateUser(oldUser)){
                        result = rBuilder.success(true).message(ResponseMessage.USER_UPDATED).build();
                    } else {
                        result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
                    }
                }
                else{
                    result = rBuilder.success(false).message(ResponseMessage.WRONG_PASSWORD).build();
                }
            } else {
                result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            }
        } catch(Exception e){
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            e.printStackTrace();
        }
        return uurBuilder.result(result).build();
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
