package com.samadhaan4u.service.request;

/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public abstract class AbstractRequest implements Request {

    protected long userId;

    public AbstractRequest(){}

    protected AbstractRequest(Builder<?, ?> builder) {
        this.userId = builder.userId;
    }

    public static abstract class Builder<E extends Request, T extends Builder<E, T>> {

        private long userId;

        protected Builder() {}

        public abstract E build();

        public abstract T self();

        public T userId(long userId) {
            this.userId = userId;
            return self();
        }
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
