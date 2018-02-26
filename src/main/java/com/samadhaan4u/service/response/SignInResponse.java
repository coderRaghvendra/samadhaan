package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignInResponse extends AbstractResponse{

    private static final Logger logger = LoggerFactory.getLogger(SignInResponse.class);
    private User user;
    private List<Document> documentList;

    protected SignInResponse(Builder builder){
        super(builder);
        this.user = builder.user;
        this.documentList = builder.documentList;
    }

    public static class Builder extends AbstractResponse.Builder<SignInResponse, Builder>{

        private User user;
        private List<Document> documentList;

        public Builder() {
            super();
        }

        @Override
        public SignInResponse build(){ return new SignInResponse(this);}

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
