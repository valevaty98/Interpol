package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.NationalityDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiveWantedPersonInfoLogic {
    private static Logger logger = LogManager.getLogger();

    public static List<WantedPerson> receiveWantedPeopleBrief() {
        WantedPersonDaoImpl dao = WantedPersonDaoImpl.getInstance();
        try {
            return dao.findWantedPeopleBrief();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding wanted people brief info", e);
            return new ArrayList<>();
        }
    }

    public static List<WantedPerson> receiveWantedPeopleFull() {
        WantedPersonDaoImpl dao = WantedPersonDaoImpl.getInstance();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding wanted people full info", e);
            return new ArrayList<>();
        }
    }

    public static Optional<WantedPerson> receiveFullInfoAboutPerson(long personId) {
        WantedPersonDaoImpl dao = WantedPersonDaoImpl.getInstance();
        Optional<WantedPerson> wantedPerson = Optional.empty();
        try {
            wantedPerson = dao.findById(personId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during receiving full info about person", e);
        }
        return wantedPerson;
    }

    public static List<String> receiveNationalityList() {
        try {
            return NationalityDaoImpl.getInstance().findPeopleNationalityNames();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during receiving nationality list", e);
        }
        return new ArrayList<>();
    }
}
