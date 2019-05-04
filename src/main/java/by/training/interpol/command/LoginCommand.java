package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.LoginLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<User> user;
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String login;
        String password;
        try {
            login = content.getFromRequestParameters(LOGIN_PARAM)[0];
            password = content.getFromRequestParameters(PASSWORD_PARAM)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo - log
            content.putInSessionAttributes("error", "Illegal params.");
            return builder.buildResponseType(ERROR_PAGE_PATH, SendType.REDIRECT);
        }
        user = LoginLogic.checkLogin(login, password);
        if (user.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
            content.putInSessionAttributes("wantedPeople", wantedPeople);
            content.putInSessionAttributes("nationalities", nationalities);
            content.putInSessionAttributes("user", user.get());
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
        } else {
            content.putInSessionAttributes("error", "Illegal params.");
            return builder.buildResponseType(ERROR_PAGE_PATH, SendType.REDIRECT);
        }
    }
}
