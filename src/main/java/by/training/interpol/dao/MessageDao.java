package by.training.interpol.dao;

import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.MessageStatus;

import java.util.List;
import java.util.Optional;

public interface MessageDao {
    List<BriefMessageInfo> receiveAllMessagesBrief() throws DaoException;

    Optional<FullMessageInfo> receiveFullMessageInfo(long messageId) throws DaoException;

    boolean updateMessageStatus(long messageId, MessageStatus newStatus) throws DaoException;
}
