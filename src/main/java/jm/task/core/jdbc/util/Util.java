package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MySQLPizdets";


    public static Connection getCon() {
        try {

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "MySQLPizdets");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }
}




