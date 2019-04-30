package by.training.interpol.dao;

import by.training.interpol.entity.Assessment;
import by.training.interpol.entity.User;

import java.util.Optional;

public interface AssessmentDao {
    Optional<Assessment> findAssessmentByUser(User user);

    Optional<Assessment> findAssessmentIdByAssessment(Assessment assessment) throws DaoException;

    Optional<Assessment> findAssessmentByUserId(long id);

    boolean deleteAssesmentByUser(User user);
}
