package com.samadhaan4u.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class Document  extends AbstractEntity{

    private static final Logger logger = LoggerFactory.getLogger(Document.class);
    private String name;
    private long userId;
    private Timestamp uploadDate;
    private String description;
    private String path;

    protected Document(Builder builder){
        super(builder);
        this.name = builder.name;
        this.userId = builder.userId;
        this.uploadDate = builder.uploadDate;
        this.description = builder.description;
        this.path = builder.path;
    }

    public static class Builder extends AbstractEntity.Builder<Document, Builder>{

        private String name;
        private long userId;
        private Timestamp uploadDate;
        private String description;
        private String path;

        public Builder() {
            super();
        }

        @Override
        public Document build(){ return new Document(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder uploadDate(Timestamp uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }
}
