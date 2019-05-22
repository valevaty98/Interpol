package by.training.interpol.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

class CheckingPoolTimerTask extends TimerTask {
    private CountDownLatch latch;

    public CheckingPoolTimerTask(CountDownLatch latch) {
        this.latch = latch;
    }

    private static Logger log = LogManager.getLogger();
    @Override
    public void run() {
        ConnectionPool pool = ConnectionPool.getInstance();
        int actualSize =  pool.getSize();
        BlockingQueue<Connection> availableConnections = pool.getAvailableConnections();
        BlockingQueue<Connection> usedConnections = pool.getUsedConnections();
        try {
            while (actualSize > (availableConnections.size() + usedConnections.size())) {
                    availableConnections.put(DbConnector.createConnection());
            }
            latch.countDown();
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "Error during putting new connection to the available connections", e);
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error during creating connection.", e);
        }
    }
}
