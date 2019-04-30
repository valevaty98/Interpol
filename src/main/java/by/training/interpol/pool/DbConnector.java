package by.training.interpol.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class DbConnector {
    static Connection createConnection() throws SQLException {
        String url = DbPropertyReader.readUrl();
        Properties props = DbPropertyReader.readConnectionProperties();
        return DriverManager.getConnection(url, props);
    }
}
