package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

import java.util.List;

public class HomeCommand implements Command {

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<String> nationalities;

        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, wantedPeople);
        content.putInSessionAttributes(AttributeParameterName.NATIONALITIES_ATTR, nationalities);
        return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.FORWARD);
    }
}
