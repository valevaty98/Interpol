package by.training.interpol.tag;

import by.training.interpol.entity.Language;
import by.training.interpol.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class CustomFooterTag extends TagSupport {
    @Override
    public int doStartTag() {

        HttpServletRequest httpRequest = (HttpServletRequest) pageContext.getRequest();
        HttpSession httpSession = httpRequest.getSession();
        User user = (User)httpSession.getAttribute("user");
        ResourceBundle bundle;
        if (user.getLang() == Language.RUS) {
            bundle = ResourceBundle
                    .getBundle("props.pagescontent", new Locale("ru", "Ru"));
        } else {
            bundle = ResourceBundle
                    .getBundle("props.pagescontent", Locale.getDefault());
        }
        String designLabel = bundle.getString("footer.label.designed-by");
        String footerTagContent =
                "<div id=\"footer\">\n" +
                        "<p class=\"right\">\n" +
                        "&copy; 2019 Interpol.\n" +
                        designLabel +
                        ".</p>\n" +
                "</div>";
        JspWriter out = pageContext.getOut();
        try {
            out.write(footerTagContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}