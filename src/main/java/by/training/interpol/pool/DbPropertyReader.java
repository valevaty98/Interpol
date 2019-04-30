package by.training.interpol.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

class DbPropertyReader {
    private static Logger log = LogManager.getLogger();
    private final static int DEFAULT_POOL_SIZE = 10;
    private final static String DEFAULT_PROPERTY_FILE_PATH = "database";
    private final static String POOL_SIZE_PROPERTY_NAME = "poolSize";
    private final static String USER_PROPERTY_NAME = "user";
    private final static String PASSWORD_PROPERTY_NAME = "password";
    private final static String URL_PROPERTY_NAME = "url";

    static int readPoolSize() {
        int actualPoolSize;
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DEFAULT_PROPERTY_FILE_PATH);
            actualPoolSize = Integer.parseInt(resource.getString(POOL_SIZE_PROPERTY_NAME));
            if (actualPoolSize < 1) {
                actualPoolSize = DEFAULT_POOL_SIZE;
                log.log(Level.WARN, "Pool size < 1 in the properties file. Set default pool size.");
            }
        } catch (NumberFormatException e) {
            log.log(Level.WARN, "Error while parsing pool size from properties file. Set default pool size.");
            actualPoolSize = DEFAULT_POOL_SIZE;
        } catch (MissingResourceException e) {
            log.log(Level.WARN, "Resource bundle not found.", e);
            actualPoolSize = DEFAULT_POOL_SIZE;
        }
        return actualPoolSize;
    }

    static Properties readConnectionProperties() {
        Properties props = new Properties();
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DEFAULT_PROPERTY_FILE_PATH);
            props.put(USER_PROPERTY_NAME, resource.getString(USER_PROPERTY_NAME));
            props.put(PASSWORD_PROPERTY_NAME, resource.getString(PASSWORD_PROPERTY_NAME));
            return props;
        } catch (MissingResourceException e) {
            log.log(Level.FATAL, "Resource bundle not found.", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.log(Level.FATAL, "Error during getting property.", e);
            throw new RuntimeException(e);
        }
    }

    static String readUrl() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DEFAULT_PROPERTY_FILE_PATH);
            return resource.getString(URL_PROPERTY_NAME);
        } catch (MissingResourceException e) {
            log.log(Level.FATAL, "Resource bundle not found.", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.log(Level.FATAL, "Error during getting property.", e);
            throw new RuntimeException(e);
        }
    }
}
