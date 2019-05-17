package by.training.interpol.filter;

import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/jsp/full_message.jsp"},
        initParams = {@WebInitParam(name = "INDEX_PAGE_PATH", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class FullMessagePageFilter implements Filter {
    private String indexPagePath;
    private String mainPagePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPagePath = filterConfig.getInitParameter("INDEX_PAGE_PATH");
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
            return;
        }
        User user = (User)userObject;
        if (user.getRole() != Role.ADMIN) {
            System.out.println("user not admin, filter redirect index");
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
        } else if (httpRequest.getAttribute("message") == null) {
            System.out.println("no mess attrib, filter redirect index");
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
        } else {
            filterChain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        indexPagePath = null;
    }
}
