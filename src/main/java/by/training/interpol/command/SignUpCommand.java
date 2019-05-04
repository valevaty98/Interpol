package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.logic.SignUpLogic;

import java.util.List;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    private static final String INDEX_PAGE = "/index.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<User> user;
        List<WantedPerson> wantedPeople;
        String login;
        String email;
        String password;

        try {
            login = content.getFromRequestParameters(LOGIN_PARAM)[0];
            email = content.getFromRequestParameters(EMAIL_PARAM)[0];
            password = content.getFromRequestParameters(PASSWORD_PARAM)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo - log
            content.putInSessionAttributes("error", "Illegal params.");
            return builder.buildResponseType(ERROR_PAGE_PATH, SendType.REDIRECT);
        }
        user = SignUpLogic.signUpUser(login, email, password);
        if (user.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            content.putInSessionAttributes("user", user.get());
            content.putInSessionAttributes("wantedPeople", wantedPeople);
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
        } else {
            content.invalidateSession();
            content.putInRequestAttributes("error", "Login already Taken.");
            return builder.buildResponseType(INDEX_PAGE, SendType.FORWARD);
        }
    }
}
