package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.Optional;

public class ShowFullPersonCommand implements Command {
    private static final String WANTED_FULL_PAGE_PATH = "/jsp/wanted_full.jsp";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        long personId = Long.parseLong(content.getFromRequestParameters("person_id")[0]);
        Optional<WantedPerson> wantedPerson;

        wantedPerson = ReceiveWantedPersonInfoLogic.receiveFullInfoAboutPerson(personId);

        if (wantedPerson.isPresent()) {
            content.putInSessionAttributes("wantedPerson", wantedPerson.get());
            return builder.buildResponseType(WANTED_FULL_PAGE_PATH, SendType.REDIRECT);
        } else {
            content.putInSessionAttributes("error", "No such person!");
            return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
        }
    }
}
