package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.DeletePersonLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;

public class DeletePersonCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String[] personIdParams = content.getFromRequestParameters("person_id");
        Long personId;
        try {
            personId = (personIdParams != null) ? Long.parseLong(personIdParams[0]) : null;
        } catch (NumberFormatException e) {
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
        }
        if (personId != null) {
            DeletePersonLogic.deletePersonById(personId);
        }
        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes("wantedPeople", wantedPeople);
        content.putInSessionAttributes("nationalities", nationalities);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
