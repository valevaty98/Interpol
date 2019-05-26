package by.training.interpol.dao;

import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.MessageStatus;

import java.util.List;
import java.util.Optional;

/**
 * Message DAO
 */
public interface MessageDao {
    /**
     * Receives brief info about all users messages
     * @return list of brief info objects
     * @throws DaoException if error occurred
     */
    List<BriefMessageInfo> receiveAllMessagesBrief() throws DaoException;

    /**
     * Receives full info about message
     * @param messageId id of appropriate message
     * @return full message info object
     * @throws DaoException if error occurred
     */
    Optional<FullMessageInfo> receiveFullMessageInfo(long messageId) throws DaoException;

    /**
     * Updates message status
     * @param messageId id of appropriate message
     * @param newStatus new status to set
     * @return true if new status is set
     * @throws DaoException if error occurred
     */
    boolean updateMessageStatus(long messageId, MessageStatus newStatus) throws DaoException;

    /**
     * Deleted message by person id
     * @param personId about whom messages will be deleted
     * @throws DaoException if error occurred
     */
    void deleteMessagesAboutPerson(long personId) throws DaoException;
}
