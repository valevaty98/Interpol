package by.training.interpol.dao;

import by.training.interpol.entity.Assessment;

import java.util.Optional;

/**
 * Assessment DAO
 */
public interface AssessmentDao {
    /**
     * Finds assessment id
     * @param assessment object without id set
     * @return object with id set
     * @throws DaoException if error occurred
     */
    Optional<Assessment> findAssessmentIdByAssessment(Assessment assessment) throws DaoException;

    /**
     * Increments by one number of messages
     * @param assessmentId which number will ne incremented
     * @return true if the new number is set
     * @throws DaoException if error occurred
     */
    boolean incrementAssessmentsNumberOfMessages(long assessmentId) throws DaoException;

    /**
     * Updates assessment message
     * @param id assessment id which assessment message will be updated
     * @param assessmentMessage new assessment message
     * @return ture if new message is set
     * @throws DaoException if error occurred
     */
    boolean updateAssessmentMessage(long id, String assessmentMessage) throws DaoException;
}
