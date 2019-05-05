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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddWantedPersonLogic {
    public static Optional<WantedPerson> addWantedPersonLogic(String name, String surname, String gender, String characteristics,
                                                              Float height, Float weight, String charges, String birthPlaceName,
                                                              String birthDate, InputStream imageIs, int imageSize,
                                                              String nationalitiesString) {
        BirthPlaceDaoImpl birthPlaceDao = new BirthPlaceDaoImpl();
        WantedPersonDaoImpl wantedPersonDao = new WantedPersonDaoImpl();
        NationalityDaoImpl nationalityDao = new NationalityDaoImpl();

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

            List<String> nationalities = Arrays.asList(nationalitiesString.split(","));
            wantedPerson.setNationality(nationalities);

            List<Nationality> existedNationalities = nationalityDao.findAll();
            long wantedPersonId = wantedPersonDao.findWantedPersonId(wantedPerson);
            System.out.println("pers_id" + wantedPersonId);
            boolean wasNation = false;
            for (String nation : nationalities) {
                System.out.println(nation);
                long nationalityId;
                for (Nationality nationObject : existedNationalities) {
                    if (nation.equals(nationObject.getName())) {
                        wasNation = true;
                    }
                }
                if (wasNation) {
                    nationalityId = nationalityDao.findNationalityId(nation).get().getId();
                    System.out.println(nationalityId+" was");
                } else {
                    nationalityDao.insert(new Nationality(nation));
                    nationalityId = nationalityDao.findNationalityId(nation).get().getId();
                    System.out.println(nationalityId+"ne was");
                }
                wasNation = false;
                nationalityDao.insertNationPerson(wantedPersonId, nationalityId);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return Optional.of(wantedPerson);
    }
}
