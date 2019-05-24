package by.training.interpol.servlet;

import by.training.interpol.mail.MailThread;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

@WebServlet("/mailServlet")
public class MailServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private final static String MAIL_INIT_PARAMETER = "mail";
    private final static String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext servletContext = getServletContext();
        String fileName = servletContext.getInitParameter(MAIL_INIT_PARAMETER);
        properties.load(servletContext.getResourceAsStream(fileName));
        String subject = request.getParameter(AttributeParameterName.SUBJECT_PARAM);
        String email = request.getParameter(AttributeParameterName.EMAIL_PARAM);
        String message = request.getParameter(AttributeParameterName.MESSAGE_PARAM);
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        if (subject == null || email == null || !emailPattern.matcher(email).matches() || message == null) {
            logger.log(Level.ERROR, "Illegal params for sending response to user.");
            response.sendRedirect(PageServletPath.MAIN_PAGE);
        } else {
            MailThread mailOperator = new MailThread(subject, email, message, properties);
            mailOperator.start();
            request.getRequestDispatcher(PageServletPath.FRONT_CONTROLLER).forward(request, response);
        }
    }
}
