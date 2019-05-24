package by.training.interpol.filter;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/jsp/send_message.jsp"},
        initParams = {@WebInitParam(name = "INDEX_PAGE", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class SendMessagePageFilter implements Filter {
    private static Logger logger = LogManager.getLogger();
    private String indexPagePath;
    private String mainPagePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPagePath = filterConfig.getInitParameter("INDEX_PAGE");
        mainPagePath = filterConfig.getInitParameter("MAIN_PAGE_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        Object userObject = httpRequest.getSession().getAttribute("user");

        if (userObject == null) {
            System.out.println("user object null, filter redirect index");
            httpRequest.getSession().invalidate();
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
        } else {
            long personId;
            try {
                personId = Long.parseLong(httpRequest.getParameter("person_id"));
                if (!WantedPersonDaoImpl.getInstance().findById(personId).isPresent()) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
                } else {
                    filterChain.doFilter(httpRequest, httpResponse);
                }
            } catch (NumberFormatException e) {
                logger.log(Level.ERROR, "Error during parsing person id.", e);
                httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            } catch (DaoException e) {
                logger.log(Level.ERROR, "Error during finding wanted person in DB.", e);
                httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            }
        }
    }

    @Override
    public void destroy() {
        indexPagePath = null;
    }
}
