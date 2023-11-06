package com.codewithkvJDBC.dao;
import com.codewithkvJDBC.Do.DbConnection;
import com.codewithkvJDBC.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private  final Connection con;
    public UserDao() {
       con= DbConnection.getConnection();
    }

    private String selectQuery="SELECT id,username,password from auth WHERE username=? AND password=?;";
    public User loginUser(String username, String password) {
        User user=null;
        try {
            PreparedStatement ps=con.prepareStatement(selectQuery);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user=new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;

    }
}
