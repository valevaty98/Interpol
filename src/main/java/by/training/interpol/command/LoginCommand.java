package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.LoginLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<User> user;
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String login;
        String password;

        login = content.getFromRequestParameters(LOGIN_PARAM)[0];
        password = content.getFromRequestParameters(PASSWORD_PARAM)[0];
        UserAndResultMessageWrapper  wrapper = LoginLogic.checkLogin(login, password);
        user = wrapper.getUser();
        if (user.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
            content.putInSessionAttributes("wantedPeople", wantedPeople);
            content.putInSessionAttributes("nationalities", nationalities);
            content.putInSessionAttributes("user", user.get());
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
        } else {
            content.putInRequestAttributes("loginError", wrapper.getResultMessage());
            return new DefaultCommand().execute(content);
        }
    }
}
