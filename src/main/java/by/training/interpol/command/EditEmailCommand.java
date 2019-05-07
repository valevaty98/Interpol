package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.EditEmailLogic;

import java.util.List;

public class EditEmailCommand implements Command {
    private static final String EDIT_EMAIL_PAGE_PATH = "/jsp/edit_email.jsp";

    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        User user;
        String email;
        String password;

        user = (User)content.getFromSessionAttributes("user");
        email = content.getFromRequestParameters("email")[0];
        password = content.getFromRequestParameters("password")[0];

        System.out.println(user);
        System.out.println(email);
        System.out.println(password);
        System.out.println(user.getPassword().equals(password));
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
