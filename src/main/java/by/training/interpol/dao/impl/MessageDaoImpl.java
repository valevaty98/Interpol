package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.MessageDao;
import by.training.interpol.entity.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl extends BaseDao<Message> implements MessageDao {
    private static final String SQL_INSERT_MESSAGE =
            "INSERT INTO messages (message, wanted_person_id, user_id, date) VALUES (?,?,?,?)";
    @Override
    protected List<Message> parseResultSetForEntities(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Optional<Message> parseResultSet(ResultSet rs) throws SQLException {
        return Optional.empty();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Message entity) throws SQLException {
        preparedStatement.setString(1, entity.getMessage());
        preparedStatement.setLong(2, entity.getWantedPersonId());
        preparedStatement.setLong(3, entity.getUserId());
        preparedStatement.setString(4, entity.getDate());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Message entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, Message entity) throws SQLException {

    }

    @Override
    protected String selectAllSQLQuery() {
        return null;
    }

    @Override
    protected String selectByIdSQLQuery() {
        return null;
    }

    @Override
    protected String deleteByIdSQLQuery() {
        return null;
    }

    @Override
    protected String insertSQLQuery() {
        return SQL_INSERT_MESSAGE;
    }

    @Override
    protected String updateSQLQuery() {
        return null;
    }

    @Override
    protected String deleteSQLQuery() {
        return null;
    }
}
