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
        long personId = Long.parseLong(content.getFromRequestParameters("person_id")[0]);
        DeletePersonLogic.deletePersonById(personId);
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes("wantedPeople", wantedPeople);
        content.putInSessionAttributes("nationalities", nationalities);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
