package org.example;

import org.example.util.SqlServerConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = SqlServerConnection.getConnection();
            System.out.println("Połączono z bazą danych SQL Server");
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych: " + e.getMessage());
        } finally {
            SqlServerConnection.closeConnection(connection);
        }
    }
}