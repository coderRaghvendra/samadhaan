package com.samadhaan4u.dto;

import com.samadhaan4u.dto.constant.ResponseMessage;
import java.util.List;

/**
 * Created by raghvendra.mishra on 23/02/18.
 */
public class Result {
    private boolean success;
    private ResponseMessage message;

    public Result(Builder builder) {
        this.success = builder.success;
        this.message = builder.message;
    }

    public static class Builder{
        private boolean success;
        private ResponseMessage message;

        public Builder() {}

        public Result build(){ return new Result(this);};

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(ResponseMessage message) {
            this.message = message;
            return this;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseMessage getMessage() {
        return message;
    }
}
