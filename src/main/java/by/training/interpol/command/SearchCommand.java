package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.logic.SearchLogic;

import java.util.List;

public class SearchCommand implements Command {
    private static final String NAME_PARAM = "wanted_person_name";
    private static final String SURNAME_PARAM = "wanted_person_surname";
    private static final String GENDER_PARAM = "gender";
    private static final String FROM_AGE_PARAM = "fromAge";
    private static final String TO_AGE_PARAM = "toAge";
    private static final String NATIONALITY_PARAM = "nation";
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<WantedPerson> filteredWantedPeople;

        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeople();
        String name = content.getFromRequestParameters(NAME_PARAM)[0];
        String surname = content.getFromRequestParameters(SURNAME_PARAM)[0];
        String gender = content.getFromRequestParameters(GENDER_PARAM)[0];
        String fromAge = content.getFromRequestParameters(FROM_AGE_PARAM)[0];
        String toAge = content.getFromRequestParameters(TO_AGE_PARAM)[0];
        String nation = content.getFromRequestParameters(NATIONALITY_PARAM)[0];
        filteredWantedPeople = SearchLogic.searchWantedPeople(wantedPeople, name, surname, gender, fromAge, toAge,
                 nation);
        content.putInSessionAttributes("wantedPeople", filteredWantedPeople);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
