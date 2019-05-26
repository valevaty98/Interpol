package by.training.interpol.dao;

import by.training.interpol.entity.Nationality;

import java.util.List;
import java.util.Optional;

/**
 * Nationality DAO
 */
public interface NationalityDao {
    /**
     * Inserts data in nation person DAO
     * @param personId person id
     * @param nationId nation id
     * @return if row is inserted
     * @throws DaoException if error occurred
     */
    boolean insertNationPerson(long personId, long nationId) throws DaoException;

    /**
     * Deletes data from nation person table
     * @param personId whose data will be delete
     * @return true if data is deleted
     * @throws DaoException if error occurred
     */
    boolean deleteFromNationPerson(long personId) throws DaoException;

    /**
     * Finds nationality id by nationality name
     * @param nationality name
     * @return nationality object if nationality with such name exists
     * @throws DaoException if error occurred
     */
    Optional<Nationality> findNationalityId(String nationality) throws DaoException;

    /**
     * Finds all nationalities in the table
     * @return list of nationalities names
     * @throws DaoException if error occurred
     */
    List<String> findPeopleNationalityNames() throws DaoException;
}
