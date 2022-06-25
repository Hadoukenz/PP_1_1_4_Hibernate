package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final String create = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
            "age TINYINT NOT NULL)";
    private final String drop = "DROP TABLE IF EXISTS users";
    private final String delete = "DELETE FROM users";
    private final String select = "SELECT * FROM users";

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().createNativeQuery(create, User.class).executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().createNativeQuery(drop, User.class).executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            sessionFactory.getCurrentSession().persist(user);
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            User user = sessionFactory.getCurrentSession().get(User.class, id);
            sessionFactory.getCurrentSession().remove(user);
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = null;
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            listOfUsers = sessionFactory.getCurrentSession().createNativeQuery(select, User.class).list();
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().createNativeQuery(delete, User.class).executeUpdate();
            sessionFactory.getCurrentSession().getTransaction().commit();
            sessionFactory.getCurrentSession().close();
        } catch (Exception e) {
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}