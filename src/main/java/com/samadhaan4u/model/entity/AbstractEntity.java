package com.samadhaan4u.model.entity;

/**
 * Created by raghvendra.mishra on 26/02/18.
 */
public class AbstractEntity implements Entity{

    protected long id;

    public AbstractEntity () {}

    protected AbstractEntity(Builder<?, ?> b) {
        this.id = b.id;
    }

    public static abstract class Builder<E extends Entity, T extends Builder<E, T>> {
        private long id;

        protected Builder() {}

        public abstract E build();

        public abstract T self();

        public T id(long id) {
            this.id = id;
            return self();
        }
    }

    @Override
    public long getId() {
        return id;
    }
}
