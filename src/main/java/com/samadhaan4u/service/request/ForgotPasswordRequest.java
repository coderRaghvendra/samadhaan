package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.service.response.ForgotPasswordResponse;
import com.samadhaan4u.service.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Created by raghvendra.mishra on 11/03/18.
 */
public class ForgotPasswordRequest extends AbstractRequest {
    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordRequest.class);
    private String email;

    public ForgotPasswordRequest(){super();}
    protected ForgotPasswordRequest(Builder builder){
        super(builder);
        this.email = builder.email;
    }

    public static class Builder extends AbstractRequest.Builder<ForgotPasswordRequest, Builder>{

        private String email;

        public Builder() {
            super();
        }

        @Override
        public ForgotPasswordRequest build(){ return new ForgotPasswordRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }

    public Response process(){

        ForgotPasswordResponse.Builder srBuilder = new ForgotPasswordResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        String newPassword = getNewPassword(email);
        String subject = "New Temporary Password";
        String content = "New Temporary Password = " + newPassword;
        SendMailRequest.Builder smBuilder = new SendMailRequest.Builder();
        Response mailResponse = smBuilder.from("qwertyraghav@gmail.com").password("_Raghav@385848").to(email)
                .sub(subject).mailContent(content).build().process();
        if(mailResponse.getResult().isSuccess() && DaoManager.userDao().updatePassword(email, newPassword)){
            logger.info("New Password mail sent successfully.");
            result = rBuilder.success(true).message(ResponseMessage.NEW_PASSWORD_SENT).build();
        } else{
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
        }
        return srBuilder.result(result).build();
    }

    public String getEmail() {
        return email;
    }

    private static String getNewPassword(String email){
        Random random = new Random();
        return email.substring(0,4) + String.valueOf(random.nextInt(100_000));
    }
}
