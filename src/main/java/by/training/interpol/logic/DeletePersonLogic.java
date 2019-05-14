package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.dao.impl.NationalityDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.Message;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DeletePersonLogic {
    private static Logger logger = LogManager.getLogger();

    public static boolean deletePersonById(long personId) {
        WantedPersonDaoImpl wantedPersonDao = WantedPersonDaoImpl.getInstance();
        NationalityDaoImpl nationalityDao = NationalityDaoImpl.getInstance();
        MessageDaoImpl messageDao = MessageDaoImpl.getInstance();

        try {
            if (nationalityDao.deleteFromNationPerson(personId)) {
                messageDao.deleteMessagesAboutPerson(personId);
                return wantedPersonDao.deleteById(personId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during deleting person!", e);
            return false;
        }
        return false;
    }
}
