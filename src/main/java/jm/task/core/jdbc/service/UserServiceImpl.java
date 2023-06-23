package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();

    public UserServiceImpl() throws SQLException {
    }

    public void createUsersTable() {
    userDao.createUsersTable();
    }

    public void dropUsersTable() {
    userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
    userDao.saveUser(name, lastName, age);
        System.out.println("User" + lastName + " added to Database");
    }

    public void removeUserById(long id) {
    userDao.removeUserById(id);
        System.out.println("User with id " + id + "removed");
    }

    public List<User> getAllUsers() {

        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
    userDao.cleanUsersTable();
    }
}