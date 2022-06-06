package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final String create = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
            "age TINYINT NOT NULL)";
    private final String drop = "DROP TABLE IF EXISTS users";
    private final String delete = "DELETE FROM users";
    private final String select = "SELECT * FROM users";

    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery(create, User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery(drop, User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = null;
        try {
            session.beginTransaction();
            listOfUsers = session.createNativeQuery(select, User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery(delete, User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}