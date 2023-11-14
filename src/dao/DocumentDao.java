package dao;

import BusinessLayer.*;
import java.sql.*;
import java.util.List;


public class DocumentDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/SMARTSCRIPT";
    private static final String USER = "root";
    private static final String PASSWORD = "Arfasara1624928";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public Document addDocument(String documentName, User user) {
    String username = user.getUsername();
    try (Connection connection = getConnection()) {
        if (documentExists(connection, documentName, username)) {
            System.out.println("Document with the given name already exists.");
            return null;
        }

        Document newDocument = new Document();
        newDocument.setDocName(documentName);
        newDocument.setOwner(user);

        String insertQuery = "INSERT INTO documents (title, username) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newDocument.getDocName());
            preparedStatement.setString(2, newDocument.getOwner().getUsername());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating document failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    newDocument.setDocid(generatedId);
                } else {
                    throw new SQLException("Creating document failed, no ID obtained.");
                }
            }
        }

        return newDocument;

    } catch (SQLException e) {
        return null;
    }
}

    public boolean saveDocument(Document document, List<DocumentElement> documentElements){
        return false;
    }
    private boolean documentExists(Connection connection, String documentName, String username) throws SQLException {
        String query = "SELECT * FROM documents WHERE title = ? AND username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, documentName);
            preparedStatement.setString(2, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    
   
}
