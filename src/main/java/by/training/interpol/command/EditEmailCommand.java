package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.logic.EditEmailLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;
import by.training.interpol.util.ParamsValidator;

import java.util.regex.Pattern;

public class EditEmailCommand implements Command {
    private final static String ERROR_ATTR = "editEmailError";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        User user = (User)content.getFromSessionAttributes(AttributeParameterName.USER_ATTR);
        String[] emailParams = content.getFromRequestParameters(AttributeParameterName.EMAIL_PARAM);
        String[] passwordParams = content.getFromRequestParameters(AttributeParameterName.PASSWORD_PARAM);
        String email = (emailParams != null) ? emailParams[0] : null;
        String password = (passwordParams != null) ? passwordParams[0] : null;
        if (!ParamsValidator.isValidEmail(email)) {
            content.putInRequestAttributes(ERROR_ATTR, "Invalid email!");
            return builder.buildResponseType(PageServletPath.EDIT_EMAIL_PAGE, SendType.FORWARD);
        }
        boolean isSaved = EditEmailLogic.saveEmail(user, password, email);
        if (isSaved) {
            content.putInSessionAttributes(AttributeParameterName.USER_ATTR, user);
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes(ERROR_ATTR, "Invalid password!");
            return builder.buildResponseType(PageServletPath.EDIT_EMAIL_PAGE, SendType.FORWARD);
         }
    }
}
