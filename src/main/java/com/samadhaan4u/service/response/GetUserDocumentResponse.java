package com.samadhaan4u.service.response;

import com.samadhaan4u.dto.DocumentDto;
import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class GetUserDocumentResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDocumentResponse.class);
    private List<DocumentDto> documents;

    protected GetUserDocumentResponse(Builder builder){
        super(builder);
        this.documents = builder.documents;
    }

    public static class Builder extends AbstractResponse.Builder<GetUserDocumentResponse, Builder>{

        private List<DocumentDto> documents;

        public Builder() {
            super();
        }

        @Override
        public GetUserDocumentResponse build(){ return new GetUserDocumentResponse(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder documents(List<DocumentDto> documents) {
            this.documents = documents;
            return this;
        }
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }
}
