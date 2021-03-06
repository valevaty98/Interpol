package by.training.interpol.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {
    private static Logger logger = LogManager.getLogger();
    private String sendToMail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String mailSubject, String sendToMail, String mailText, Properties properties) {
        this.mailSubject = mailSubject;
        this.sendToMail = sendToMail;
        this.mailText = mailText;
        this.properties = properties;
    }

    public void run () {
        Session mailSession = new SessionCreator(properties).createSession();
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);

        try {
            message.setFrom(new InternetAddress(properties.getProperty("mail.user.name")));
            message.setSubject((mailSubject));
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToMail));

            Transport transport = mailSession.getTransport();
            transport.connect(null, properties.getProperty("mail.user.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            logger.log(Level.ERROR, "Illegal address for sending response to user.", e);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Messaging exception during sending response to user.");
        }
    }
}
