package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao implForUser = new UserDaoHibernateImpl();

    public void createUsersTable() {
        implForUser.createUsersTable();
    }

    public void dropUsersTable() {
        implForUser.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        implForUser.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        implForUser.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return implForUser.getAllUsers();
    }

    public void cleanUsersTable() {
        implForUser.cleanUsersTable();
    }
}
