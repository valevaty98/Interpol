package by.training.interpol.logic;

import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.MessageDao;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.entity.FullMessageInfo;

import java.util.ArrayList;
import java.util.List;

public class MessageLogic {
    public static List<FullMessageInfo> receiveAllMessagesBrief() {
        MessageDaoImpl dao = new MessageDaoImpl();
        try {
            return dao.receiveAllMessagesBrief();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
