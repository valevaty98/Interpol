package by.training.interpol.dao;

import by.training.interpol.entity.Entity;
import by.training.interpol.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends Entity> {
    private static Logger log = LogManager.getLogger();
    protected static ConnectionPool pool = ConnectionPool.getInstance();

    protected abstract List<T> parseResultSetForEntities(ResultSet rs) throws SQLException;
    protected abstract Optional<T> parseResultSet(ResultSet rs) throws SQLException;
    protected abstract void prepareStatementForInsert(PreparedStatement preparedStatement, T entity) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;
    protected abstract void prepareStatementForDelete(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract String selectAllSQLQuery();
    protected abstract String selectByIdSQLQuery();
    protected abstract String deleteByIdSQLQuery();
    protected abstract String insertSQLQuery();
    protected abstract String updateSQLQuery();
    protected abstract String deleteSQLQuery();

    public List<T> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectAllSQLQuery());
            return parseResultSetForEntities(rs);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(statement, connection);
        }
    }

    public Optional<T> findById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(selectByIdSQLQuery());
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public boolean deleteById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(deleteByIdSQLQuery());
            preparedStatement.setLong(1, id);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public boolean delete(T entity) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQLQuery());
            prepareStatementForDelete(preparedStatement, entity);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public boolean insert(T entity) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQLQuery());
            this.prepareStatementForInsert(preparedStatement, entity);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    public Optional<T> update(T entity) throws DaoException {
        Optional<T> oldEntity = findById(entity.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (pool == null) {
                throw new DaoException("Null pointer to the pool.");
            }
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQLQuery());
            prepareStatementForUpdate(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQLQuery.", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
        return oldEntity;
    }

    protected void closeResources(Statement statement, Connection connection){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, "Error during closing statement.");
            }
        }
        pool.releaseConnection(connection);
    }
}
