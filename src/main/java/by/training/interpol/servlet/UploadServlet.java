package by.training.interpol.servlet;

import by.training.interpol.command.Command;
import by.training.interpol.command.CommandFactory;
import by.training.interpol.command.ResponseType;
import by.training.interpol.command.SessionRequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@WebServlet(urlPatterns = {"/uploadServlet"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024)
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "D:\\wanted\\";
    private static final String INDEX_PAGE_PATH = "/index.jsp";
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        File dirForSaveFile = new File(UPLOAD_DIR);
        if (!dirForSaveFile.exists()) {
            dirForSaveFile.mkdirs();
        }
        try {
            Part image = request.getPart("image");
            String fileName = image.getSubmittedFileName();
            InputStream is = image.getInputStream();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!fileExtension.equalsIgnoreCase("png")) {
                request.setAttribute("uploadError", "Illegal image file extension! Upload failed");
            }
            request.setAttribute("image_is", is);
            request.setAttribute("image_size", image.getSize());

            request.getRequestDispatcher("/controller").forward(request, response);
//            SessionRequestContent content = new SessionRequestContent(request);
//            CommandFactory commandFactory = new CommandFactory();
//            Command command = commandFactory.defineCommand(content);
//            ResponseType responseType = command.execute(content);
//            content.insertValues(request);
//            switch (responseType.getSendType()) {
//                case FORWARD:
//                    request.getRequestDispatcher(responseType.getPage()).forward(request, response);
//                    break;
//                case REDIRECT:
//                    response.sendRedirect(request.getContextPath() + responseType.getPage());
//                    break;
//                default:
//                    logger.log(Level.ERROR, "Illegal type of send.");
//                    request.getRequestDispatcher(INDEX_PAGE_PATH).forward(request, response);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
