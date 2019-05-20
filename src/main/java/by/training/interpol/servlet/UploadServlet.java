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
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
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
        } catch (IOException e) {
            response.sendRedirect(MAIN_PAGE_PATH);
        } catch (ServletException e) {
            response.sendRedirect(MAIN_PAGE_PATH);
        }

    }
}
