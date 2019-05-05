package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.AddWantedPersonLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AddWantedPersonCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        String name = content.getFromRequestParameters("person_name")[0];
        String surname = content.getFromRequestParameters("person_surname")[0];
        String gender = content.getFromRequestParameters("gender")[0];
        String characteristics = content.getFromRequestParameters("characteristics")[0];
        Float height;
        try {
            height = Float.parseFloat(content.getFromRequestParameters("height")[0]);
        } catch (NumberFormatException e) {
            height = null;
        }
        Float weight;
        try {
             weight = Float.parseFloat(content.getFromRequestParameters("weight")[0]);
        } catch (NumberFormatException e) {
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
        return new HomeCommand().execute(content);
    }
}
