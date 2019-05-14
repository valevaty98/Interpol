package by.training.interpol.dao;

import by.training.interpol.entity.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityDao {
    boolean insertNationPerson(long personId, long nationId) throws DaoException;

    boolean deleteFromNationPerson(long personId) throws DaoException;

    Optional<Nationality> findNationalityId(String nationality) throws DaoException;

    List<String> findPeopleNationalityNames() throws DaoException;
}
