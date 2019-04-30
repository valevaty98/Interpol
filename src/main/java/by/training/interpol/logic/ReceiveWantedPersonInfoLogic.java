package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ReceiveWantedPersonInfoLogic {
    private static Logger logger = LogManager.getLogger();

    public static List<WantedPerson> receiveWantedPeople() {
        WantedPersonDaoImpl dao = new WantedPersonDaoImpl();
        try {
            return dao.findWantedPeopleBrief();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding wanted people", e);
            return new ArrayList<>();
        }
    }
}
