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

@WebFilter( urlPatterns = {"/jsp/set_assessment.jsp"},
        initParams = {@WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class SetAssessmentPageFilter implements Filter {
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
        Object userObject = httpRequest.getSession().getAttribute(AttributeParameterName.USER_ATTR);
        User user = (User)userObject;
        if (user.getRole() != Role.ADMIN) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
        } else {
            String userLogin;
            try {
                userLogin = httpRequest.getParameter(AttributeParameterName.USER_LOGIN_PARAM);
                if (userLogin == null || !UserDaoImpl.getInstance().findUserByLogin(userLogin).isPresent()) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
                } else {
                    filterChain.doFilter(httpRequest, httpResponse);
                }
            } catch (DaoException e) {
                logger.log(Level.ERROR, "Error during finding user in DB.", e);
                httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            }
        }
    }

    @Override
    public void destroy() {
        mainPagePath = null;
    }
}
