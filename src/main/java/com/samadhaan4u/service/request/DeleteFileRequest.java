package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.service.response.DeleteFileResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * Created by raghvendra.mishra on 10/06/18.
 */
public class DeleteFileRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFileRequest.class);
    private long documentId;

    public DeleteFileRequest(){ super();}

    protected DeleteFileRequest(Builder builder){
        super(builder);
        this.documentId = builder.documentId;
    }

    public static class Builder extends AbstractRequest.Builder<DeleteFileRequest, Builder>{
        private long documentId;

        public Builder() {
            super();
        }

        @Override
        public DeleteFileRequest build(){ return new DeleteFileRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder file(long documentId) {
            this.documentId = documentId;
            return this;
        }
    }

    public Response process(){
        logger.info("DeleteFileRequest received, being processed.");
        DeleteFileResponse.Builder gudrBuilder = new DeleteFileResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
//            success = DaoManager.documentDao().de
            rBuilder.message(ResponseMessage.FILE_UPLOADED);
            success = true;
            logger.info("DeleteFileRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing DeleteFileRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return gudrBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}