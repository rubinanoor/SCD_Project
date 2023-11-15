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
        boolean saved = false;
        try(Connection connection = getConnection()){
            connection.setAutoCommit(false);
            
            //String username = document.getOwner().getUsername();
            PreparedStatement statement = connection.prepareCall("UPDATE documents SET last_modified_date = ?, color = ?, word_count = ?, line_count = ? WHERE document_id = ?");
            statement.setDate(1, new java.sql.Date(System.currentTimeMillis())); 
            statement.setString(2, document.getColor().toString()); 
            statement.setInt(3, document.getWordCount());
            statement.setInt(4, document.getLineCount());
            statement.setInt(5, document.getDocid());
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                for(int i=0;i < document.getDocumentElements().size();i++){
                    DocumentElement docElement = document.getDocumentElements().get(i);
                    
                    PreparedStatement textElementStatement = connection.prepareStatement("INSERT INTO TextElement (document_id, content, start, end, font, font_size, is_bold, is_italic, foreground_color) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    textElementStatement.setInt(1, document.getDocid());
                    textElementStatement.setString(2, docElement.getContent());
                    textElementStatement.setInt(3, docElement.getStart());
                    textElementStatement.setInt(4, docElement.getEnd());
                    textElementStatement.setString(5, docElement.getFontFamily());
                    textElementStatement.setInt(6, docElement.getFontSize());
                    textElementStatement.setBoolean(7, docElement.isBold());
                    textElementStatement.setBoolean(8, docElement.isItalic());
                    textElementStatement.setString(9, docElement.getFontColor().toString());

                    int result = textElementStatement.executeUpdate();
                    if(result > 0){
                        saved = true;
                    }
                    else{
                        saved = false;
                        break;
                    }
                
                }
                
                if(saved == false){
                    try {
                        if (connection != null) {
                        connection.rollback();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return false;
                      }
                    }
                else{
                    connection.commit();
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
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
