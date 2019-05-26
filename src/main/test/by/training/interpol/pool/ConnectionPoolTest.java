package by.training.interpol.pool;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest {
    private static ConnectionPool pool;

    @BeforeClass
    public void setUp() {
        pool = ConnectionPool.getInstance();
    }

    @Test
    public void testGetInstance() {
        int expectedPoolSize = 20;
        int actualPoolSize = pool.getAvailableConnections().size() + pool.getUsedConnections().size();

        Assert.assertEquals(actualPoolSize, expectedPoolSize);
    }

    @Test
    public void testGetConnection() {
        Connection actualConnection = pool.getConnection();
        Assert.assertNotNull(actualConnection);
        pool.releaseConnection(actualConnection);
    }

    @AfterClass
    public void tearDown() {
        pool.destroyConnectionPool();
    }
}