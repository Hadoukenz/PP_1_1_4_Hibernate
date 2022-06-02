package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Name1", "LastName1", (byte) 20);
//        userService.saveUser("Name2", "LastName2", (byte) 25);
//        userService.saveUser("Name3", "LastName3", (byte) 31);
//        userService.saveUser("Name4", "LastName4", (byte) 38);
//
//        Iterator<User> usersList = userService.getAllUsers().iterator();
//        while (usersList.hasNext()) {
//            System.out.println(usersList.next() + "\n");
//        }
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
//        Util.closeCon();

        UserDao dao = new UserDaoHibernateImpl();
        User user = new User("Bulat", "Bulat", (byte) 18);
        dao.saveUser("Bulat", "Bulat", (byte) 18);

        List<User> students = dao.getAllUsers();
        students.forEach(s -> System.out.println(s.getName()));


    }
}