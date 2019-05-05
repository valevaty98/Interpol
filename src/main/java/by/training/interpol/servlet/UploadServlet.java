package by.training.interpol.servlet;

import by.training.interpol.command.Command;
import by.training.interpol.command.CommandFactory;
import by.training.interpol.command.ResponseType;
import by.training.interpol.command.SessionRequestContent;
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

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024)
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "D:\\wanted\\";
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
//            String randFileName = UUID.randomUUID() + "." + fileExtension;
//            String absoluteFilePath = UPLOAD_DIR + randFileName;
            if (!fileExtension.equalsIgnoreCase("png")) {
                request.setAttribute("cause_of_fail", "Illegal file extension!!!");
                request.getRequestDispatcher("/jsp/upload_failed.jsp").forward(request, response);
                return;
            }
            //image.write(absoluteFilePath);
            request.setAttribute("image_is", is);
            request.setAttribute("image_size", image.getSize());

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
