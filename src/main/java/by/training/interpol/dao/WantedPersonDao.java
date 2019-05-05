package by.training.interpol.dao;

import by.training.interpol.entity.WantedPerson;

import java.util.List;

public interface WantedPersonDao {
    List<WantedPerson> findWantedPeopleBrief() throws DaoException;

    long findWantedPersonId(WantedPerson person) throws DaoException;

    List<String> findNationalities() throws DaoException;
}
