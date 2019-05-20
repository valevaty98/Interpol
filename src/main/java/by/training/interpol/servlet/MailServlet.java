package by.training.interpol.servlet;

import by.training.interpol.command.*;
import by.training.interpol.mail.MailThread;
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
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext servletContext = getServletContext();
        String fileName = servletContext.getInitParameter("mail");
        properties.load(servletContext.getResourceAsStream(fileName));
        String subject = request.getParameter("subject");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$");
        if (subject == null || (email == null || emailPattern.matcher(email).matches()) || message == null) {
            response.sendRedirect(MAIN_PAGE_PATH);
            return;
        }
        MailThread mailOperator = new MailThread(request.getParameter("subject"), request.getParameter("email"), request.getParameter("message"), properties);
        mailOperator.start();
        request.getRequestDispatcher("/controller").forward(request, response);
    }
}
