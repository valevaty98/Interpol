package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.User;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginLogic {
    private static Logger logger = LogManager.getLogger();

    public static Optional<User> checkLogin(String login, String password) {
        UserDaoImpl dao = new UserDaoImpl();
        Optional<User> user;
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            logger.log(Level.WARN, "Illegal login or password!");
            return Optional.empty();
        }
        try {
            user = dao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding user by login", e);
            return Optional.empty();
        }
        if (user.isPresent() && user.get().getPassword().equals(password)){
            return user;
        } else {
            return Optional.empty();
        }
    }
}
