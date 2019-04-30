package by.training.interpol.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/mailServlet")
public class MailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.printf("mail servlet");
        Properties properties = new Properties();
        ServletContext servletContext = getServletContext();
        String fileName = servletContext.getInitParameter("mail");
        properties.load(servletContext.getResourceAsStream(fileName));

        MailThread mailOperator = new MailThread(req.getParameter("subject"), req.getParameter("message"), properties);
        mailOperator.start();

        req.getRequestDispatcher("/jsp/send.jsp").forward(req, resp);
    }
}
