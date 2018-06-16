package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.DocumentDto;
import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.GetAllUserResponse;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.GetUserDocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class GetUserDocumentRequest extends AbstractRequest{
    private static final Logger logger = LoggerFactory.getLogger(GetUserDocumentRequest.class);

    public GetUserDocumentRequest() { super(); }

    protected GetUserDocumentRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends AbstractRequest.Builder<GetUserDocumentRequest, Builder>{

        public Builder() {
            super();
        }

        @Override
        public GetUserDocumentRequest build(){ return new GetUserDocumentRequest(this);}

        @Override
        public Builder self() {
            return this;
        }
    }

    @Override
    public Response process(){
        logger.info("GetUserDocumentRequest received, being processed.");
        GetUserDocumentResponse.Builder gudrBuilder = new GetUserDocumentResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            List<Document> documentList = DaoManager.documentDao().getByUserId(this.userId).orElse(new ArrayList<>());
            gudrBuilder.documents(documentList.stream().map(d -> new DocumentDto.Builder(d).build())
                    .collect(Collectors.toList()));
            success = true;
            rBuilder.message(ResponseMessage.NO_ERROR);
            logger.info("GetUserDocumentRequest processed successfully.");
        } catch(Exception e){
            logger.info("Exception occurred while processing GetUserDocumentRequest for user id {}.", this.userId);
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
        }
        return gudrBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }
}