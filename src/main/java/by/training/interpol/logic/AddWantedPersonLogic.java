package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.BirthPlaceDaoImpl;
import by.training.interpol.dao.impl.NationalityDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.BirthPlace;
import by.training.interpol.entity.Gender;
import by.training.interpol.entity.Nationality;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AddWantedPersonLogic {
    private static Logger logger = LogManager.getLogger();

    public static void addWantedPersonLogic(String name, String surname, String gender, String characteristics,
                                                              Float height, Float weight, String charges, String birthPlaceName,
                                                              String birthDate, InputStream imageIs, int imageSize,
                                                              String nationalitiesString) {
        BirthPlaceDaoImpl birthPlaceDao = BirthPlaceDaoImpl.getInstance();
        WantedPersonDaoImpl wantedPersonDao = WantedPersonDaoImpl.getInstance();
        NationalityDaoImpl nationalityDao = NationalityDaoImpl.getInstance();

        WantedPerson wantedPerson = new WantedPerson();
        wantedPerson.setName(name);
        wantedPerson.setSurname(surname);
        wantedPerson.setGender(Gender.valueOf(gender.toUpperCase()));
        wantedPerson.setCharacteristics(characteristics);
        wantedPerson.setHeight(height);
        wantedPerson.setWeight(weight);
        wantedPerson.setBirthDate(birthDate);
        wantedPerson.setCharges(charges);
        wantedPerson.setImageInputStream(imageIs);
        wantedPerson.setImageSize(imageSize);

        try {
            BirthPlace birthPlace = new BirthPlace(birthPlaceName);
            Optional<BirthPlace> birthPlaceFromDb = birthPlaceDao.findBirthPlaceId(birthPlace);
            if (!birthPlaceFromDb.isPresent()) {
                birthPlaceDao.insert(birthPlace);
                birthPlaceFromDb = birthPlaceDao.findBirthPlaceId(birthPlace);
            }
            wantedPerson.setBirthPlace(birthPlaceFromDb.get());
            wantedPersonDao.insert(wantedPerson);

            String[] nationalities = nationalitiesString.split(",");
            List<Nationality> existedNationalities = nationalityDao.findAll();

            long wantedPersonId = wantedPersonDao.findWantedPersonId(wantedPerson);
            boolean doesDbContainsNationality = false;
            for (String nationality : nationalities) {
                long nationalityId;
                for (Nationality nationalityObject : existedNationalities) {
                    if (nationality.equals(nationalityObject.getName())) {
                        doesDbContainsNationality = true;
                    }
                }
                if (doesDbContainsNationality) {
                    nationalityId = nationalityDao.findNationalityId(nationality).get().getId();
                } else {
                    nationalityDao.insert(new Nationality(nationality));
                    nationalityId = nationalityDao.findNationalityId(nationality).get().getId();
                }
                doesDbContainsNationality = false;
                nationalityDao.insertNationPerson(wantedPersonId, nationalityId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during adding wanted person!", e);
        }
    }
}
