package com.samadhaan4u.service.request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Created by raghvendra.mishra on 02/02/18.
 */
public class SendMailRequest {

    private static final Logger logger = LoggerFactory.getLogger(SendMailRequest.class);

    private String from;
    private String password;
    private String to;
    private String sub;
    private String mailContent;

    public SendMailRequest(String from, String password, String to, String sub, String mailContent) {
        this.from = from;
        this.password = password;
        this.to = to;
        this.sub = sub;
        this.mailContent = mailContent;
    }

    public void send(){

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
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    public static void main(String[] args) {

        SendMailRequest req = new SendMailRequest("qwertyraghav@gmail.com", "_Raghav@385848",
                "raghvendra.mishra@timesinternet.in", "Test Mail",
                "<html><head></head><body><h1>this is sample mail</h1></body></html>");
        req.send();
    }
}