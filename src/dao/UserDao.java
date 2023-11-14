package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/SMARTSCRIPT";
    private static final String USER = "root";
    private static final String PASSWORD = "Arfasara1624928";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public boolean login(String username, char[] password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, String.valueOf(password));

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean signup(String username, char[] password) {
        if(isUserInDatabase(username)){
            return false;
        }
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
        
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, String.valueOf(password));
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        } 
    }
 
    private boolean isUserInDatabase(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
