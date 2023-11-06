package com.codewithkvJDBC.Do;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final static String connectionUrl = "jdbc:mysql://localhost:3306/demo";

    private static final String username = "root";

    private static final String password = "root";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connection " + !connection.isClosed());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
