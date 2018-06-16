package com.samadhaan4u.dto;

import com.samadhaan4u.dto.constant.UserType;
import com.samadhaan4u.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghvendra.mishra on 19/05/18.
 */
public class UserDto {
    private static final Logger logger = LoggerFactory.getLogger(UserDto.class);
    private long id;
    private String fname;
    private String lname;
    private String email;
    private long phoneNo;
    private boolean emailVerified;
    private UserType type;
    private Timestamp creationTime;

    protected UserDto(Builder builder){
        this.id = builder.id;
        this.fname = builder.fname;
        this.lname = builder.lname;
        this.email = builder.email;
        this.phoneNo = builder.phoneNo;
        this.emailVerified = builder.emailVerified;
        this.type = builder.type;
        this.creationTime = builder.creationTime;
    }

    public static class Builder{

        private long id;
        private String fname;
        private String lname;
        private String email;
        private long phoneNo;
        private boolean emailVerified;
        private UserType type;
        private Timestamp creationTime;

        public Builder() {
            super();
        }

        public Builder(User user) {
            super();
            id(user.getId()).fname(user.getFname()).lname(user.getLname()).email(user.getEmail())
                    .phoneNo(user.getPhoneNo()).emailVerified(user.isEmailVerified())
                    .type(user.getType()).creationTime(user.getCreationTime());
        }

        public UserDto build(){ return new UserDto(this);}

        public Builder self() {
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder fname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder lname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNo(long phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public Builder emailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        public Builder type(UserType type) {
            this.type = type;
            return this;
        }

        public Builder creationTime(Timestamp creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public Builder entity(User user){
            return this.id(user.getId()).fname(user.getFname()).lname(user.getLname()).email(user.getEmail())
                    .phoneNo(user.getPhoneNo()).emailVerified(user.isEmailVerified()).type(user.getType())
                    .creationTime(user.getCreationTime());
        }
    }

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public UserType getType() {
        return type;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }
}
