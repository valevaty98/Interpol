package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.Language;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetNewUserLanguageLogic {
    private static Logger logger = LogManager.getLogger();

    public static boolean setLanguageToUser(User user, Language lang) {
        UserDaoImpl dao = UserDaoImpl.getInstance();
        boolean isSet = false;
        try {
            isSet = dao.updateUserLanguageById(user.getId(), lang);
            if (isSet) {
                user.setLang(lang);
            }
        } catch (DaoException e) {
            e.printStackTrace(); //todo log
        }
        return isSet;
    }
}
