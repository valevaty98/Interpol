package by.training.interpol.dao;

import by.training.interpol.entity.BirthPlace;

import java.util.Optional;

public interface BirthPlaceDao {
    Optional<BirthPlace> findBirthPlaceId(BirthPlace birthPlace) throws DaoException;
}
