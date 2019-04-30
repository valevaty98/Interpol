package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;

public class HomeCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;

        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeople();
        content.putInSessionAttributes("wantedPeople", wantedPeople);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
