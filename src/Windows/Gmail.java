package Windows;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Gmail {
    public Gmail(String eamil, String Mensage, String Subject) throws MessagingException {
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
    public Gmail(String toEmail , String ticpath) throws MessagingException, IOException {
        String username = "xlr8trans@gmail.com";
        String password = "bmufsiyfarvqzgba";



        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with account credentials
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set sender's address
            message.setFrom(new InternetAddress(username));

            // Set recipient's address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set email subject
            message.setSubject("Disfrute de su viaje. Gracias por confiar en XLR8 Transports");

            // Create a multipart message
            MimeMultipart multipart = new MimeMultipart();

            // Create the message body part (text)
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Adjunto a este correo encontrará su boleto de viaje, el cual contiene un código de barras único. \n\nEste código de barras es su identificador personal para el viaje. Asegúrese de tenerlo a mano para cualquier consulta o al abordar el transporte correspondiente. \n\nGracias por viajar con nosotros. \n\nAtentamente,\n" + "El equipo de XLR8 Transports.");

            // Create the message body part (photo attachment)
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(ticpath); // Replace with the actual file path

            // Add body parts to the multipart message
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Set the content of the message to the multipart object
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            throw e;
        }

    }


    public static void main(String[] args) {
        //gmail g=new gmail();

    }
}

