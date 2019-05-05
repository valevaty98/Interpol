package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.BirthPlaceDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.BirthPlace;
import by.training.interpol.entity.Gender;
import by.training.interpol.entity.WantedPerson;

import java.io.InputStream;
import java.util.Optional;

public class AddWantedPersonLogic {
    public static Optional<WantedPerson> addWantedPersonLogic(String name, String surname, String gender, String characteristics,
                                                              Float height, Float weight, String charges, String birthPlaceName,
                                                              String birthDate, InputStream imageIs, int imageSize) {
        BirthPlaceDaoImpl birthPlaceDao = new BirthPlaceDaoImpl();
        WantedPersonDaoImpl wantedPersonDao = new WantedPersonDaoImpl();

        WantedPerson wantedPerson = new WantedPerson();
        wantedPerson.setName(name);
        wantedPerson.setSurname(surname);
        wantedPerson.setGender(Gender.valueOf(gender.toUpperCase()));
        wantedPerson.setCharacteristics(characteristics);
        wantedPerson.setHeight(height);
        wantedPerson.setWeight(weight);
        wantedPerson.setCharges(charges);
        BirthPlace birthPlace = new BirthPlace(birthPlaceName);
        wantedPerson.setBirthDate(birthDate);
        System.out.println(birthDate);
        wantedPerson.setImageIs(imageIs);
        wantedPerson.setSize(imageSize);
        try {
            Optional<BirthPlace> birthPlaceFromDb = birthPlaceDao.findBirthPlaceId(birthPlace);
            if (!birthPlaceFromDb.isPresent()) {
                birthPlaceDao.insert(birthPlace);
                birthPlaceFromDb = birthPlaceDao.findBirthPlaceId(birthPlace);
            }
            System.out.println(birthPlaceFromDb.get().getName() + " " + birthPlaceFromDb.get().getId());
            wantedPerson.setBirthPlace(birthPlaceFromDb.get());

            wantedPersonDao.insert(wantedPerson);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return Optional.of(wantedPerson);
    }
}
