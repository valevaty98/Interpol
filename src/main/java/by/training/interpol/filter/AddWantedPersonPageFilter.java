package by.training.interpol.filter;

import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/jsp/add_wanted_person.jsp"},
        initParams = {@WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class AddWantedPersonPageFilter implements Filter {
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
            filterChain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        mainPagePath = null;
    }
}
