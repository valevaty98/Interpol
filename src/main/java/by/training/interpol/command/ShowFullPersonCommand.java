package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.Optional;

public class ShowFullPersonCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] personIdParams = content.getFromRequestParameters(AttributeParameterName.WANTED_PERSON_ID_PARAM);
        Long personId;
        try {
            personId = (personIdParams != null) ? Long.parseLong(personIdParams[0]) : null;
        } catch (NumberFormatException e) {
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
        }
        Optional<WantedPerson> wantedPerson;
        if (personId != null) {
            wantedPerson = ReceiveWantedPersonInfoLogic.receiveFullInfoAboutPerson(personId);
        } else {
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
        }
        if (wantedPerson.isPresent()) {
            content.putInRequestAttributes(AttributeParameterName.WANTED_PERSON_ATTR, wantedPerson.get());
            return builder.buildResponseType(PageServletPath.FULL_WANTED_PERSON_PAGE, SendType.FORWARD);
        } else {
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
        }
    }
}
