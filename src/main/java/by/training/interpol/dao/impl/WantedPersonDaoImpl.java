package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.WantedPersonDao;
import by.training.interpol.entity.BirthPlace;
import by.training.interpol.entity.Gender;
import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WantedPersonDaoImpl extends BaseDao<WantedPerson> implements WantedPersonDao {
    private static Logger logger = LogManager.getLogger();
    private static WantedPersonDaoImpl instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private static final String SQL_SELECT_WANTED_PEOPLE_FULL =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.name as birth_place, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.birth_place_id " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationalities n ON np.nationality_id = n.nationality_id " +
                    "ORDER BY w.person_id";
    private static final String SQL_SELECT_WANTED_PEOPLE_BRIEF =
            "SELECT w.person_id, w.name, w.surname, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationalities n ON np.nationality_id = n.nationality_id " +
                    "ORDER BY w.person_id";
    private static final String SQL_SELECT_PERSON_BY_ID =
            "SELECT w.person_id, w.name, w.surname, w.gender, w.characteristics, w.height, w.weight, w.charges, " +
                    "b.name as birth_place, w.birth_date, w.image, n.name as nationality " +
                    "FROM wanted_people w INNER JOIN birth_places b " +
                    "ON w.birth_place_id = b.birth_place_id " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationalities n ON np.nationality_id = n.nationality_id " +
                    "WHERE w.person_id=?";
    private static final String SQL_INSERT_WANTED_PERSON =
            "INSERT INTO wanted_people (name, surname, gender, characteristics, height," +
                    " weight, charges, birth_place_id, birth_date, image) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_WANTED_PERSON_ID =
            "SELECT person_id FROM wanted_people " +
                    "WHERE name=? AND charges=? AND birth_date=?";
    private static final String SQL_DELETE_PERSON_BY_ID =
            "DELETE FROM wanted_people WHERE person_id=?";

    private WantedPersonDaoImpl(){
    }

    public static WantedPersonDaoImpl getInstance() {
        if (!isInstanceCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new WantedPersonDaoImpl();
                    isInstanceCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public List<WantedPerson> findWantedPeopleBrief() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_WANTED_PEOPLE_BRIEF);
           // ResultSet rs = statement.executeQuery(SQL_INSERT_WANTED_PERSON); //todo change
            return parseResultSetForEntitiesBrief(rs);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(statement, connection);
        }
    }

    @Override
    public long findWantedPersonId(WantedPerson person) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_WANTED_PERSON_ID);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getCharges());
            preparedStatement.setString(3, person.getBirthDate());

            System.out.println(person.getName());
            System.out.println(person.getCharges());
            System.out.println(person.getBirthDate());
            ResultSet rs = preparedStatement.executeQuery();
            // ResultSet rs = statement.executeQuery(SQL_INSERT_WANTED_PERSON); //todo change
            if (rs.next()) {
                return rs.getLong("person_id");
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
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
                        weight, charges, new BirthPlace(birthPlace), birthDate, nationality, image);
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
                        weight, charges, new BirthPlace(birthPlace), birthDate, nationality, image);
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
        preparedStatement.setString(1, entity.getName());
        String surname = entity.getSurname();
        if (surname == null || surname.isEmpty()) {
            preparedStatement.setNull(2, Types.VARCHAR);
        } else {
            preparedStatement.setString(2, surname);
        }
        String gender = entity.getGender().toString();
        preparedStatement.setString(3, gender);
        String characteristics = entity.getCharacteristics();
        preparedStatement.setString(4, characteristics);
        Float height = entity.getHeight();
        if (height == null) {
            preparedStatement.setNull(5, Types.NULL);
        } else {
            preparedStatement.setFloat(5, height);
        }
        Float weight = entity.getWeight();
        if (weight == null) {
            preparedStatement.setNull(6, Types.NULL);
        } else {
            preparedStatement.setFloat(6, weight);
        }
        preparedStatement.setString(7, entity.getCharges());
        preparedStatement.setLong(8, entity.getBirthPlace().getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate personBirthDate = LocalDate.parse(entity.getBirthDate(), formatter);

        preparedStatement.setDate(9, Date.valueOf(personBirthDate), Calendar.getInstance());

        InputStream is = entity.getImageIs();
        int size = entity.getSize();
        preparedStatement.setBinaryStream(10, is, size);
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
        return SQL_DELETE_PERSON_BY_ID;
    }

    @Override
    protected String insertSQLQuery() {
        return SQL_INSERT_WANTED_PERSON;
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
