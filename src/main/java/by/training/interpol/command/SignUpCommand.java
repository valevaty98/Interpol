package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.logic.SignUpLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;
import java.util.Optional;

public class SignUpCommand implements Command {
    public static final String ERROR_ATTR = "signUpError";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        Optional<User> user;
        List<WantedPerson> wantedPeople;
        String login;
        String email;
        String password;

        login = content.getFromRequestParameters(AttributeParameterName.LOGIN_PARAM)[0];
        email = content.getFromRequestParameters(AttributeParameterName.EMAIL_PARAM)[0];
        password = content.getFromRequestParameters(AttributeParameterName.PASSWORD_PARAM)[0];
        UserAndResultMessageWrapper  wrapper = SignUpLogic.signUpUser(login, email, password);
        user = wrapper.getUser();
        if (user.isPresent()) {
            wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
            content.putInSessionAttributes(AttributeParameterName.USER_ATTR, user.get());
            content.putInSessionAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, wantedPeople);
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.REDIRECT);
        } else {
            content.invalidateSession();
            content.putInRequestAttributes(ERROR_ATTR, wrapper.getResultMessage());
            return builder.buildResponseType(PageServletPath.INDEX_PAGE, SendType.FORWARD);
        }
    }
}
