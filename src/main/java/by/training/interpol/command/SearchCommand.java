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
        String[] nameParams = content.getFromRequestParameters(NAME_PARAM);
        String[] surnameParams = content.getFromRequestParameters(SURNAME_PARAM);
        String[] genderParams = content.getFromRequestParameters(GENDER_PARAM);
        String[] fromAgeParams = content.getFromRequestParameters(FROM_AGE_PARAM);
        String[] toAgeParams = content.getFromRequestParameters(TO_AGE_PARAM);
        String[] nationParams = content.getFromRequestParameters(NATIONALITY_PARAM);
        String name = (nameParams != null) ? nameParams[0] : null;
        String surname = (surnameParams != null) ? surnameParams[0] : null;
        String gender = (genderParams != null) ? genderParams[0] : null;
        String fromAge = (fromAgeParams != null) ? fromAgeParams[0] : null;
        String toAge = (toAgeParams != null) ? toAgeParams[0] : null;
        String nation = (nationParams != null) ? nationParams[0] : null;
        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleFull();
        filteredWantedPeople = SearchLogic.searchWantedPeople(wantedPeople, name, surname, gender, fromAge, toAge,
                 nation);
        content.putInRequestAttributes("wantedPeople", filteredWantedPeople);
        content.putInRequestAttributes("personName", name);
        content.putInRequestAttributes("personSurname", surname);
        content.putInRequestAttributes("personGender", gender);
        content.putInRequestAttributes("fromAge", fromAge);
        content.putInRequestAttributes("toAge", toAge);
        content.putInRequestAttributes("nation", nation);

        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.FORWARD);
    }
}
