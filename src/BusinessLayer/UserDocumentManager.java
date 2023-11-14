package BusinessLayer;

import java.util.List;
import dao.*;
import java.util.LinkedList;

public class UserDocumentManager {
    private User user;
    DocumentDao documentDao;
    private List<Document> createdDocuments;
    private List<Document> favouriteDocuments;
    private List<Document> receivedDocuments;
    private List<Document> sharedDocuments;
   
   
    public User getUser(){
        return this.user;
    }
    
    public UserDocumentManager(User user){
        this.user = user;
        documentDao = new DocumentDao();
        createdDocuments = new LinkedList<Document>();
        favouriteDocuments = new LinkedList<Document>();
        receivedDocuments = new LinkedList<Document>();
        sharedDocuments = new LinkedList<Document>();
        
    }
    public Document addDocument(String documentName){
        Document document = documentDao.addDocument(documentName, user);
        document.setDao(documentDao);
        if(document != null){
           createdDocuments.add(document);
        }
        return document;
    }
    
    public boolean addFavouriteDocument(Document document){
        return false;
    }
    
    public boolean shareDocument(Document document){
        return false;
    }
    
    public boolean editPermissions(Document document, User user){
      return false;
    }
    
    public boolean deleteDocument(Document document){
      return false;
    }
    
    public List<Document> getCreatedDocuments() {
      return createdDocuments;
    }
    
    public List<Document> getFavouriteDocuments() {
    return favouriteDocuments;
    }
    
    public List<Document> getReceivedDocuments() {
    return receivedDocuments;
    }
    
    public List<Document> getSharedDocuments() {
    return sharedDocuments;
    } 
}
