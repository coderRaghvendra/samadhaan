package com.samadhaan4u.service.response;

import java.util.List;

/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public abstract class AbstractResponse implements Response{
    private boolean success;
    private String message;
    private List<ResponseError> errors;

    public AbstractResponse() {}

    public AbstractResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static abstract class Builder<E extends Response>{
        private boolean success;
        private String message;
        private List<ResponseError> errors;

        protected Builder() {}

        public abstract E build();


        public void success(boolean success) {
            this.success = success;
        }

        public void message(String message) {
            this.message = message;
        }

        public void errors(List<ResponseError> errors) {
            this.errors = errors;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseError> getErrors() {
        return errors;
    }

    public void setErrors(List<ResponseError> errors) {
        this.errors = errors;
    }
}
