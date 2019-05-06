package by.training.interpol.logic;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.impl.NationalityDaoImpl;
import by.training.interpol.dao.impl.WantedPersonDaoImpl;
import by.training.interpol.entity.WantedPerson;

public class DeletePersonLogic {
    public static boolean deletePersonById(long personId) {
        BaseDao<WantedPerson> dao = new WantedPersonDaoImpl();
        NationalityDaoImpl nationalityDao = new NationalityDaoImpl();

        try {
            if (nationalityDao.deleteFromNationPerson(personId)) {
                return dao.deleteById(personId);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }
}
