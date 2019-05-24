package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.LoginLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private final static String ERROR_ATTR = "loginError";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<User> user;
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String login;
        String password;

        login = content.getFromRequestParameters(AttributeParameterName.LOGIN_PARAM)[0];
        password = content.getFromRequestParameters(AttributeParameterName.PASSWORD_PARAM)[0];
        UserAndResultMessageWrapper  wrapper = LoginLogic.checkLogin(login, password);
        user = wrapper.getUser();
        if (user.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
            content.putInSessionAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, wantedPeople);
            content.putInSessionAttributes(AttributeParameterName.NATIONALITIES_ATTR, nationalities);
            content.putInSessionAttributes(AttributeParameterName.USER_ATTR, user.get());
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes(ERROR_ATTR, wrapper.getResultMessage());
            return new DefaultCommand().execute(content);
        }
    }
}
