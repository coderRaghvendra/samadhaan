package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.SendMailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class SendMailRequest  extends AbstractRequest{
    private static final Logger logger = LoggerFactory.getLogger(SendMailRequest.class);
    private String from;
    private String password;
    private String to;
    private String sub;
    private String mailContent;

    protected SendMailRequest(Builder builder) {
        super(builder);
        this.from = builder.from;
        this.password = builder.password;
        this.to = builder.to;
        this.sub = builder.sub;
        this.mailContent = builder.mailContent;
    }

    public static class Builder extends AbstractRequest.Builder<SendMailRequest, Builder>{
        private String from;
        private String password;
        private String to;
        private String sub;
        private String mailContent;

        public Builder() {
            super();
        }

        public Builder(String to, String sub, String mailContent) {
            super();
            from("qwertyraghav@gmail.com").password("_Raghav@385848").to(to).sub(sub).mailContent(mailContent);
        }

        @Override
        public SendMailRequest build(){ return new SendMailRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return self();
        }

        public Builder password(String password) {
            this.password = password;
            return self();
        }

        public Builder to(String to) {
            this.to = to;
            return self();
        }

        public Builder sub(String sub) {
            this.sub = sub;
            return self();
        }

        public Builder mailContent(String mailContent) {
            this.mailContent = mailContent;
            return self();
        }
    }

    public Response process(){
        SendMailResponse.Builder srBuilder = new SendMailResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
//            message.setText(mailContent);
            message.setContent(mailContent, "text/html; charset=utf-8");
            //send message
            Transport.send(message);
            logger.info("message sent successfully");
            rBuilder.success(true).message(ResponseMessage.MAIL_SENT).build();
        } catch (MessagingException e) {
            rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            throw new RuntimeException(e);
        }
        return srBuilder.result(rBuilder.build()).build();
    }

    public String getFrom() {
        return from;
    }

    public String getPassword() {
        return password;
    }

    public String getTo() {
        return to;
    }

    public String getSub() {
        return sub;
    }

    public String getMailContent() {
        return mailContent;
    }

        public static void main(String[] args) {

            String link = "http://localhost:8080/verifyEmail?emailKey=xyz";
            String subject = "Email verification from  www.samadhaan.com";
            String mailContent = "<div>please chs click on<h1>hello</h1>" +
                    " this link to verify your email <a href=\"www.google.com\">click</a></div>";
            //send verification mail
            SendMailRequest.Builder smBuilder = new SendMailRequest.Builder();
            Response mailResponse = smBuilder.from("qwertyraghav@gmail.com").password("_Raghav@385848").to("qwertyraghav@gmail.com")
                    .sub(subject).mailContent(createMailContent("abc")).build().process();
            System.out.println(mailResponse.getResult().isSuccess());
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