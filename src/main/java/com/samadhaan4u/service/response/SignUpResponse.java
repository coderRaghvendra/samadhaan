package com.samadhaan4u.service.response;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;

import java.util.List;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignUpResponse {

    private String message;
    private User user;
    private List<Document> documentList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
}
