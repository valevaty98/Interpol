package by.training.interpol.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger log = LogManager.getLogger();
    private static ConnectionPool instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;
    private int actualPoolSize;
    private final static int DEFAULT_DELAY_FOR_SCHEDULE_CHECKING_TASK = 50;

    private ConnectionPool() {

        actualPoolSize = DbPropertyReader.readPoolSize();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error during driver registration.", e);
        }
        availableConnections = new LinkedBlockingQueue<>(actualPoolSize);
        usedConnections = new LinkedBlockingQueue<>(actualPoolSize);
        try {
            availableConnections.addAll(DbConnectionsCreator.createConnections(actualPoolSize));
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error during creating connections.", e);
        }
        checkConnectionPool();
    }

    public static ConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isInstanceCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "Error during taking from the connection pool or putting connection to the used connections", e);
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if (usedConnections.contains(connection)) {
            boolean isQueueChanged = usedConnections.remove(connection);
            try {
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                log.log(Level.ERROR, "Error during restoring connection to the connection pool", e);
            }
            return isQueueChanged;
        } else {
            log.log(Level.WARN, "Connection for release isn't from the connection pool.");
            return false;
        }
    }

    private void checkConnectionPool() {
        CountDownLatch latch = new CountDownLatch(1);
        Timer timer = new Timer();
        timer.schedule(new CheckingPoolTimerTask(latch), DEFAULT_DELAY_FOR_SCHEDULE_CHECKING_TASK);
//        try {
//            //TimeUnit.MILLISECONDS.sleep(1500);
//            //latch.await();
//        } catch (InterruptedException e) {
//            log.log(Level.ERROR, "Error during waiting for connection pool to be checked.");
//        }
    }
    
    int getSize() {
        return actualPoolSize;
    }

    BlockingQueue<Connection> getAvailableConnections() {
        return availableConnections;
    }

    BlockingQueue<Connection> getUsedConnections() {
        return usedConnections;
    }
}