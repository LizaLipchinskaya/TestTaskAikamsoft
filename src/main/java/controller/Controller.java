package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
    public static final String URL = "jdbc:postgresql://localhost:5432/storeAikamsoft";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "Hello2015";
    public static final String JDBC_POSTGRES_DRIVER = "org.postgresql.Driver";

    private Connection connection;

    public Controller() {
        try {
            Class.forName(JDBC_POSTGRES_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return connection = null;
        }

        return connection;
    }
}
