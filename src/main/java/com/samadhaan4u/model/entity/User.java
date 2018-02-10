package com.samadhaan4u.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private long id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private long phoneNo;
    private boolean emailVerified;
    private String emailKey;

    public User(){}

    public User(Builder builder) {

        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {

        private long id;
        private String fname;
        private String lname;
        private String email;
        private String password;
        private long phoneNo;
        private boolean emailVerified;
        private String emailKey;

        public Builder(){ this.phoneNo = 0;}

        public User build(){ return new User(this);}

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
}