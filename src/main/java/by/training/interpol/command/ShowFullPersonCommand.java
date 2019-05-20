package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.Optional;

public class ShowFullPersonCommand implements Command {
    private static final String WANTED_FULL_PAGE_PATH = "/jsp/full_wanted_person.jsp";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] personIdParams = content.getFromRequestParameters("person_id");
        Long personId;
        try {
            personId = (personIdParams != null) ? Long.parseLong(personIdParams[0]) : null;
        } catch (NumberFormatException e) {
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
        }
        Optional<WantedPerson> wantedPerson;
        if (personId != null) {
            wantedPerson = ReceiveWantedPersonInfoLogic.receiveFullInfoAboutPerson(personId);
        } else {
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
        }
        if (wantedPerson.isPresent()) {
            content.putInRequestAttributes("wantedPerson", wantedPerson.get());
            return builder.buildResponseType(WANTED_FULL_PAGE_PATH, SendType.FORWARD);
        } else {
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
        }
    }
}
