package com.codewithkvJDBC.dao;


import com.codewithkvJDBC.Do.DbConnection;
import com.codewithkvJDBC.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final Connection connection;
    private final String SELECT_ALL = "SELECT id, item, userId FROM todo WHERE userId=? AND delete_flag=false;";
    private final String INSERT_TODO = "INSERT INTO todo (item, userId) VALUES (?, ?);";
    private final String DELETE_TODO = "UPDATE todo SET delete_flag=true WHERE id=?;";

    public TodoDao() {
        connection= DbConnection.getConnection();
    }


    public void addTodo(String item, int userId) {
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_TODO);
            preparedStatement.setString(1,item);
            preparedStatement.setInt(2,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodo(int id)  {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_TODO);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public List<Todo> selectAllTodos(int userId) {
        List<Todo> todos=new ArrayList<>();

        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                Todo todo=new Todo();
                todo.setId(Integer.parseInt(resultSet.getString("id")));
                todo.setTodo(resultSet.getString("item"));
                todo.setUserId(resultSet.getInt("userId"));
                todos.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }
}