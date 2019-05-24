package by.training.interpol.filter;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/jsp/send_response_to_user.jsp"},
        initParams = {@WebInitParam(name = "INDEX_PAGE", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class SendResponseToUserPageFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private String mainPagePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mainPagePath = filterConfig.getInitParameter("MAIN_PAGE_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        Object userObject = httpRequest.getSession().getAttribute("user");
        User user = (User)userObject;
        if (user.getRole() != Role.ADMIN) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
        } else {
            long messageId;
            String userEmail;
            try {
                messageId = Long.parseLong(httpRequest.getParameter(AttributeParameterName.MESSAGE_ID_PARAM));
                userEmail = httpRequest.getParameter(AttributeParameterName.USER_EMAIL_PARAM);
                System.out.println(userEmail);
                if (!MessageDaoImpl.getInstance().findById(messageId).isPresent() || userEmail == null ||
                        UserDaoImpl.getInstance().findUserIdsByEmail(userEmail).isEmpty()) {
                    logger.log(Level.ERROR, "Illegal params for sending response.");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
                } else {
                    filterChain.doFilter(httpRequest, httpResponse);
                }
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, "Error during parsing message id.", e);
                httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            } catch (DaoException e) {
                logger.log(Level.ERROR, "Error during checking params correctness in DB.", e);
                httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            }
        }
    }

    @Override
    public void destroy() {
        mainPagePath = null;
    }
}
