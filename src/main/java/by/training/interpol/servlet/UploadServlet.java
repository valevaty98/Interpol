package by.training.interpol.servlet;

import by.training.interpol.util.PageServletPath;
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
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = {"/uploadServlet"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024)
public class UploadServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private final static String IMAGE_PARAMETER = "image";
    private final static String IMAGE_EXTENSION = "png";
    private final static String IMAGE_INPUT_STREAM_ATTRIBUTE = "image_is";
    private final static String IMAGE_SIZE_ATTRIBUTE = "image_size";
    private final static String UPLOAD_ERROR_ATTRIBUTE = "uploadError";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        try {
            Part image = request.getPart(IMAGE_PARAMETER);
            String fileName = image.getSubmittedFileName();
            InputStream is = image.getInputStream();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!fileExtension.equalsIgnoreCase(IMAGE_EXTENSION)) {
                request.setAttribute(UPLOAD_ERROR_ATTRIBUTE, "Illegal image file extension! Upload failed");
            }
            request.setAttribute(IMAGE_INPUT_STREAM_ATTRIBUTE, is);
            request.setAttribute(IMAGE_SIZE_ATTRIBUTE, image.getSize());

            request.getRequestDispatcher(PageServletPath.FRONT_CONTROLLER).forward(request, response);
        } catch (IOException e) {
            logger.log(Level.ERROR, "I/O exception during forwarding to front controller", e);
            response.sendRedirect(PageServletPath.MAIN_PAGE);
        } catch (ServletException e) {
            logger.log(Level.ERROR, "Servlet exception during forwarding to front controller", e);
            response.sendRedirect(PageServletPath.MAIN_PAGE);
        }

    }
}
