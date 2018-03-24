package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 15/03/18.
 */
public class UserDocumentResponse extends AbstractResponse {
    private static final Logger logger = LoggerFactory.getLogger(UserDocumentResponse.class);
    private User user;
    private List<Document> documentList;

    protected UserDocumentResponse(Builder builder){
        super(builder);
        this.user = builder.user;
        this.documentList = builder.documentList;
    }

    public static class Builder extends AbstractResponse.Builder<UserDocumentResponse, Builder>{

        private User user;
        private List<Document> documentList;

        public Builder() {
            super();
        }

        @Override
        public UserDocumentResponse build(){ return new UserDocumentResponse(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder documentList(List<Document> documentList) {
            this.documentList = documentList;
            return this;
        }
    }

    public User getUser() {
        return user;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }
}
