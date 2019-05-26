package by.training.interpol.dao;

import by.training.interpol.entity.Language;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * User DAO
 */
public interface UserDao {
    /**
     * Finds user object by login
     * @param login login for finding
     * @return User optional object
     * @throws DaoException if error occurred
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Updates user email
     * @param user whose email will be updated
     * @param email new email
     * @return true if email is set in DB
     * @throws DaoException if error occurred
     */
    boolean updateUserEmail(User user, String email) throws DaoException;

    /**
     * Updates user language by user id
     * @param userId whose language will be updated
     * @param lang new language
     * @return true if language is set in BD
     * @throws DaoException if error occurred
     */
    boolean updateUserLanguageById(long userId, Language lang) throws DaoException;

    /**
     * Finds assessment id by user id
     * @param userId whose assessment is being searched
     * @return appropriate assessment id
     * @throws DaoException if error occurred
     */
    long findAssessmentIdByUserId(long userId) throws DaoException;

    /**
     * Finds assessment id by user login
     * @param userLogin whose assessment is being searched
     * @return appropriate assessment id
     * @throws DaoException if error occurred
     */
    long findAssessmentIdByUserLogin(String userLogin) throws DaoException;

    /**
     * Finds user ids by email
     * @param email to find
     * @return list of user ids
     * @throws DaoException if error occurred
     */
    List<Long> findUserIdsByEmail(String email) throws DaoException;

}
