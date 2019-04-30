package by.training.interpol.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DbConnectionsCreator {

    static List<Connection> createConnections(int actualPoolSize) throws SQLException {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < actualPoolSize; i++) {
            connections.add(DbConnector.createConnection());
        }
        return connections;
    }
}
