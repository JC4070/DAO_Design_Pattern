package org.cst8288Lab2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private static Connection connection = null;

    /**
     * Initializes the database connection based on properties file.
     *
     * @param propertiesFilePath path to the database properties file
     * @return true if connection is successfully established, false otherwise
     */
    public static boolean initializeConnection(String propertiesFilePath) {
        Properties dbProperties = new Properties();

        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            dbProperties.load(input);
        } catch (IOException ex) {
            System.out.println("Error loading database properties file.");
            ex.printStackTrace();
            return false;
        }

        String db = dbProperties.getProperty("db");
        String name = dbProperties.getProperty("name");
        String host = dbProperties.getProperty("host");
        String password = dbProperties.getProperty("pass");
        String port = dbProperties.getProperty("port");
        String username = dbProperties.getProperty("user");

        String jdbcUrl = "jdbc:" + db + "://" + host + ":" + port + "/" + name;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            return true;
        } catch (SQLException ex) {
            System.out.println("Database connection error, Please Check :(");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the established database connection.
     *
     * @return the database connection object
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

