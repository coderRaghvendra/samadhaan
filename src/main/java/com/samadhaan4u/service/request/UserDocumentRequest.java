package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UserDocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class UserDocumentRequest extends AbstractRequest{
    private static final Logger logger = LoggerFactory.getLogger(UserDocumentRequest.class);

    protected UserDocumentRequest() { super(); }

    protected UserDocumentRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<UserDocumentRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public UserDocumentRequest build(){ return new UserDocumentRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        logger.info("in udrProcess : userId = "  + this.userId);
        UserDocumentResponse.Builder udrBuilder = new UserDocumentResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        User user = DaoManager.userDao().select(this.userId);
        if(user != null){
            result = rBuilder.success(true).build();
            udrBuilder.user(user).documentList(DaoManager.documentDao().select(this.userId));
        }else{
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
        }
        return udrBuilder.userId(this.userId).result(result).build();
    }
}
