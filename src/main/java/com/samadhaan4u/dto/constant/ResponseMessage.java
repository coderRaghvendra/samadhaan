package com.samadhaan4u.dto.constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raghvendra.mishra on 23/02/18.
 */
public enum ResponseMessage{
    NO_ERROR(0, "No Error", "No Error.", null),
    EXCEPTION_OCCURRED(1, "Exception occurred", "Exception occurred.", null),
    INTERNAL_ERROR(2, "Internal Error", "Sorry... something went wrong.", "sorry.png"),
    USER_ALREADY_REGISTERED(3, "User Already Registered","User with this email id is already registered.", "face-256.png"),
    EMAIL_NOT_VERIFIED(4, "Email Not Verified", "Email is not verified. Please verify your email and then sign in.", "mail-envelopes-couple.png"),
    REGISTERED_SUCCESSFULLY(5, "Registered Successfully", "You got registered successfully. Check your mail to verify and then sign in.", "mail-envelopes-couple.png"),
    EMAIL_NOT_REGISTERED(6, "Email Not Registered", "This email is not registered with us. Please sign up.", "mail-envelopes-couple.png"),
    WRONG_PASSWORD(7, "Wrong Password", "Password you entered is wrong. Please enter correct password.", "sorry.png"),
    EMAIL_VERIFIED(8, "Email Verified", "Congrats!! Email is verified. Please sign in.", "sign-up.png"),
    MAIL_SENT(9, "Mail Sent", "Mail sent successfully.", null),
    SIGN_IN_SUCCESS(10, "Sign In Successful.", "Sign In Successful", null),
    FILE_UPLOADED(11, "File Uploaded", "File uploaded successfully.", null),
    NEW_PASSWORD_SENT(12, "New Password Sent", "A temporary password has been sent to your mail. " +
            "Use it to sign in and create your new password.", "lock.png"),
    USER_UPDATED(13, "User Updated", "Your changes have been saved", ""),
    PERMISSION_DENIED(14, "Permission Denied", "You do not have access to this page", ""),
    NOT_FOUND(15, "Not Found", "No file found for this id.", "")
    ;

    private final int id;
    private final String heading;
    private final String description;
    private final String imgPath;

    ResponseMessage(int id, String heading, String description, String imgPath) {
        this.id = id;
        this.description = description;
        this.heading = heading;
        this.imgPath = "/resources/image/application/" + imgPath;
    }

    private static final Map<Integer, ResponseMessage> messageMap = new HashMap<>();

    static {
        for (ResponseMessage message : ResponseMessage.values()) {
            messageMap.put(message.id, message);
        }
    }

    public static ResponseMessage valueOf(int id) {
        return messageMap.get(id);
    }

    public int getId() {
        return id;
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
