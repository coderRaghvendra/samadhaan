package com.samadhaan4u.service.request;

/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public abstract class AbstractRequest implements Request {

    protected AbstractRequest(Builder<?, ?> b) {}

    public static abstract class Builder<E extends Request, T extends Builder<E, T>> {

        protected Builder() {}

        public abstract E build();

        public abstract T self();
    }
}
