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

    private static final String SQL_SELECT_WANTED_PEOPLE_FULL =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.name as birth_place, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.birth_place_id " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationality n ON np.nationality_id = n.nationality_id " +
                    "ORDER BY w.person_id";
    private static final String SQL_SELECT_WANTED_PEOPLE_BRIEF =
            "SELECT w.person_id, w.name, w.surname, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationality n ON np.nationality_id = n.nationality_id " +
                    "ORDER BY w.person_id";
    private static final String SQL_SELECT_PERSON_BY_ID =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.name as birth_place, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.birth_place_id " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationality n ON np.nationality_id = n.nationality_id " +
                    "WHERE w.person_id=?";
    private static final String SQL_SELECT_ALL_NATIONALITIES =
            "SELECT DISTINCT n.name as nationality " +
                    "FROM wanted_people w " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationality n ON np.nationality_id = n.nationality_id";


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

    @Override
    public List<String> findNationalities() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_NATIONALITIES);

            List<String> nationalities = new ArrayList<>();
            while (rs.next()) {
                nationalities.add(rs.getString("nationality"));
            }
            return nationalities;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(statement, connection);
        }
    }

    protected List<WantedPerson> parseResultSetForEntitiesBrief(ResultSet rs) throws SQLException {
        List<WantedPerson> wantedPeople = new ArrayList<>();
        long previousPersonId = 0;
        while (rs.next()) {
            long wantedPersonId = rs.getLong("person_id");
            if (previousPersonId == wantedPersonId) {
                wantedPeople.get(wantedPeople.size() - 1).getNationality().add(rs.getString("nationality"));
            } else {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String birthDate = rs.getDate("birth_date").toString();
                Blob image = rs.getBlob("image");
                List<String> nationality = new ArrayList<>();
                nationality.add(rs.getString("nationality"));
                WantedPerson person = new WantedPerson(wantedPersonId, name, surname, birthDate, nationality, image);
                wantedPeople.add(person);
                previousPersonId = wantedPersonId;
            }
        }
        return wantedPeople;
    }

    @Override
    protected List<WantedPerson> parseResultSetForEntities(ResultSet rs) throws SQLException {
        List<WantedPerson> wantedPeople = new ArrayList<>();
        long previousPersonId = 0;
        while (rs.next()) {
            long wantedPersonId = rs.getLong("person_id");
            if (previousPersonId == wantedPersonId) {
                wantedPeople.get(wantedPeople.size() - 1).getNationality().add(rs.getString("nationality"));
            } else {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Gender gender = Gender.valueOf(rs.getString("gender").toUpperCase());
                String characteristics = rs.getString("characteristics");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                String charges = rs.getString("charges");
                String birthPlace = rs.getString("birth_place");
                String birthDate = rs.getDate("birth_date").toString();
                Blob image = rs.getBlob("image");
                List<String> nationality = new ArrayList<>();
                nationality.add(rs.getString("nationality"));
                WantedPerson person = new WantedPerson(wantedPersonId, name, surname, gender, characteristics, height,
                        weight, charges, birthPlace, birthDate, nationality, image);
                wantedPeople.add(person);
                previousPersonId = wantedPersonId;
            }
        }
        return wantedPeople;
    }

    @Override
    protected Optional<WantedPerson> parseResultSet(ResultSet rs) throws SQLException {
        WantedPerson person = null;
        boolean wasFirstRow = false;
        while (rs.next()) {
            if (!wasFirstRow) {
                long wantedPersonId = rs.getLong("person_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Gender gender = Gender.valueOf(rs.getString("gender").toUpperCase());
                String characteristics = rs.getString("characteristics");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                String charges = rs.getString("charges");
                String birthPlace = rs.getString("birth_place");
                String birthDate = rs.getDate("birth_date").toString();
                Blob image = rs.getBlob("image");
                List<String> nationality = new ArrayList<>();
                nationality.add(rs.getString("nationality"));
                person = new WantedPerson(wantedPersonId, name, surname, gender, characteristics, height,
                        weight, charges, birthPlace, birthDate, nationality, image);
                wasFirstRow = true;
            } else {
                person.getNationality().add(rs.getString("nationality"));
            }
        }
        if (wasFirstRow) {
            return Optional.of(person);
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
        return SQL_SELECT_WANTED_PEOPLE_FULL;
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
