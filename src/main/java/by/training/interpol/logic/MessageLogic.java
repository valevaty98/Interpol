package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.AssessmentDaoImpl;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.Message;
import by.training.interpol.entity.MessageStatus;
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

    public static Optional<FullMessageInfo> receiveFullMessage(long messageId) {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        try {
            return dao.receiveFullMessageInfo(messageId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during receiving fill messages info", e);
        }
        return Optional.empty();
    }

    public static boolean updateMessageStatusToChecked(long messageId) {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        try {
            return dao.updateMessageStatus(messageId, CHECKED_MESSAGE_STATUS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DAO exception during updating message status", e);
        }
        return false;
    }
    public static boolean sendMessage(Message message) {
        MessageDaoImpl dao = MessageDaoImpl.getInstance();
        try {
            return dao.insert(message);
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
