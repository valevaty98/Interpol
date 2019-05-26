package by.training.interpol.logic;

import by.training.interpol.command.UserAndResultMessageWrapper;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.User;
import by.training.interpol.hash.EncodePasswordException;
import by.training.interpol.hash.HashGenerator;
import by.training.interpol.util.ParamsValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginLogic {
    private static Logger logger = LogManager.getLogger();
    private static final String CANT_ENCODE_PASSWORD_MESSAGE = "Can't encode password to verify!";
    private static final String ILLEGAL_LOGIN_MESSAGE = "Illegal login or password!";
    private static final String CANT_FIND_USER_MESSAGE = "Can't find appropriate user";
    private static final String OK_MESSAGE = "User found";

    public static UserAndResultMessageWrapper checkLogin(String login, String password) {
        UserDaoImpl dao = UserDaoImpl.getInstance();
        Optional<User> user;

        if (!ParamsValidator.isValidPassword(password) || !ParamsValidator.isValidLogin(login) ) {
            logger.log(Level.WARN, ILLEGAL_LOGIN_MESSAGE);
            return new UserAndResultMessageWrapper(Optional.empty(), ILLEGAL_LOGIN_MESSAGE);
        }
        try {
            user = dao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding user by login.", e);
            return new UserAndResultMessageWrapper(Optional.empty(), CANT_FIND_USER_MESSAGE);
        }
        try {
            String encodedPassword = HashGenerator.encodePassword(password);
            if (user.isPresent() && user.get().getPassword().equals(encodedPassword)){
                return new UserAndResultMessageWrapper(user, OK_MESSAGE);
            } else {
                return new UserAndResultMessageWrapper(Optional.empty(), CANT_FIND_USER_MESSAGE);
            }
        } catch (EncodePasswordException e) {
            logger.log(Level.ERROR, "Can't encode password to verify user.", e);
            return new UserAndResultMessageWrapper(Optional.empty(), CANT_ENCODE_PASSWORD_MESSAGE);
        }
    }
}
