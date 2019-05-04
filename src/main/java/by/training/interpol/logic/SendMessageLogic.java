package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.MessageDaoImpl;
import by.training.interpol.entity.Message;

public class SendMessageLogic {
    public static boolean sendMessage(Message message) {
        BaseDao<Message> dao = new MessageDaoImpl();
        try {
            return dao.insert(message);
        } catch (DaoException e) {
            e.printStackTrace(); //todo log
        }
        return false;
    }
}
