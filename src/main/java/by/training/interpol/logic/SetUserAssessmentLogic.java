package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetUserAssessmentLogic {
    private static Logger logger = LogManager.getLogger();

    public static boolean setAssessmentToUser(String login, String assessmentMessage) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AssessmentDaoImpl assessmentDao = AssessmentDaoImpl.getInstance();
        try {
            long assessmentId = userDao.findAssessmentIdByUserLogin(login);
            return assessmentDao.updateAssessmentMessage(assessmentId, assessmentMessage);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during updating assessment language", e);
            return false;
        }
    }
}
