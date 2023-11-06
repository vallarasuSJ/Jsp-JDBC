package com.codewithkvJDBC.dao;

import com.codewithkvJDBC.Do.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private final Connection connection;
    private final String INSERT_USER = "INSERT INTO auth (username, password) VALUES (?, ?);";



    public RegisterDao() {
        connection= DbConnection.getConnection();
    }

    public void registerUser(String username, String password) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
