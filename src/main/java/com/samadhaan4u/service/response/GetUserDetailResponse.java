package com.samadhaan4u.service.response;

import com.samadhaan4u.dto.DocumentDto;
import com.samadhaan4u.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 13/06/18.
 */
public class GetUserDetailResponse extends AbstractResponse{
    private static final Logger logger = LoggerFactory.getLogger(GetUserDetailResponse.class);
    private UserDto user;
    private List<DocumentDto> documents;

    protected GetUserDetailResponse(Builder builder){
        super(builder);
        this.user = builder.user;
        this.documents = builder.documents;
    }

    public static class Builder extends AbstractResponse.Builder<GetUserDetailResponse, Builder>{

        private UserDto user;
        private List<DocumentDto> documents;

        public Builder() {
            super();
        }

        @Override
        public GetUserDetailResponse build(){ return new GetUserDetailResponse(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder userDto(UserDto userDto) {
            this.user = userDto;
            return this;
        }

        public Builder documents(List<DocumentDto> documents) {
            this.documents = documents;
            return this;
        }
    }

    public UserDto getUser() {
        return user;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }
}
