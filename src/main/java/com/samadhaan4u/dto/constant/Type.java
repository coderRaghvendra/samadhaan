package com.samadhaan4u.dto.constant;

import com.samadhaan4u.model.entity.Entity;

/**
 * Created by raghvendra.mishra on 05/04/18.
 */
public interface Type<E extends Entity> {
    int id();

    String name();

    String description();
}
