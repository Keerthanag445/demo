package org.cybersecurity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String Url="jdbc:mysql://localhost:3306/CyberArch";
    private static final String UserName="root";
    private static final String Password="Keerthana@04";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Url, UserName, Password);
    }
 }
