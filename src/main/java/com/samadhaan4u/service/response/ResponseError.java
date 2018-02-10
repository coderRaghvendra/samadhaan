package com.samadhaan4u.service.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raghvendra.mishra on 03/02/18.
 */
public enum ResponseError {

    EMAIL_ALREADY_REGISTERED(1, "Email Already Registered."),
    INTERNAL_ERROR(2, "Internal Error."),
    NO_ERROR(3, "No Error"),
    WRONG_EMAIL(4,"Wrong Email"),
    WRONG_PASSWORD(5,"Wrong Password");

    private static final Logger logger = LoggerFactory.getLogger(ResponseError.class);
    private final int code;
    private final String description;

    ResponseError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private static final Map<Integer, ResponseError> errors = new HashMap<Integer, ResponseError>();

    static {
        for (ResponseError error : ResponseError.values()) {
            if (errors.get(error.code) == null) {
                errors.put(error.code, error);
            } else {
                logger.info("Duplicate errors.");
                throw new RuntimeException();
            }
        }
    }

    public static ResponseError valueOf(int id) {
        return errors.get(id);
    }

    public int code() {
        return code;
    }

    public String description() {
        return description;
    }
}
