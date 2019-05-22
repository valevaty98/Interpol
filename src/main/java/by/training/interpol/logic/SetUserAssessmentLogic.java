package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;

public class SetUserAssessmentLogic {
    public static boolean setAssessmentToUser(String login, String assessmentMessage) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AssessmentDaoImpl assessmentDao = AssessmentDaoImpl.getInstance();
        System.out.println(login + " " + assessmentMessage);
        try {
            long assessmentId = userDao.findAssessmentIdByUserLogin(login);
            System.out.println(assessmentId);
            return assessmentDao.updateAssessmentMessage(assessmentId, assessmentMessage);
        } catch (DaoException e) {
            //todo log
            return false;
        }
    }
}
