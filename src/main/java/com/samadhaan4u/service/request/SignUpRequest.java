package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.dao.UserDao;
import com.samadhaan4u.model.entity.User;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SignUpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by raghvendra.mishra on 01/02/18.
 */
public class SignUpRequest extends AbstractRequest{

    private static final Logger logger = LoggerFactory.getLogger(SignUpRequest.class);
    private String email;
    private String password;

    public SignUpRequest(){super();}
    protected SignUpRequest(Builder builder){
        super(builder);
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder extends AbstractRequest.Builder<SignUpRequest, Builder>{

        private String email;
        private String password;

        public Builder() {
            super();
        }

        @Override
        public SignUpRequest build(){ return new SignUpRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }
    }

    public Response process(){

        SignUpResponse.Builder srBuilder = new SignUpResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        String emailKey = email+ UUID.randomUUID().toString();
        //check if email already exist
        if(DaoManager.userDao().isEmailExist(email)){
            result = rBuilder.success(false).message(ResponseMessage.USER_ALREADY_REGISTERED).build();
        }else if(DaoManager.userDao().insert(email, password, emailKey)){

            String link = "localhost:8080/verifyEmail?emailKey=" + emailKey;
            String subject = "Email verification from  www.samadhaan.com";
            String mailContent = "<div>please ch click on" +
                    " this link to verify your email <a href=\"www.google.com\">click</a></div>";
            //send verification mail
            SendMailRequest.Builder smBuilder = new SendMailRequest.Builder();
            Response mailResponse = smBuilder.from("qwertyraghav@gmail.com").password("_Raghav@385848").to(email)
                    .sub(subject).mailContent(createMailContent(emailKey)).build().process();
            if(mailResponse.getResult().isSuccess()){
                logger.info("Verification mail sent successfully.");
                result = rBuilder.success(true).message(ResponseMessage.VERIFY_EMAIL).build();
            }
            else {
                logger.info("Verification mail could not be sent.");
                result = rBuilder.success(true).message(ResponseMessage.INTERNAL_ERROR).build();
            }
        }else
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();

        return srBuilder.result(result).build();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    static String createMailContent(String emailKey){

        String messageHtml = "<div style=\"text-align: center\"><div>" +
                "<div style=\"color : #cc6600\"><h2>Samadhaan</h2></div>" +
                "<h3>Thanks for registering with us !!</h3>" +
                "<div><img src=\"https://drive.google.com/file/d/0B1S3zdUEkxMaZ0hmaVhZRkRib2M/view\" style=\"height: " +
                "500px; width: 500px;\"/></div></div><br/><br/>\n" +
                "    <div style=\"opacity: 0.8\"><h3>You have registered successfully. " +
                "Click below to verify your email   id.</h3></div><br/>\n" +
                "    <div style=\"height: 200px;\"><a href=\"http://localhost:8080/verifyEmail?emailKey=" + emailKey +
                "\">\n<span style=\"color: white; background: #cc6600; padding: 10px 20px;" +
                " border-radius: 3px;\">Verify Email</span></a></div>\n" +
                "</div>";
        return messageHtml;
    }
}
