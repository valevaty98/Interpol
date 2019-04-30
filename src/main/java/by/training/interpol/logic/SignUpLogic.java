package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.Assessment;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Spliterator;

public class SignUpLogic {
    private static final int INITIAL_NUMBER_OF_MESSAGES = 0;
    private static final String INITIAL_ASSESSMENT_TEXT = "There was no help yet, but still ahead.";
    private static Logger logger = LogManager.getLogger();

    public static Optional<User> signUpUser(String login, String email, String password) {
        AssessmentDaoImpl assessmentDao = new AssessmentDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        Assessment assessment = new Assessment(INITIAL_NUMBER_OF_MESSAGES, INITIAL_ASSESSMENT_TEXT);
        Optional<Assessment> optionalAssessment = Optional.empty();
        User user;
        Optional<User> optionalUser = Optional.empty();

        if (login == null || login.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty()
        ) {
            logger.log(Level.WARN, "Illegal parameters for singing up!");
            return Optional.empty();
        }

        try {
            if (userDao.findUserByLogin(login).isPresent()) {
                logger.log(Level.WARN, "Login already taken!");
                return Optional.empty();
            }
            assessmentDao.insert(assessment);
            optionalAssessment = assessmentDao.findAssessmentIdByAssessment(assessment); //todo think
            user = new User(login, password, email, Role.GUEST, optionalAssessment.get());
            userDao.insert(user);
            optionalUser = userDao.findUserByLogin(user.getLogin());
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "DAO exception during inserting user to database", ex);
            return Optional.empty();
        }

        return optionalUser;
    }
}
