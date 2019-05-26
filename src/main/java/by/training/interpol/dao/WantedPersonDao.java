package by.training.interpol.dao;

import by.training.interpol.entity.WantedPerson;

import java.util.List;

/**
 * Wanted people DAO
 */
public interface WantedPersonDao {
    /**
     * Finds brief info about wanted person.
     * @return List of brief info about wanted people
     * @throws DaoException if error occurred
     */
    List<WantedPerson> findWantedPeopleBrief() throws DaoException;

    /**
     * Finds wanted person's id
     * @param person WantedPerson entity without id set
     * @return wanted person id
     * @throws DaoException if error occurred
     */
    long findWantedPersonId(WantedPerson person) throws DaoException;
}
