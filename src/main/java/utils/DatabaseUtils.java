package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUtils {

    public static final String URL = PropertyLoader.loadProperty("URL");
    public static final String USER = PropertyLoader.loadProperty("USER");
    public static final String PASSWORD = PropertyLoader.loadProperty("PASSWORD");
    public static final String DRIVER = PropertyLoader.loadProperty("DRIVER");

    private static final Logger log = Logger.getLogger(DatabaseUtils.class.getName());

    private static void loadDriver() {
        try {
            log.info("Loading db driver...");
            Class.forName(DRIVER);
            log.info("Loading db driver -> success");
        } catch (ClassNotFoundException e) {
            log.error("Loading db driver -> FAIL");
            e.printStackTrace();
            throw new Error(e);
        }

    }

    public static Connection getDBConnection(String dbName) {
        Connection connection = null;
        loadDriver();
        try {
            connection = DriverManager.getConnection(URL + dbName, USER, PASSWORD);
            log.info("create connection to DB -> success");
            return connection;
        } catch (SQLException e) {
            log.error("create connection to DB -> FAIL");
            e.printStackTrace();
        }
        return connection;
    }
}
