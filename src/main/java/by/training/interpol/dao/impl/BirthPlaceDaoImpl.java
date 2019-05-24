package by.training.interpol.dao.impl;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.BirthPlaceDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.entity.BirthPlace;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BirthPlaceDaoImpl extends BaseDao<BirthPlace> implements BirthPlaceDao {
    private static Logger logger = LogManager.getLogger();
    private final static BirthPlaceDaoImpl INSTANCE = new BirthPlaceDaoImpl();

    private static final String SQL_INSERT_BIRTH_PLACE =
            "INSERT INTO birth_places (name) VALUES (?)";
    private static final String SQL_SELECT_BIRTH_PLACE_ID =
            "SELECT birth_place_id FROM birth_places WHERE name=?";

    private BirthPlaceDaoImpl(){
    }

    public static BirthPlaceDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<BirthPlace> parseResultSetForEntities(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Optional<BirthPlace> parseResultSet(ResultSet rs) throws SQLException {
        return Optional.empty();
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, BirthPlace entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, BirthPlace entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement preparedStatement, BirthPlace entity) throws SQLException {

    }

    @Override
    protected String selectAllSQLQuery() {
        return null;
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
        return SQL_INSERT_BIRTH_PLACE;
    }

    @Override
    protected String updateSQLQuery() {
        return null;
    }

    @Override
    protected String deleteSQLQuery() {
        return null;
    }

    @Override
    public Optional<BirthPlace> findBirthPlaceId(BirthPlace birthPlace) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BIRTH_PLACE_ID);
            preparedStatement.setString(1, birthPlace.getName());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                long birthPlaceId = rs.getLong(1);
                return Optional.of(
                        new BirthPlace(birthPlaceId, birthPlace.getName()));
            } else {

                logger.log(Level.WARN, "No elements in result set!");
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }
}
