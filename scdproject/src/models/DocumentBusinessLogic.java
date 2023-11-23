package models;

import View.DocumentManager;
 public class DocumentBusinessLogic {

    private DocumentManager documentManager;

    public DocumentBusinessLogic() {
        this.documentManager = DocumentManager.getInstance();
    }

    public void createDocument(String documentName) {
        documentManager.createDocument(documentName);
    }

    public String getDocumentContent(String documentName) {
        return documentManager.getDocumentContent(documentName);
    }

    public void saveDocument(String documentName, String content) {
        documentManager.saveDocument(documentName, content);
    }

    // Additional methods for document-related operations can be added here
}

