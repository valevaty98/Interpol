package by.training.interpol.servlet;

import by.training.interpol.command.Command;
import by.training.interpol.command.CommandFactory;
import by.training.interpol.command.ResponseType;
import by.training.interpol.command.SessionRequestContent;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext servletContext = getServletContext();
        String fileName = servletContext.getInitParameter("mail");
        properties.load(servletContext.getResourceAsStream(fileName));

        MailThread mailOperator = new MailThread(request.getParameter("subject"), request.getParameter("email"), request.getParameter("message"), properties);
        mailOperator.start();

        SessionRequestContent content = new SessionRequestContent(request);
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.defineCommand(content);

        ResponseType responseType = command.execute(content);

        content.insertValues(request);

        switch (responseType.getSendType()) {
            case FORWARD:
                request.getRequestDispatcher(responseType.getPage()).forward(request, response);
                break;
            case REDIRECT:
                System.out.println(request.getContextPath() + " - context path");
                response.sendRedirect(request.getContextPath() + responseType.getPage());
                break;
            default:
                System.out.println("illegal redirect type");
                //todo - log
        }
    }
}
