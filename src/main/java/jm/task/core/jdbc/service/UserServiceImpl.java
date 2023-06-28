package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

   public class UserServiceImpl implements UserService {
       //  //  private final UserDao userDao = new UserDaoJDBCImpl();
       private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

       //
        public UserServiceImpl() {
        }

       @Override
       public void createUsersTable() {
            userDaoHibernate.createUsersTable();
       }

       @Override
       public void dropUsersTable() {
       userDaoHibernate.dropUsersTable();
       }

       @Override
       public void saveUser(String name, String lastName, byte age) {
       userDaoHibernate.saveUser(name, lastName, age);
       }

       @Override
       public void removeUserById(long id) {
       userDaoHibernate.removeUserById(id);
       }

       @Override
       public List<User> getAllUsers() {
           return userDaoHibernate.getAllUsers();
       }

       @Override
       public void cleanUsersTable() {
       userDaoHibernate.cleanUsersTable();
       }
   }
 //  }
//
//    public void saveUser(String name, String lastName, byte age) {
//    userDao.saveUser(name, lastName, age);
//        System.out.println("User" + lastName + " added to Database");
//    }
//
//    public void removeUserById(long id) {
//  //  userDao.removeUserById(id);
//        System.out.println("User with id " + id + "removed");
//    }
//
//    public List<User> getAllUsers() {
//
//  //      List<User> users = userDao.getAllUsers();
//  //      for (User user : users) {
//            System.out.println(user);
//        }
//        return users;
//    }
//
 //              public void cleanUsersTable () {
//    userDao.cleanUsersTable();
//    }
  //             }
