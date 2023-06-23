package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getCon();

    public UserDaoJDBCImpl()  {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {

            String create ="CREATE TABLE IF NOT EXISTS USER "+
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), " +
                    "lastName VARCHAR(30), AGE INT)";

            statement.executeUpdate(create);
            System.out.println("Database has been created");
        } catch (SQLException e) {
            System.out.println("Failed to create a database");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            System.out.println("Deleting went wrong");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO user (name, lastName, age) " +
                "VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User saving went wrong");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
            try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
                pstm.setLong(1, id);
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Userremoval went wrong");
                e.printStackTrace();
            }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            System.out.println("ClearingTable went wrong");
            e.printStackTrace();
        }
    }
}
