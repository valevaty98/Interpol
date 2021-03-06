package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.MessageDao;
import by.training.interpol.entity.BriefMessageInfo;
import by.training.interpol.entity.FullMessageInfo;
import by.training.interpol.entity.Message;
import by.training.interpol.entity.MessageStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl extends BaseDao<Message> implements MessageDao {
    private final static MessageDaoImpl INSTANCE = new MessageDaoImpl();

    private static final String SQL_INSERT_MESSAGE =
            "INSERT INTO messages (subject, message, wanted_person_id, user_id, date, status) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_MESSAGE_STATUS =
            "UPDATE messages SET status=? WHERE message_id=?";
    private static final String SQL_SELECT_ALL_MESSAGES_BRIEF =
            "SELECT m.message_id, m.subject, m.message, m.date, m.status, w.person_id, w.name, w.surname, " +
                    "u.user_id, u.login FROM messages m " +
                    "INNER JOIN users u ON m.user_id = u.user_id " +
                    "INNER JOIN wanted_people w ON m.wanted_person_id = w.person_id " +
                    "ORDER BY m.date DESC";
    private static final String SQL_SELECT_FULL_MESSAGE_INFO =
            "SELECT m.subject, m.message, m.date, m.status, w.person_id, w.name, w.surname, " +
                    "w.image, u.user_id, u.login, u.email FROM messages m " +
                    "INNER JOIN users u ON m.user_id = u.user_id " +
                    "INNER JOIN wanted_people w ON m.wanted_person_id = w.person_id " +
                    "WHERE m.message_id=?";
    private static final String SQL_DELETE_MESSAGES_BY_PERSON_ID =
            "DELETE FROM messages WHERE wanted_person_id=?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT message FROM messages WHERE message_id=?";
    private MessageDaoImpl(){
    }

    public static MessageDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<FullMessageInfo> receiveFullMessageInfo(long messageId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_SELECT_FULL_MESSAGE_INFO);
            preparedStatement.setLong(1, messageId);
            ResultSet rs = preparedStatement.executeQuery();
            Optional<FullMessageInfo> messageInfo = Optional.empty();
            if (rs.next()) {
                String subject = rs.getString("subject");
                String message = rs.getString("message");
                long wantedPersonId = rs.getLong("person_id");
                long userId = rs.getLong("user_id");
                MessageStatus status = MessageStatus.valueOf(rs.getString("status").toUpperCase());
                String date = rs.getDate("date").toString();
                String userLogin = rs.getString("login");
                String userEmail = rs.getString("email");
                String wantedPersonName = rs.getString("name");
                String wantedPersonSurname = rs.getString("surname");
                Blob imageBlob = rs.getBlob("image");
                String wantedPersonImage = Base64.getEncoder().encodeToString(imageBlob.getBytes(1,(int)imageBlob.length()));

                messageInfo = Optional.of(new FullMessageInfo(
                        new Message(messageId, subject, message, date, wantedPersonId, userId, status),
                        userLogin, userEmail, wantedPersonName, wantedPersonSurname, wantedPersonImage)
                );
            }
            return messageInfo;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public List<BriefMessageInfo> receiveAllMessagesBrief() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_MESSAGES_BRIEF);
            List<BriefMessageInfo> messageInfoList = new ArrayList<>();
            while (rs.next()) {
                long messageId = rs.getLong("message_id");
                String subject = rs.getString("subject");
                String message = rs.getString("message");
                long wantedPersonId = rs.getLong("person_id");
                long userId = rs.getLong("user_id");
                MessageStatus status = MessageStatus.valueOf(rs.getString("status").toUpperCase());
                String date = rs.getDate("date").toString();
                String userLogin = rs.getString("login");
                String wantedPersonName = rs.getString("name");
                String wantedPersonSurname = rs.getString("surname");

                messageInfoList.add(
                        new BriefMessageInfo(
                                new Message(messageId, subject, message,date, wantedPersonId, userId, status),
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

    @Override
    public boolean updateMessageStatus(long messageId, MessageStatus newStatus) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_UPDATE_MESSAGE_STATUS);
            preparedStatement.setString(1, newStatus.toString());
            preparedStatement.setLong(2, messageId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public void deleteMessagesAboutPerson(long personId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_MESSAGES_BY_PERSON_ID);
            preparedStatement.setLong(1, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    protected List<Message> parseResultSetForEntities(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Optional<Message> parseResultSet(ResultSet rs) throws SQLException {
       if (rs.next()) {
           String message= rs.getString("message");
           return Optional.of(new Message(message));
       }
       return Optional.empty();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Message entity) throws SQLException {
        preparedStatement.setString(1, entity.getSubject());
        preparedStatement.setString(2, entity.getMessage());
        preparedStatement.setLong(3, entity.getWantedPersonId());
        preparedStatement.setLong(4, entity.getUserId());
        preparedStatement.setString(5, entity.getDate());
        preparedStatement.setString(6, entity.getStatus().toString());
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
        return SQL_SELECT_BY_ID;
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
