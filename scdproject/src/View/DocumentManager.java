package View;

import java.util.HashMap;
import java.util.Map;

public class DocumentManager {
    private static DocumentManager instance;
    private Map<String, String> documentContents;

    private DocumentManager() {
        // Initialize the documentContents map to store document content by name
        documentContents = new HashMap<>();
    }

    public static DocumentManager getInstance() {
        if (instance == null) {
            instance = new DocumentManager();
        }
        return instance;
    }

    public void createDocument(String documentName) {
        // Create a new document with empty content
        documentContents.put(documentName, "");
    }

    public String getDocumentContent(String documentName) {
        // Get the content of a document by name
        return documentContents.get(documentName);
    }

    public void saveDocument(String documentName, String content) {
        // Save the content of a document
        documentContents.put(documentName, content);
    }

    // Additional methods for document-related operations can be added here
}
