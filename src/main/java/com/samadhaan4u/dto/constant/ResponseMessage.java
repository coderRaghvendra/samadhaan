package com.samadhaan4u.dto.constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raghvendra.mishra on 23/02/18.
 */
public enum ResponseMessage{
    USER_ALREADY_REGISTERED(1, "User with this email id is already registered."),
    INTERNAL_ERROR(2, "Internal Error."),
    NO_ERROR(3, "No Error"),
    EMAIL_NOT_VERIFIED(4,"Email not verified. Please verify your email."),
    VERIFY_EMAIL(5,"You got registered successfully.Check your mail to verify and then log in."),
    EMAIL_NOT_REGISTERED(6,"This email is not registered with us.Please sign up."),
    WRONG_PASSWORD(7,"Password you entered is wrong."),
    EMAIL_VERIFIED(8, "Congrats!! Email is verified. Please log in.");

    private final int code;
    private final String description;

    ResponseMessage(int code, String description) {
        this.code = code;
        this.description = description;
    }

//    private static final Map<Integer, ResponseMessage> messageMap = new HashMap<>();
//
//    static {
//        for (ResponseMessage message : ResponseMessage.values()) {
//            if (messageMap.get(message.code) == null) {
//                messageMap.put(message.code, message);
//            }
//        }
//    }
//
//    public static ResponseMessage valueOf(int id) {
//        return messageMap.get(id);
//    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
