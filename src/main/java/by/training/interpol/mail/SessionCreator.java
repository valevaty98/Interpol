package by.training.interpol.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class SessionCreator {
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    SessionCreator(Properties configProperties) {
        String smtpHost = configProperties.getProperty("mail.smtp.host");
        String smtpPort = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.password");
        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
        sessionProperties.setProperty("mail.smtp.quitwait", "false");

    }
    Session createSession()
    {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }

}
