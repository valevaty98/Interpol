package by.training.interpol.dao.impl;

import by.training.interpol.dao.AssessmentDao;
import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
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
import java.util.List;
import java.util.Optional;

public class AssessmentDaoImpl extends BaseDao<Assessment> implements AssessmentDao {
    private static Logger logger = LogManager.getLogger();

    private static final String SQL_INSERT_ASSESSMENT =
            "INSERT INTO assessments (number_of_messages, assessment) VALUES (?,?)";
    private static final String SQL_SELECT_LAST_ASSESSMENT_ID =
            "SELECT MAX(assessment_id) FROM assessments WHERE number_of_messages=? AND assessment=?";
    @Override
    public Optional<Assessment> findAssessmentByUser(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<Assessment> findAssessmentIdByAssessment(Assessment assessment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
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
    public Optional<Assessment> findAssessmentByUserId(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAssesmentByUser(User user) {
        return false;
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
