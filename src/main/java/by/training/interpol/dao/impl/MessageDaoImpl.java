package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.MessageDao;
import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl extends BaseDao<Message> implements MessageDao {
    private static final String SQL_INSERT_MESSAGE =
            "INSERT INTO messages (subject, message, wanted_person_id, user_id, date) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_ALL_MESSAGES_BRIEF =
            "SELECT m.message_id, m.subject, m.message, m.date, w.person_id, w.name, w.surname, " +
                    "u.user_id, u.login FROM messages m " +
                    "INNER JOIN users u ON m.user_id = u.user_id " +
                    "INNER JOIN wanted_people w ON m.wanted_person_id = w.person_id";
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
        preparedStatement.setString(1, entity.getSubject());
        preparedStatement.setString(2, entity.getMessage());
        preparedStatement.setLong(3, entity.getWantedPersonId());
        preparedStatement.setLong(4, entity.getUserId());
        preparedStatement.setString(5, entity.getDate());
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

    @Override
    public List<FullMessageInfo> receiveAllMessagesBrief() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_MESSAGES_BRIEF);
            List<FullMessageInfo> messageInfoList = new ArrayList<>();
            while (rs.next()) {
                long messageId = rs.getLong("message_id");
                String subject = rs.getString("subject");
                String message = rs.getString("message");
                long wantedPersonId = rs.getLong("person_id");
                long userId = rs.getLong("user_id");
                String date = rs.getDate("date").toString();
                String userLogin = rs.getString("login");
                String wantedPersonName = rs.getString("name");
                String wantedPersonSurname = rs.getString("surname");

                messageInfoList.add(
                        new FullMessageInfo(
                                new Message(messageId, subject, message,date, wantedPersonId, userId),
                                userLogin, wantedPersonName, wantedPersonSurname)
                        );
            }
            return messageInfoList;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(statement, connection);
        }
    }
}
