package by.training.interpol.servlet;

import by.training.interpol.command.*;
import by.training.interpol.util.PageServletPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
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
                response.sendRedirect(request.getContextPath() + responseType.getPage());
                break;
            default:
                logger.log(Level.ERROR, "Illegal type of send.");
                response.sendRedirect(request.getContextPath() + PageServletPath.INDEX_PAGE);
        }
    }
}
