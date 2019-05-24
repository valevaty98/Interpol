package by.training.interpol.filter;

import by.training.interpol.util.AttributeParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/jsp/full_wanted_person.jsp"},
        initParams = {@WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class FullWantedPersonPageFilter implements Filter {
    private String mainPagePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mainPagePath = filterConfig.getInitParameter("MAIN_PAGE_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (httpRequest.getAttribute(AttributeParameterName.WANTED_PERSON_ATTR) == null) {
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
