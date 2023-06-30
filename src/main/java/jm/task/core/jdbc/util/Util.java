package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect" +
            "=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MySQLPizdets";
    private static SessionFactory sessionFactory;


    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }

    public static SessionFactory getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USERNAME)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class)
                        .setProperty("hibernate.c3p0.min_size", "5")
                        .setProperty("hibernate.c3p0.max_size", "200")
                        .setProperty("hibernate.c3p0.max_statements", "200");

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("connected");
            } catch (HibernateException e) {
                System.out.println("Опять 25!");
                e.printStackTrace();
            }
        }

            return sessionFactory;
        }

    }







