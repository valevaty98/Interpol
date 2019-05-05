package by.training.interpol.dao;

import by.training.interpol.entity.Nationality;

import java.util.Optional;

public interface NationalityDao {
    boolean insertNationPerson(long personId, long nationId) throws DaoException;

    Optional<Nationality> findNationalityId(String nationality) throws DaoException;
}
