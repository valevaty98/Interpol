package by.training.interpol.filter;

import by.training.interpol.command.Command;
import by.training.interpol.command.CommandEnum;
import by.training.interpol.command.DefaultCommand;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.Level;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/controller"},
        initParams = {@WebInitParam(name = "INDEX_PAGE_PATH", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class FrontControllerFilter implements Filter {
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
        CommandEnum command;
        String stringCommand = httpRequest.getParameter("command");

        if (stringCommand == null) {
            //logger.log(Level.ERROR, "No command parameter");
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
            return;
        }
        try {
            command = CommandEnum.valueOf(stringCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            //logger.log(Level.ERROR, "No such command in the command enum!", e);
            //content.putInRequestAttributes("wrongCommand", stringCommand);
            command = CommandEnum.DEFAULT;
        }

        if (userObject == null) {
            switch (command) {
                case LOGIN:
                    if (httpRequest.getParameter("login") == null ||
                            httpRequest.getParameter("password") == null) {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
                    } else {
                        filterChain.doFilter(httpRequest, httpResponse);
                    }
                    break;
                case SIGN_UP:
                    if (httpRequest.getParameter("login") == null ||
                            httpRequest.getParameter("password") == null ||
                            httpRequest.getParameter("email") == null  ) {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
                    } else {
                        filterChain.doFilter(httpRequest, httpResponse);
                    }
                    break;
                default:
                    System.out.println("user object null, filter redirect index");
                    httpRequest.getSession().invalidate();
                    httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
            }
        } else {
            switch (command) {
                case LOGIN:
                case SIGN_UP:
                    httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
                    break;
                case HOME:
                case LOGOUT:
                case DEFAULT:
                case SEARCH:
                case EDIT_EMAIL:
                case SHOW_FULL_PERSON:
                case SEND_MESSAGE:
                case CHANGE_LANG:
                    filterChain.doFilter(httpRequest, httpResponse);
                    break;
                case DELETE_PERSON:
                case SHOW_FULL_MESSAGE:
                case SHOW_ALL_MESSAGES:
                case SET_ASSESSMENT:
                    if (((User) userObject).getRole() == Role.ADMIN) {
                        filterChain.doFilter(httpRequest, httpResponse);
                    } else {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
                    }
                    break;
                default:
                    System.out.println("unsupported direct command, filter redirect main");
                    httpResponse.sendRedirect(httpRequest.getContextPath() + mainPagePath);
            }
        }
    }

    @Override
    public void destroy() {
        indexPagePath = null;
        mainPagePath = null;
    }
}
