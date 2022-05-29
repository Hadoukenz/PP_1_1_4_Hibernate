package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String NAME = "root";
    private static final String PASS = "root";

    public static Connection getCon() throws SQLException {
        Connection con = DriverManager.getConnection(URL, NAME, PASS);
        System.out.println("-------------------------");
        return con;
    }


}