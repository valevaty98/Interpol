package by.training.interpol.dao;

import by.training.interpol.entity.Assessment;
import by.training.interpol.entity.User;

import java.util.Optional;

public interface AssessmentDao {
    Optional<Assessment> findAssessmentIdByAssessment(Assessment assessment) throws DaoException;

    boolean incrementAssessmentsNumberOfMessages(long assessmentId) throws DaoException;

    boolean updateAssessmentMessage(long id, String assessmentMessage) throws DaoException;
}
