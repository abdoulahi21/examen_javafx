package com.example.examen_javafx.repository;


import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp {
    Connection connection;
    public User getconn(String login, String password) {
        BD bd=new BD();
        connection= bd.getConnection();
        User user=null;
        try {
            String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet rs=statement.executeQuery();
            if(rs.next())
            {
                user=new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
}

