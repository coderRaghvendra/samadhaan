package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.service.response.ResourceResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 03/05/18.
 */
public class ResourceRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(ResourceRequest.class);

    public ResourceRequest() { super(); }

    protected ResourceRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<ResourceRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public ResourceRequest build(){ return new ResourceRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    public Response process(){
        ResourceResponse.Builder rrBuilder = new ResourceResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result = rBuilder.success(false).message(ResponseMessage.PERMISSION_DENIED).build();
        return rrBuilder.userId(this.userId).result(result).build();
    }
}
