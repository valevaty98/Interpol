package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.AddWantedPersonLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class AddWantedPersonCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private static final String ERROR_ATTR = "uploadError";
    private static final String NAME_PARAM = "person_name";
    private static final String SURNAME_PARAM = "person_surname";
    private static final String GENDER_PARAM = "gender";
    private static final String CHARACTERISTICS_PARAM = "characteristics";
    private static final String HEIGHT_PARAM = "height";
    private static final String WEIGHT_PARAM = "weight";
    private static final String CHARGES_PARAM = "charges";
    private static final String BIRTH_PLACE_PARAM = "birth_place";
    private static final String BIRTH_DATE_PARAM = "birth_date";
    private static final String NATIONALITIES_PARAM = "nationalities";
    private static final String IMAGE_INPUT_STREAM_PARAM = "image_is";
    private static final String IMAGE_SIZE_PARAM = "image_size";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        if (content.getFromRequestAttributes(ERROR_ATTR) != null ) {
            return new HomeCommand().execute(content);
        }
        ResponseTypeCreator builder = new ResponseTypeCreator();
        List<WantedPerson> wantedPeople;
        List<String> nationalities;
        String name = content.getFromRequestParameters(NAME_PARAM)[0];
        String surname = content.getFromRequestParameters(SURNAME_PARAM)[0];
        String gender = content.getFromRequestParameters(GENDER_PARAM)[0];
        String characteristics = content.getFromRequestParameters(CHARACTERISTICS_PARAM)[0];
        Float height;
        try {
            height = Float.parseFloat(content.getFromRequestParameters(HEIGHT_PARAM)[0]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "Exception during parsing height from parameters", e);
            height = null;
        }
        Float weight;
        try {
             weight = Float.parseFloat(content.getFromRequestParameters(WEIGHT_PARAM)[0]);
        } catch (NumberFormatException e) {
            logger.log(Level.WARN, "Exception during parsing weight from parameters", e);
            weight = null;
        }
        String charges = content.getFromRequestParameters(CHARGES_PARAM)[0];
        String birthPlace = content.getFromRequestParameters(BIRTH_PLACE_PARAM)[0];
        String birthDate = content.getFromRequestParameters(BIRTH_DATE_PARAM)[0];
        String nationalitiesString = content.getFromRequestParameters(NATIONALITIES_PARAM)[0];
        InputStream imageInputStream = (InputStream)content.getFromRequestAttributes(IMAGE_INPUT_STREAM_PARAM);
        long imageSize = (long)content.getFromRequestAttributes(IMAGE_SIZE_PARAM);

        AddWantedPersonLogic.addWantedPersonLogic(
            name, surname, gender, characteristics, height, weight, charges, birthPlace,
            birthDate, imageInputStream, (int)imageSize, nationalitiesString);

        wantedPeople = ReceiveWantedPersonInfoLogic.receiveWantedPeopleBrief();
        nationalities = ReceiveWantedPersonInfoLogic.receiveNationalityList();
        content.putInSessionAttributes(AttributeParameterName.WANTED_PEOPLE_ATTR, wantedPeople);
        content.putInSessionAttributes(AttributeParameterName.NATIONALITIES_ATTR, nationalities);
        return builder.buildResponseType(PageServletPath.MAIN_PAGE, SendType.REDIRECT);
    }
}
