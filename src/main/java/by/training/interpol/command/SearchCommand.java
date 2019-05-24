package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.logic.SearchLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;

public class SearchCommand implements Command {
    private static final String NAME_PARAM = "personName";
    private static final String SURNAME_PARAM = "personSurname";
    private static final String GENDER_PARAM = "gender";
    private static final String FROM_AGE_PARAM = "fromAge";
    private static final String TO_AGE_PARAM = "toAge";
    private static final String NATIONALITY_PARAM = "nation";

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
        content.putInRequestAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, filteredWantedPeople);
        content.putInRequestAttributes(NAME_PARAM, name);
        content.putInRequestAttributes(SURNAME_PARAM, surname);
        content.putInRequestAttributes(GENDER_PARAM, gender);
        content.putInRequestAttributes(FROM_AGE_PARAM, fromAge);
        content.putInRequestAttributes(TO_AGE_PARAM, toAge);
        content.putInRequestAttributes(NATIONALITY_PARAM, nation);

        return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
    }
}
