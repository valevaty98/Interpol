package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditEmailLogic {
    private static Logger logger = LogManager.getLogger();

    public static boolean saveEmail(User user, String password, String email) {
        UserDaoImpl dao = UserDaoImpl.getInstance();
        boolean isSaved = false;
        try {
            if (user.getPassword().equals(password) && email != null) {
                isSaved = dao.updateUserEmail(user, email);
                user.setEmail(email);
            }
        } catch (DaoException e) {
            e.printStackTrace(); //todo log
        }
        return isSaved;
    }
}
