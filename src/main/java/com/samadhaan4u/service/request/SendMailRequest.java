package com.samadhaan4u.service.request;
import com.samadhaan4u.dto.Result;
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

        @Override
        public SendMailRequest build(){ return new SendMailRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder sub(String sub) {
            this.sub = sub;
            return this;
        }

        public Builder mailContent(String mailContent) {
            this.mailContent = mailContent;
            return this;
        }
    }

    public Response process(){
        SendMailResponse.Builder srBuilder = new SendMailResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
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
            message.setText(mailContent);
            //send message
            Transport.send(message);
            logger.info("message sent successfully");
            srBuilder.result(rBuilder.success(true).build());
        } catch (MessagingException e) {throw new RuntimeException(e);}
        return srBuilder.build();
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

    //    public static void main(String[] args) {
//
//        SendMailRequest req = new SendMailRequest("qwertyraghav@gmail.com", "_Raghav@385848",
//                "raghvendra.mishra@timesinternet.in", "Test Mail",
//                "<html><head></head><body><h1>this is sample mail</h1></body></html>");
//        req.send();
//    }
}