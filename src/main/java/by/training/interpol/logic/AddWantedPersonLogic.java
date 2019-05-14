package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.BirthPlaceDaoImpl;
import by.training.interpol.dao.impl.NationalityDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.BirthPlace;
import by.training.interpol.entity.Gender;
import by.training.interpol.entity.Nationality;
import by.training.interpol.entity.WantedPerson;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class AddWantedPersonLogic {
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
        wantedPerson.setImageIs(imageIs);
        wantedPerson.setSize(imageSize);

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
            boolean doesDbContainNationality = false;
            for (String nationality : nationalities) {
                long nationalityId;
                for (Nationality nationalityObject : existedNationalities) {
                    if (nationality.equals(nationalityObject.getName())) {
                        doesDbContainNationality = true;
                    }
                }
                if (doesDbContainNationality) {
                    nationalityId = nationalityDao.findNationalityId(nationality).get().getId();
                } else {
                    nationalityDao.insert(new Nationality(nationality));
                    nationalityId = nationalityDao.findNationalityId(nationality).get().getId();
                }
                doesDbContainNationality = false;
                nationalityDao.insertNationPerson(wantedPersonId, nationalityId);
            }
        } catch (DaoException e) {
            e.printStackTrace();//todo log
        }
    }
}
