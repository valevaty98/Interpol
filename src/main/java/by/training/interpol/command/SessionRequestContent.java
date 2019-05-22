package by.training.interpol.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private boolean isSessionDestroyed = false;

    public SessionRequestContent(HttpServletRequest request) {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        Enumeration<String> enumeration;

        enumeration = request.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String attributeName = enumeration.nextElement();
            requestAttributes.put(attributeName, request.getAttribute(attributeName));
        }

        enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                paramValues[i] = paramValues[i].replaceAll("<","&lt").replaceAll(">", "&gt");
            }
            requestParameters.put(paramName, paramValues);
        }

        enumeration = request.getSession().getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String attributeName = enumeration.nextElement();
            sessionAttributes.put(attributeName, request.getSession().getAttribute(attributeName));
        }
    }

    public void insertValues(HttpServletRequest request) {
        for (String attributeName : requestAttributes.keySet()) {
            request.setAttribute(attributeName, requestAttributes.get(attributeName));
        }

        if (isSessionDestroyed) {
            request.getSession().invalidate();
        } else {
            for (String attributeName : sessionAttributes.keySet()) {
                request.getSession().setAttribute(attributeName, sessionAttributes.get(attributeName));
            }
        }
    }

    public void invalidateSession() {
        isSessionDestroyed = true;
    }

    public Object getFromRequestAttributes(String key) {
        return requestAttributes.get(key);
    }

    public void putInRequestAttributes(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public String[] getFromRequestParameters(String key) {
        return requestParameters.get(key);
    }

    public Object getFromSessionAttributes(String key) {
        return sessionAttributes.get(key);
    }

    public void putInSessionAttributes(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public boolean hasSessiondDestroyed() {
        return isSessionDestroyed;
    }

}
