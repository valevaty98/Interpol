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

@WebFilter( urlPatterns = {"/uploadServlet", "/mailServlet"},
        initParams = {@WebInitParam(name = "INDEX_PAGE", value = "/index.jsp"),
                      @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class SpecialServletsFilter implements Filter {
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
        Object userObject = httpRequest.getSession().getAttribute(AttributeParameterName.USER_ATTR);

        if (userObject == null) {
            httpRequest.getSession().invalidate();
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
        } else if (((User)userObject).getRole() == Role.ADMIN) {
            filterChain.doFilter(httpRequest, httpResponse);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
        }
    }

    @Override
    public void destroy() {
        indexPagePath = null;
        mainPagePath = null;
    }
}
