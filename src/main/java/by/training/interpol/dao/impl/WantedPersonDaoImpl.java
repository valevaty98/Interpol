package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.WantedPersonDao;
import by.training.interpol.entity.Gender;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WantedPersonDaoImpl extends BaseDao<WantedPerson> implements WantedPersonDao {
    private static Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_WANTED_PEOPLE_BRIEF =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.birth_place, w.age, w.image " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.bearth_place_id";
    private static final String SQL_SELECT_PERSON_BY_ID =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.birth_place, w.age, w.image " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.bearth_place_id " +
                    "WHERE w.person_id=?";


    @Override
    public List<WantedPerson> findWantedPeopleBrief() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_WANTED_PEOPLE_BRIEF);
            return parseResultSetForEntitiesBrief(rs);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(statement, connection);
        }
    }

    protected List<WantedPerson> parseResultSetForEntitiesBrief(ResultSet rs) throws SQLException {
        List<WantedPerson> wantedPeople = new ArrayList<>();
        while (rs.next()) {
            long wantedPersonId = rs.getLong(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Gender gender = Gender.valueOf(rs.getString(4).toUpperCase());
            String characteristics = rs.getString("characteristics");
            float height = rs.getFloat("height");
            float weight = rs.getFloat("weight");
            String charges = rs.getString("charges");
            String birthPlace = rs.getString("birth_place");
            int age = rs.getInt("age");
            Blob image = rs.getBlob("image");

            WantedPerson person = new WantedPerson(wantedPersonId, name, surname, gender, characteristics,
                    height, weight, charges, birthPlace, age, image);
            wantedPeople.add(person);
        }
        return wantedPeople;
    }

    @Override
    protected List<WantedPerson> parseResultSetForEntities(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Optional<WantedPerson> parseResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            long wantedPersonId = rs.getLong(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Gender gender = Gender.valueOf(rs.getString(4).toUpperCase());
            String characteristics = rs.getString("characteristics");
            float height = rs.getFloat("height");
            float weight = rs.getFloat("weight");
            String charges = rs.getString("charges");
            String birthPlace = rs.getString("birth_place");
            int age = rs.getInt("age");
            Blob image = rs.getBlob("image");

            return Optional.of(
                    new WantedPerson(wantedPersonId, name, surname, gender, characteristics,
                            height, weight, charges, birthPlace, age, image));
        } else {
            logger.log(Level.WARN, "No elements in result set!");
            return Optional.empty();
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, WantedPerson entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, WantedPerson entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, WantedPerson entity) throws SQLException {

    }

    @Override
    protected String selectAllSQLQuery() {
        return null;
    }

    @Override
    protected String selectByIdSQLQuery() {
        return SQL_SELECT_PERSON_BY_ID;
    }

    @Override
    protected String deleteByIdSQLQuery() {
        return null;
    }

    @Override
    protected String insertSQLQuery() {
        return null;
    }

    @Override
    protected String updateSQLQuery() {
        return null;
    }

    @Override
    protected String deleteSQLQuery() {
        return null;
    }
}
