package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.DeletePersonLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;

public class DeletePersonCommand implements Command {

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String[] personIdParams = content.getFromRequestParameters(AttributeParameterName.WANTED_PERSON_ID_PARAM);
        Long personId;
        try {
            personId = (personIdParams != null) ? Long.parseLong(personIdParams[0]) : null;
        } catch (NumberFormatException e) {
            return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
        }
        if (personId != null) {
            DeletePersonLogic.deletePersonById(personId);
        }
        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, wantedPeople);
        content.putInSessionAttributes(AttributeParameterName.NATIONALITIES_ATTR, nationalities);
        return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.REDIRECT);
    }
}
