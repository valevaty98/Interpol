package by.training.interpol.logic;

import by.training.interpol.command.UserAndResultMessageWrapper;
import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.Assessment;
import by.training.interpol.entity.Language;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import by.training.interpol.hash.EncodePasswordException;
import by.training.interpol.hash.HashGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Spliterator;

public class SignUpLogic {
    private static final String ILLEGAL_PARAMS_MESSAGE = "Illegal parameters for singing up.";
    private static final String LOGIN_ALREADY_TAKEN_MESSAGE = "Login already taken.";
    private static final String CANT_CREATE_USER_MESSAGE = "Can't create user.";
    private static final String OK_MESSAGE = "User created";
    private static final int INITIAL_NUMBER_OF_MESSAGES = 0;
    private static final String INITIAL_ASSESSMENT_TEXT = "There was no help yet, but still ahead.";
    private static final String CANT_ENCODE_PASSWORD_MESSAGE = "Can't encode password.";
    private static final Language DEFAULT_LANGUAGE_AFTER_REGISTRATION = Language.ENG;
    private static Logger logger = LogManager.getLogger();

    public static UserAndResultMessageWrapper signUpUser(String login, String email, String password) {
        AssessmentDaoImpl assessmentDao = AssessmentDaoImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Assessment assessment = new Assessment(INITIAL_NUMBER_OF_MESSAGES, INITIAL_ASSESSMENT_TEXT);
        Optional<Assessment> optionalAssessment;
        User user;

        if (login == null || login.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty()
        ) {
            logger.log(Level.WARN, ILLEGAL_PARAMS_MESSAGE);
            return new UserAndResultMessageWrapper(Optional.empty(), ILLEGAL_PARAMS_MESSAGE);
        }

        try {
            if (userDao.findUserByLogin(login).isPresent()) {
                logger.log(Level.WARN, LOGIN_ALREADY_TAKEN_MESSAGE);
                return new UserAndResultMessageWrapper(Optional.empty(), LOGIN_ALREADY_TAKEN_MESSAGE);
            }
            if (assessmentDao.insert(assessment)) {
                optionalAssessment = assessmentDao.findAssessmentIdByAssessment(assessment);
                String encodedPassword = HashGenerator.encodePassword(password);
                user = new User(login, encodedPassword, email, Role.GUEST, optionalAssessment.get(), DEFAULT_LANGUAGE_AFTER_REGISTRATION);
                userDao.insert(user);
                return new UserAndResultMessageWrapper(userDao.findUserByLogin(user.getLogin()), OK_MESSAGE);
            } else {
                logger.log(Level.ERROR, "Error inserting default assessment");
                return new UserAndResultMessageWrapper(Optional.empty(), CANT_CREATE_USER_MESSAGE);
            }
        } catch (DaoException ex) {
            logger.log(Level.ERROR, "DAO exception during inserting user to database", ex);
            return new UserAndResultMessageWrapper(Optional.empty(), CANT_CREATE_USER_MESSAGE);
        } catch (EncodePasswordException e) {
            logger.log(Level.ERROR, "Can't encode password during signing up!", e);
            return new UserAndResultMessageWrapper(Optional.empty(), CANT_ENCODE_PASSWORD_MESSAGE);
        }
    }
}
