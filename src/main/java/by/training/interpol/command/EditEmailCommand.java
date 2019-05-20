package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.EditEmailLogic;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditEmailCommand implements Command {
    private static final String EDIT_EMAIL_PAGE_PATH = "/jsp/edit_email.jsp";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        User user = (User)content.getFromSessionAttributes("user");
        String[] emailParams = content.getFromRequestParameters("email");
        String[] passwordParams = content.getFromRequestParameters("password");
        String email = (emailParams != null) ? emailParams[0] : null;
        String password = (passwordParams != null) ? passwordParams[0] : null;

        Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$");
        boolean isValidEmail = (email != null) && emailPattern.matcher(email).matches();
        if (!isValidEmail) {
            content.putInRequestAttributes("edit_email_error", "Invalid email!");
            return builder.buildResponseType(EDIT_EMAIL_PAGE_PATH, SendType.FORWARD);
        }
        boolean isSaved = EditEmailLogic.saveEmail(user, password, email);
        if (isSaved) {
            content.putInSessionAttributes("user", user);
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes("edit_email_error", "Invalid password!");
            return builder.buildResponseType(EDIT_EMAIL_PAGE_PATH, SendType.FORWARD);
         }
    }
}
