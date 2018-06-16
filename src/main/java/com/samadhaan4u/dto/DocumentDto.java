package com.samadhaan4u.dto;

import com.samadhaan4u.model.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghvendra.mishra on 19/05/18.
 */
public class DocumentDto {
    private static final Logger logger = LoggerFactory.getLogger(DocumentDto.class);
    private long id;
    private String name;
    private Timestamp uploadDate;
    private String description;

    protected DocumentDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.uploadDate = builder.uploadDate;
        this.description = builder.description;
    }

    public static class Builder{
        private long id;
        private String name;
        private Timestamp uploadDate;
        private String description;

        public Builder() {
            super();
        }

        public Builder(Document document) {
            super();
            id(document.getId()).name(document.getName()).uploadDate(document.getUploadDate())
                    .description(document.getDescription());
        }

        public DocumentDto build(){ return new DocumentDto(this);}

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
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
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public String getDescription() {
        return description;
    }
}
