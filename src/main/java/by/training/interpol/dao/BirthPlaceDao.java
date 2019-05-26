package by.training.interpol.dao;

import by.training.interpol.entity.BirthPlace;

import java.util.Optional;

/**
 * Birth place DAO
 */
public interface BirthPlaceDao {
    /**
     * Finds birth place id
     * @param birthPlace object without id set
     * @return object with id set
     * @throws DaoException if error occurred
     */
    Optional<BirthPlace> findBirthPlaceId(BirthPlace birthPlace) throws DaoException;
}
