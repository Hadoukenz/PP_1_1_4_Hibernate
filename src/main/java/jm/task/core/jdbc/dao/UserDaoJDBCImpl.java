package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String CREATE = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT NOT NULL AUTO_INCREMENT, " +
            " name VARCHAR(45) NOT NULL, " +
            " lastName VARCHAR(45) NOT NULL, " +
            " age TINYINT(3) UNSIGNED, " +
            " PRIMARY KEY ( id ))";

    private final String SELECT = "SELECT * FROM users";

    private final String INSERT = "INSERT INTO users (name, lastName, age) " +
            "VALUES (?, ?, ?)";

    private final String DROP = "DROP TABLE IF EXISTS users";

    private final String REMOVE = "DELETE FROM users WHERE id = ?";

    private final String TRUNCATE = "TRUNCATE TABLE users";

    private Connection connection = Util.openConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement sqlStatement = connection.createStatement()) {
            sqlStatement.executeUpdate(CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement sqlStatement = connection.createStatement()) {
            sqlStatement.executeUpdate(DROP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepStatement = connection.prepareStatement(INSERT)) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
            System.out.println("User " + name + " " + lastName + " added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepStatement = connection.prepareStatement(REMOVE)) {
            prepStatement.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement sqlStatement = connection.createStatement();
            ResultSet getColumn = sqlStatement.executeQuery(SELECT)) {
            while (getColumn.next()) {
                User user = new User();
                user.setId(getColumn.getLong(1));
                user.setName(getColumn.getString(2));
                user.setLastName(getColumn.getString(3));
                user.setAge(getColumn.getByte(4));
                usersList.add(user);
                System.out.println("User " + user.getName() + " " + user.getLastName() + " added to list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement sqlStatement = connection.createStatement()) {
            sqlStatement.executeUpdate(TRUNCATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}