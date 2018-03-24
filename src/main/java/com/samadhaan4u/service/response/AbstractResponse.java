package com.samadhaan4u.service.response;

import com.samadhaan4u.dto.Result;
/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public abstract class AbstractResponse implements Response{
    protected Result result;
    protected long userId;

    protected AbstractResponse(Builder<?, ?> b) {
        result = b.result;
        userId = b.userId;
    }

    public static abstract class Builder<E extends Response, T extends Builder<E, T>> {
        private Result result;
        private long userId;

        protected Builder() {}

        public abstract E build();

        public abstract T self();

        public T result(Result result) {
            this.result = result;
            return self();
        }

        public T userId(long userId) {
            this.userId = userId;
            return self();
        }
    }

    @Override
    public Result getResult() {
        return result;
    }

    @Override
    public long getUserId() {
        return userId;
    }
}
