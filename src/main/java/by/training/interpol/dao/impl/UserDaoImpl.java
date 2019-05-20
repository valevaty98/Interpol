package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.entity.Assessment;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static Logger logger = LogManager.getLogger();
    private static UserDaoImpl instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT u.user_id, u.login, u.password, u.email, u.role, a.assessment_id, a.number_of_messages, a.assessment " +
                    "FROM users u INNER JOIN assessments a " +
                    "ON u.assessment_id = a.assessment_id";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT user_id, login, password, email, role, assessment_id FROM users WHERE user_id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT u.user_id, u.login, u.password, u.email, u.role, a.assessment_id, a.number_of_messages, a.assessment " +
                    "FROM users u INNER JOIN assessments a " +
                    "ON u.assessment_id = a.assessment_id WHERE u.login=?";
    private static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT user_id, login, password, email, role, assessment_id FROM users WHERE email=?";
    private static final String SQL_SELECT_USER_BY_ROLE =
            "SELECT u.user_id, u.login, u.password, u.email, u.role, a.assessment_id, a.number_of_messages, a.assessment " +
                    "FROM users u INNER JOIN assessments a " +
                    "ON u.assessment_id = a.assessment_id WHERE u.role=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id=?";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login, password, email, role, assessment_id) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, email=?, role=? " +
            "WHERE user_id=?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id=? AND login=? AND password=? " +
            "AND email=? AND role=?";
    private static final String SQL_UPDATE_USER_EMAIL = "UPDATE users SET email=? WHERE user_id=?";
    private static final String SQL_SELECT_ASSESSMENT_BY_ID =
            "SELECT assessment_id FROM users WHERE user_id=?";
    private static final String SQL_SELECT_USERS_BY_EMAIL = "SELECT user_id FROM users WHERE email=?";

    private UserDaoImpl(){
    }

    public static UserDaoImpl getInstance() {
        if (!isInstanceCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new UserDaoImpl();
                    isInstanceCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        return findUser(login, SQL_SELECT_USER_BY_LOGIN);
    }

    @Override
    public boolean updateUserEmail(User user, String email) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, user.getId());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL Query.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    private Optional<User> findUser(String field, String sqlSelectUser) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(sqlSelectUser);
            preparedStatement.setString(1, field);
            ResultSet rs = preparedStatement.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    protected List<User> parseResultSetForEntities(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            long userId = rs.getLong(1);
            String login = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            Role role = Role.valueOf(rs.getString(5).toUpperCase());
            long assessmentId = rs.getLong(6);
            int numberOfMessages = rs.getInt(7);
            String assessmentText = rs.getString(8);

            User user = new User(userId, login, password, email, role,
                    new Assessment(assessmentId, numberOfMessages, assessmentText));
            users.add(user);
        }
        return users;
    }

    @Override
    protected Optional<User> parseResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            long userId = rs.getLong(1);
            String login = rs.getString(2);
            String password = rs.getString(3);
            String email = rs.getString(4);
            Role role = Role.valueOf(rs.getString(5).toUpperCase());
            long assessmentId = rs.getLong(6);
            int numberOfMessages = rs.getInt(7);
            String assessment = rs.getString(8);

            Assessment userAssessment = new Assessment(assessmentId, numberOfMessages, assessment);
            return Optional.of(new User(userId, login, password, email, role, userAssessment));
        } else {
            logger.log(Level.WARN, "No elements in result set!");
            return Optional.empty();
        }
    }

    @Override
    public long findAssessmentIdByUserId(long userId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ASSESSMENT_BY_ID);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getLong("assessment_id");
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public List<Long> findUserIdsByEmail(String searchEmail) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_EMAIL);
            preparedStatement.setString(1, searchEmail);
            ResultSet rs = preparedStatement.executeQuery();
            List<Long> idList = new ArrayList<>();
            while (rs.next()) {
                Long userId = rs.getLong(1);
                idList.add(userId);
            }
            return idList;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole().toString());
        preparedStatement.setLong(5, user.getAssessment().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole().toString());
        preparedStatement.setLong(5, user.getId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getRole().toString());
    }

    @Override
    protected String selectAllSQLQuery() {
        return SQL_SELECT_ALL_USERS;
    }

    @Override
    protected String selectByIdSQLQuery() {
        return SQL_SELECT_USER_BY_ID;
    }

    @Override
    protected String deleteByIdSQLQuery() {
        return SQL_DELETE_USER_BY_ID;
    }

    @Override
    protected String deleteSQLQuery() {
        return SQL_DELETE_USER;
    }

    @Override
    protected String insertSQLQuery() {
        return SQL_INSERT_USER;
    }

    @Override
    protected String updateSQLQuery() {
        return SQL_UPDATE_USER;
    }
}
