package by.training.interpol.dao;

import by.training.interpol.entity.FullMessageInfo;

import java.util.List;

public interface MessageDao {
    List<FullMessageInfo> receiveAllMessagesBrief() throws DaoException;
}
