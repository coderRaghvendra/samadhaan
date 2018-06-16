package com.samadhaan4u.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by raghvendra.mishra on 19/05/18.
 */
public class UserDataDto {
    private static final Logger logger = LoggerFactory.getLogger(UserDataDto.class);
    private UserDto userDto;
    private List<DocumentDto> documentDtos;

    protected UserDataDto(Builder builder){
        this.userDto = builder.userDto;
        this.documentDtos = builder.documentDtos;
    }

    public static class Builder {

        private UserDto userDto;
        private List<DocumentDto> documentDtos;

        public Builder() {
            super();
        }

        public UserDataDto build() {
            return new UserDataDto(this);
        }

        public Builder self() {
            return this;
        }

        public Builder userDto(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public Builder documentDtos(List<DocumentDto> documentDtos) {
            this.documentDtos = documentDtos;
            return this;
        }
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public List<DocumentDto> getDocumentDtos() {
        return documentDtos;
    }
}
