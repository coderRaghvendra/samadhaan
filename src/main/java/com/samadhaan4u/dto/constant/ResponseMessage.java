package com.samadhaan4u.dto.constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raghvendra.mishra on 23/02/18.
 */
public enum ResponseMessage{
    USER_ALREADY_REGISTERED(1, "Already Registered","User with this email id is already registered", "face-256.png"),
    INTERNAL_ERROR(2, "Sorry", "Internal Error", "sorry.png"),
    NO_ERROR(3, "No Error", "No Error", null),
    EMAIL_NOT_VERIFIED(4, "Verify Email", "Email not verified. Please verify your email", "mail-envelopes-couple.png"),
    VERIFY_EMAIL(5, "Congrats", "You got registered successfully. Check your mail to verify and then log in", "mail-envelopes-couple.png"),
    EMAIL_NOT_REGISTERED(6, "Sign Up", "This email is not registered with us. Please sign up", "mail-envelopes-couple.png"),
    WRONG_PASSWORD(7, "Wrong password", "Password you entered is wrong", "sorry.png"),
    EMAIL_VERIFIED(8, "Congrats", "Congrats!! Email is verified. Please log in", "sign-up.png"),
    MAIL_SENT(9, "Mail Sent", "Mail sent successfully", null),
    SIGN_IN_SUCCESS(10, "Sign In Successful", "Sign In Successful", null),
    ;

    private final int code;
    private final String heading;
    private final String description;
    private final String imgPath;

    ResponseMessage(int code, String heading, String description, String imgPath) {
        this.code = code;
        this.description = description;
        this.heading = heading;
        this.imgPath = "/resources/image/application/" + imgPath;
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

    public String getHeading() {
        return heading;
    }

    public String getImgPath() {
        return imgPath;
    }
}
