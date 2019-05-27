package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.WantedPersonDao;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.*;
import by.training.interpol.util.ParamsValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageLogic {
    private static Logger logger = LogManager.getLogger();
    private static final MessageStatus CHECKED_MESSAGE_STATUS = MessageStatus.CHECKED;

    public static List<BriefMessageInfo> receiveAllMessagesBrief() {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        try {
            return dao.receiveAllMessagesBrief();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during receiving all messages brief info", e);
        }
        return new ArrayList<>();
    }

    public static Optional<FullMessageInfo> receiveFullMessage(Long messageId) {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        if (messageId == null) {
            logger.log(Level.ERROR, "There is no message id param");
            return Optional.empty();
        }
        try {
            return dao.receiveFullMessageInfo(messageId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during receiving fill messages info", e);
        }
        return Optional.empty();
    }

    public static boolean updateMessageStatusToChecked(Long messageId) {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        if (messageId == null) {
            logger.log(Level.ERROR, "There is no message id param");
            return false;
        }
        try {
            return dao.updateMessageStatus(messageId, CHECKED_MESSAGE_STATUS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during updating message status", e);
        }
        return false;
    }
    public static boolean sendMessage(Message message) {
        MessageDaoImpl messageDao = MessageDaoImpl.getInstance();
        WantedPersonDaoImpl wantedPersonDao = WantedPersonDaoImpl.getInstance();
        try {
            if (!ParamsValidator.isValidSubject(message.getSubject()) ||
                    !ParamsValidator.isValidMessage(message.getMessage()) ||
                    !wantedPersonDao.findById(message.getWantedPersonId()).isPresent()) {
                logger.log(Level.WARN, "Illegal params for sending message");
                return false;
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during finding appropriate person", e);
            return false;
        }
        try {
            return messageDao.insert(message);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during sending message", e);
        }
        return false;
    }

    public static boolean incrementUsersNumberOfMessages(long userId) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AssessmentDaoImpl assessmentDao = AssessmentDaoImpl.getInstance();
        long assessmentId;
        try {
            assessmentId = userDao.findAssessmentIdByUserId(userId);
            return assessmentDao.incrementAssessmentsNumberOfMessages(assessmentId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during incrementing user's number of messages", e);
        }
        return false;
    }
}
