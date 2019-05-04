package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.WantedPersonDao;
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
        WantedPersonDaoImpl dao = new WantedPersonDaoImpl();
        try {
            return dao.findWantedPeopleBrief();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding wanted people", e);
            return new ArrayList<>();
        }
    }

    public static List<WantedPerson> receiveWantedPeopleFull() {
        BaseDao<WantedPerson> dao = new WantedPersonDaoImpl();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding wanted people", e);
            return new ArrayList<>();
        }
    }

    public static Optional<WantedPerson> receiveFullInfoAboutPerson(long personId) {
        BaseDao<WantedPerson> dao = new WantedPersonDaoImpl();
        Optional<WantedPerson> wantedPerson = Optional.empty();
        try {
            wantedPerson = dao.findById(personId);
        } catch (DaoException e) {
            e.printStackTrace();  //todo log
        }
        return wantedPerson;
    }

    public static List<String> receiveNationalityList() {
        try {
            return new WantedPersonDaoImpl().findNationalities();
        } catch (DaoException e) {
            e.printStackTrace();  //todo log
        }
        return new ArrayList<>();
    }
}
