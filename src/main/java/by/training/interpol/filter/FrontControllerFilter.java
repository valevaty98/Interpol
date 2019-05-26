package by.training.interpol.filter;

import by.training.interpol.command.Command;
import by.training.interpol.command.CommandEnum;
import by.training.interpol.command.DefaultCommand;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;
import org.apache.logging.log4j.Level;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter( urlPatterns = {"/controller"},
        initParams = {@WebInitParam(name = "INDEX_PAGE", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class FrontControllerFilter implements Filter {
    private String indexPagePath;
    private String mainPagePath;
    private final static String LOGIN_PATTERN = "^[\\w.-]{1,19}[0-9a-zA-Z]$";
    private final static String PASSWORD_PATTERN = "^[0-9a-zA-Z]{6,20}$";
    private final static String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$";

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
        CommandEnum command;
        String stringCommand = httpRequest.getParameter(AttributeParameterName.COMMAND_PARAM);

        if (stringCommand == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
            return;
        }
        try {
            command = CommandEnum.valueOf(stringCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
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
                    String login = httpRequest.getParameter(AttributeParameterName.LOGIN_PARAM);
                    String password = httpRequest.getParameter(AttributeParameterName.PASSWORD_PARAM);
                    String email = httpRequest.getParameter(AttributeParameterName.EMAIL_PARAM);
                    Pattern loginPattern = Pattern.compile(EMAIL_PATTERN);
                    Pattern passwordPattern = Pattern.compile(EMAIL_PATTERN);
                    Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
                    if (login == null || !loginPattern.matcher(login).matches() ||
                            password == null || !passwordPattern.matcher(password).matches() ||
                            email == null  || !emailPattern.matcher(email).matches()) {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
                    } else {
                        filterChain.doFilter(httpRequest, httpResponse);
                    }
                    break;
                default:
                    httpRequest.getSession().invalidate();
                    httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
            }
        } else {
            switch (command) {
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
