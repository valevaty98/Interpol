package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.NationalityDao;
import by.training.interpol.entity.Nationality;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NationalityDaoImpl extends BaseDao<Nationality> implements NationalityDao {
    private final static NationalityDaoImpl INSTANCE = new NationalityDaoImpl();

    private static final String SQL_INSERT_NATIONALITY =
            "INSERT INTO nationalities (name) VALUES (?)";
    private static final String SQL_INSERT_PERSON_NATION =
            "INSERT INTO nation_person (nationality_id, wanted_person_id) VALUES (?,?)";
    private static final String SQL_SELECT_ALL_NATIONALITIES =
            "SELECT nationality_id, name FROM nationalities";
    private static final String SQL_SELECT_NATIONALITY_ID =
            "SELECT nationality_id FROM nationalities WHERE name=?";
    private static final String SQL_DELETE_FROM_NATION_PERSON =
            "DELETE FROM nation_person WHERE wanted_person_id=?";
    private static final String SQL_SELECT_WANTED_PEOPLE_NATIONALITY_NAMES =
            "SELECT DISTINCT n.name as nationality " +
                    "FROM wanted_people w " +
                    "INNER JOIN nation_person np ON np.wanted_person_id=w.person_id " +
                    "INNER JOIN nationalities n ON np.nationality_id = n.nationality_id";

    private NationalityDaoImpl(){
    }

    public static NationalityDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> findPeopleNationalityNames() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_WANTED_PEOPLE_NATIONALITY_NAMES);

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

    @Override
    public boolean insertNationPerson(long personId, long nationId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON_NATION);
            preparedStatement.setLong(1, nationId);
            preparedStatement.setLong(2, personId);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public boolean deleteFromNationPerson(long personId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_FROM_NATION_PERSON);
            preparedStatement.setLong(1, personId);
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public Optional<Nationality> findNationalityId(String nationality) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            if (connection == null) {
                throw new DaoException("Null pointer to the connection.");
            }
            preparedStatement = connection.prepareStatement(SQL_SELECT_NATIONALITY_ID);
            preparedStatement.setString(1, nationality);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new Nationality(
                        rs.getLong("nationality_id"), nationality));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    protected List<Nationality> parseResultSetForEntities(ResultSet rs) throws SQLException {
        List<Nationality> nationalities = new ArrayList<>();
        while (rs.next()) {
            nationalities.add(new Nationality(
                    rs.getLong("nationality_id"), rs.getString("name")));
        }
        return nationalities;
    }

    @Override
    protected Optional<Nationality> parseResultSet(ResultSet rs) throws SQLException {
        return Optional.empty();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Nationality entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Nationality entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, Nationality entity) throws SQLException {

    }

    @Override
    protected String selectAllSQLQuery() {
        return SQL_SELECT_ALL_NATIONALITIES;
    }

    @Override
    protected String selectByIdSQLQuery() {
        return null;
    }

    @Override
    protected String deleteByIdSQLQuery() {
        return null;
    }

    @Override
    protected String insertSQLQuery() {
        return SQL_INSERT_NATIONALITY;
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
