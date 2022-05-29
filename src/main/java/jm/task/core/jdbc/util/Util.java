package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String NAME = "root";
    private static final String PASS = "root";

    private static Connection connection = null;

    public static Connection openCon() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASS);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeCon() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return connection;
    }
}