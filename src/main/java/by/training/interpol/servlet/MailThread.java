package by.training.interpol.servlet;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private MimeMessage message;
    private String sendToMail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String mailSubject, String mailText, Properties properties) {
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init () {
        Session mailSession = new SessionCreator(properties).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);

        try {
            message.setFrom(new InternetAddress("vlad_box98@mail.ru"));
            message.setSubject((mailSubject));
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("valevaty98@gmail.com"));

            Transport tr = mailSession.getTransport();
            tr.connect(null, "boicy_ne_bolna");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void run () {
        init();
        System.out.printf("run()");
//        try {
//
//            //Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}
