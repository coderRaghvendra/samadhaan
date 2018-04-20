package com.samadhaan4u.model.entity;

import com.samadhaan4u.dto.constant.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class User extends AbstractEntity{

    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private String fname;
    private String lname;
    private String email;
    private String password;
    private long phoneNo;
    private boolean emailVerified;
    private String emailKey;
    private boolean status;
    private UserType type;
    private Timestamp creationTime;

    public User(){ super(); }

    protected User(Builder builder){
        super(builder);
        this.fname = builder.fname;
        this.lname = builder.lname;
        this.email = builder.email;
        this.password = builder.password;
        this.phoneNo = builder.phoneNo;
        this.emailVerified = builder.emailVerified;
        this.emailKey = builder.emailKey;
        this.status = builder.status;
        this.type = builder.type;
        this.creationTime = builder.creationTime;
    }

    public static class Builder extends AbstractEntity.Builder<User, Builder>{

        private String fname;
        private String lname;
        private String email;
        private String password;
        private long phoneNo;
        private boolean emailVerified;
        private String emailKey;
        private boolean status;
        private UserType type;
        private Timestamp creationTime;

        public Builder() {
            super();
        }

        @Override
        public User build(){ return new User(this);}

        @Override
        public Builder self() {
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

        public Builder password(String password) {
            this.password = password;
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

        public Builder emailKey(String emailKey) {
            this.emailKey = emailKey;
            return this;
        }

        public Builder status(boolean status) {
            this.status = status;
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

    public String getPassword() {
        return password;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public boolean isStatus() {
        return status;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(long phoneNo) { this.phoneNo = phoneNo; }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}