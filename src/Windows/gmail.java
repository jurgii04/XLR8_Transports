package Windows;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class gmail {
    public gmail(String eamil,String Mensage,String Subject) throws MessagingException {
        String to = eamil;
        String from = "xlr8trans@gmail.com";
        String password = "bmufsiyfarvqzgba";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw e;
        }
    }

    public static void main(String[] args) {


    }
}

