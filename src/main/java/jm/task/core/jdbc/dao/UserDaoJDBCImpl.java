package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stmt = Util.getCon().createStatement()) {
            String query = "CREATE TABLE users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(45) NOT NULL, " +
                    " lastName VARCHAR(45) NOT NULL, " +
                    " age TINYINT(3) UNSIGNED, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(query);
            System.out.println("Table users was created");
        } catch (SQLException e) {
            System.out.println("Table users already exists");
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = Util.getCon().createStatement()) {
            String query = "DROP TABLE users";
            stmt.executeUpdate(query);
            System.out.println("Table was dropped");
        } catch (SQLException e) {
            System.out.println("No table to delete");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = Util.getCon().prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();

            System.out.println("User been saved");
        } catch (SQLException e) {
            System.out.println("User can't be saved");
        }
    }

    public void removeUserById(long id) {
        try (Statement stmt = Util.getCon().createStatement()) {
            String query = "DELETE FROM users WHERE id = " + id;
            stmt.executeUpdate(query);
            System.out.println("User by id " + id + " was deleted");
        } catch (SQLException e) {
            System.out.println("Coulnd't delete user by id");
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Statement stmt = Util.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                usersList.add(user);
            }
            System.out.println("Got Users list successfully\n");
        } catch (SQLException e) {
            System.out.println("Couldn't get Users list");
        }

        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement stmt = Util.getCon().createStatement()) {
            String query = "TRUNCATE TABLE users";
            stmt.executeUpdate(query);
            System.out.println("Table Users was cleared");
        } catch (SQLException e) {
            System.out.println("Couldn't clear Users table");
        }
    }
}