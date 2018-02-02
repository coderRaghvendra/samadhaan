package com.samadhaan4u.dto;

import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.model.entity.User;

import java.util.List;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class UserDataDto {

    private User user;
    private List<Document> documentList;

    public UserDataDto(User user) {
        this.user = user;
    }
}
