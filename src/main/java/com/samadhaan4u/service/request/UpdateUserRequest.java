package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UpdateUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 23/03/18.
 */
public class UpdateUserRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserRequest.class);
    private User user;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private long phoneNo;
    private boolean emailVerified;
    private String emailKey;
    private boolean status;

    public UpdateUserRequest() { super(); }

    protected UpdateUserRequest(Builder builder){
        super(builder);
        this.fname = builder.fname;
        this.lname = builder.lname;
        this.email = builder.email;
        this.password = builder.password;
        this.phoneNo = builder.phoneNo;
        this.emailVerified = builder.emailVerified;
        this.emailKey = builder.emailKey;
        this.status = builder.status;
    }

    public static class Builder extends AbstractRequest.Builder<UpdateUserRequest, Builder>{

        private String fname;
        private String lname;
        private String email;
        private String password;
        private long phoneNo;
        private boolean emailVerified;
        private String emailKey;
        private boolean status;

        public Builder() {
            super();
        }

        @Override
        public UpdateUserRequest build(){ return new UpdateUserRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder fname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder lname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phoneNo(long phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }
        public Builder emailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder emailKey(String emailKey) {
            this.emailKey = emailKey;
            return this;
        }

        public Builder status(boolean status) {
            this.status = status;
            return this;
        }
    }

    public Response process(){
        logger.info("in uoload file request, userid = " + this.userId);
//        UpdateUserResponse.Builder ufBuilder = new UpdateUserResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        try{
            if(DaoManager.userDao().updateUser(this.user)){
                result = rBuilder.success(true).message(ResponseMessage.FILE_UPLOADED).build();
            }else{
                result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            }
        } catch(Exception e){
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            e.printStackTrace();
        }return null;
//        return ufBuilder.result(result).build();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
