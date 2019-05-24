package by.training.interpol.tag;

import by.training.interpol.entity.Language;
import by.training.interpol.entity.User;
import by.training.interpol.util.AttributeParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class CustomFooterTag extends TagSupport {
    private static Logger logger = LogManager.getLogger();
    private static final String LANG_RU = "ru";
    private static final String COUNTRY_RU = "RU";
    private static final String BUNDLE_BASE_NAME = "props.pagescontent";

    @Override
    public int doStartTag() {
        HttpServletRequest httpRequest = (HttpServletRequest) pageContext.getRequest();
        HttpSession httpSession = httpRequest.getSession();
        User user = (User)httpSession.getAttribute(AttributeParameterName.USER_ATTR);
        ResourceBundle bundle;
        if (user.getLang() == Language.RUS) {
            bundle = ResourceBundle
                    .getBundle(BUNDLE_BASE_NAME, new Locale(LANG_RU, COUNTRY_RU));
        } else {
            bundle = ResourceBundle
                    .getBundle(BUNDLE_BASE_NAME, Locale.getDefault());
        }
        String userRole = user.getRole().toString();
        String designLabel = bundle.getString("footer.label.designed-by");
        String footerTagContent =
                "<div id=\"footer\">\n" +
                        "<p class=\"right\">\n" +
                        "User status: " +
                        userRole +
                        ". &copy; 2019 Interpol.\n" +
                        designLabel +
                        ".</p>\n" +
                "</div>";
        JspWriter out = pageContext.getOut();
        try {
            out.write(footerTagContent);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error during creating tag.", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}