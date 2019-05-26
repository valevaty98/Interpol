package by.training.interpol.filter;

import by.training.interpol.command.CommandEnum;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = {"/controller"},
        initParams = {@WebInitParam(name = "INDEX_PAGE", value = "/index.jsp"),
                @WebInitParam(name = "MAIN_PAGE_PATH", value = "/jsp/main_page.jsp")})
public class FrontControllerFilter implements Filter {
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
                    String loginForLogin = httpRequest.getParameter(AttributeParameterName.LOGIN_PARAM);
                    String passwordForLogin = httpRequest.getParameter(AttributeParameterName.PASSWORD_PARAM);
                    if (loginForLogin != null && passwordForLogin != null) {
                        filterChain.doFilter(httpRequest, httpResponse);
                    } else {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
                    }
                    break;
                case SIGN_UP:
                    String loginForSignUp = httpRequest.getParameter(AttributeParameterName.LOGIN_PARAM);
                    String passwordForSignUp = httpRequest.getParameter(AttributeParameterName.PASSWORD_PARAM);
                    String emailForSignUp = httpRequest.getParameter(AttributeParameterName.EMAIL_PARAM);
                    if (loginForSignUp != null && passwordForSignUp != null && emailForSignUp != null) {
                        filterChain.doFilter(httpRequest, httpResponse);
                    } else {
                        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPagePath);
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
