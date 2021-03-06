package by.training.interpol.dao.impl;

import by.training.interpol.dao.AssessmentDao;
import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.entity.Assessment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AssessmentDaoImpl extends BaseDao<Assessment> implements AssessmentDao {
    private static Logger logger = LogManager.getLogger();
    private final static AssessmentDaoImpl INSTANCE = new AssessmentDaoImpl();

    private static final String SQL_INSERT_ASSESSMENT =
            "INSERT INTO assessments (number_of_messages, assessment) VALUES (?,?)";
    private static final String SQL_SELECT_LAST_ASSESSMENT_ID =
            "SELECT MAX(assessment_id) FROM assessments WHERE number_of_messages=? AND assessment=?";
    private static final String SQL_INCREMENT_NUMBER_OF_MESSAGES =
            "UPDATE assessments SET number_of_messages=number_of_messages+1 WHERE assessment_id=?";
    private static final String SQL_UPDATE_MESSAGE_BY_ID =
            "UPDATE assessments SET assessment=? WHERE assessment_id=?";


    private AssessmentDaoImpl(){
    }

    public static AssessmentDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Assessment> findAssessmentIdByAssessment(Assessment assessment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_SELECT_LAST_ASSESSMENT_ID);
            preparedStatement.setInt(1, assessment.getNumberOfMessages());
            preparedStatement.setString(2, assessment.getAssessmentText());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                long assessmentId = rs.getLong(1);
                return Optional.of(
                        new Assessment(assessmentId, assessment.getNumberOfMessages(), assessment.getAssessmentText()));
            } else {
                logger.log(Level.WARN, "No elements in result set!");
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public boolean incrementAssessmentsNumberOfMessages(long assessmentId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_INCREMENT_NUMBER_OF_MESSAGES);
            preparedStatement.setLong(1, assessmentId);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public boolean updateAssessmentMessage(long id, String assessmentMessage) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_UPDATE_MESSAGE_BY_ID);
            preparedStatement.setString(1, assessmentMessage);
            preparedStatement.setLong(2, id);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    protected List<Assessment> parseResultSetForEntities(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Optional<Assessment> parseResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            long assessmentId = rs.getLong(1);
            int numberOfMessages = rs.getInt(2);
            String assessment = rs.getString(3);

            return Optional.of(new Assessment(assessmentId, numberOfMessages, assessment));
        } else {
            logger.log(Level.WARN, "No elements in result set!");
            return Optional.empty();
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Assessment entity) throws SQLException {
        preparedStatement.setInt(1, entity.getNumberOfMessages());
        preparedStatement.setString(2, entity.getAssessmentText());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Assessment entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, Assessment entity) throws SQLException {

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
        return SQL_INSERT_ASSESSMENT;
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
