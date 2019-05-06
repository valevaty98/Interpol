package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.FullMessageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageLogic {
    public static List<BriefMessageInfo> receiveAllMessagesBrief() {
        MessageDaoImpl dao = new MessageDaoImpl();
        try {
            return dao.receiveAllMessagesBrief();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Optional<FullMessageInfo> receiveFullMessage(long messageId) {
        MessageDaoImpl dao = new MessageDaoImpl();
        try {
            return dao.receiveFullMessageInfo(messageId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
