package org.cyberscam.dao;
import org.cyberscam.config.DatabaseConfig;
import org.cyberscam.models.User;
import java.sql.*;

public class UserDA0 {
    public boolean RegisterUser(User user) {
        String sql = "INSERT INTO Users(username,email,password,role) VALUES(?,?,?,'User')";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User LoginUser(String Email,String Password){
    String sql ="SELECT * FROM Users Where email = ? AND password = ?";
    try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1,Email);
        stmt.setString(2,Password);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            User user= new User();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
    }

    public boolean UpdatePassword(String Email,String newPassword){
        String sql = "UPDATE Users SET password = ? WHERE email = ?";
        try ( Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,newPassword);
            stmt.setString(2,Email);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

