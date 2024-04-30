package org.example.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class SqlServerConnection {

    private static final String PROPERTIES_FILE = "/credentials/db.properties";
    private static final Properties props = new Properties();
    static {
        try (InputStream input = SqlServerConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = props.getProperty("jdbc.url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
