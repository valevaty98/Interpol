package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.AddWantedPersonLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AddWantedPersonCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        if (content.getFromRequestAttributes("uploadError") != null ) {
            return new HomeCommand().execute(content);
        }
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String name = content.getFromRequestParameters("person_name")[0];
        String surname = content.getFromRequestParameters("person_surname")[0];
        String gender = content.getFromRequestParameters("gender")[0];
        String characteristics = content.getFromRequestParameters("characteristics")[0];
        Float height;
        try {
            height = Float.parseFloat(content.getFromRequestParameters("height")[0]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "Exception during parsing height from parameters", e);
            height = null;
        }
        Float weight;
        try {
             weight = Float.parseFloat(content.getFromRequestParameters("weight")[0]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "Exception during parsing weight from parameters", e);
            weight = null;
        }
        String charges = content.getFromRequestParameters("charges")[0];
        String birthPlace = content.getFromRequestParameters("birth_place")[0];
        String birthDate = content.getFromRequestParameters("birth_date")[0];
        String nationalitiesString = content.getFromRequestParameters("nationalities")[0];
        InputStream imageInputStream = (InputStream)content.getFromRequestAttributes("image_is");
        long imageSize = (long)content.getFromRequestAttributes("image_size");

        AddWantedPersonLogic.addWantedPersonLogic(
            name, surname, gender, characteristics, height, weight, charges, birthPlace,
            birthDate, imageInputStream, (int)imageSize, nationalitiesString);

        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes("wantedPeople", wantedPeople);
        content.putInSessionAttributes("nationalities", nationalities);
        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
