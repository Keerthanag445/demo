package org.cybersecurity;
import java.util.Scanner;
import java.sql.*;
public class UserLogin {
    public boolean RegisterUser(User user) {
        String sql = "INSERT INTO Users(username,email,password) VALUES(?,?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User LoginUser(String Email,String Password){
    String sql ="SELECT * FROM Users Where email = ? AND Password = ?";
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

public static void main(String[] args) {
    UserLogin userLogin = new UserLogin();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("1. Register User");
        System.out.println("2. Login User");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter user name:");
                String userName=scanner.nextLine();
                System.out.println("Enter user mail:");
                String email=scanner.nextLine();
                System.out.println("Set your password:");
                String password = scanner.nextLine();
                if (userLogin.RegisterUser(new User(userName,email,password))){
                    System.out.println("User registered successfully");
                }
                else {
                    System.out.println("User registration failed ");
                }
                break;
            case 2:
                System.out.println("Enter user mail:");
                email = scanner.nextLine();
                System.out.println("Enter your password:");
                password = scanner.nextLine();
                User loggedInUser = userLogin.LoginUser(email, password);
                if (loggedInUser != null) {
                    System.out.println("User logged in successfully: " );
                } else {
                    System.out.println("Login failed. Invalid email or password.");
                }
                break;
            case 3:
                System.out.println("Exiting program...");
                scanner.close();
                break;
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }
}
}