/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer;

/**
 *
 * @author Admin
 */
public class TextElement extends DocumentElement{
    
//    public TextElement(int docID){
//        super(docID);
//    }
    
    public TextElement(Document document, int position){
        super(document,position);
        System.out.println("\nText Element Created\n");
    }
    
    @Override
    public void addDocumentElement() {
        
    }

    @Override
    public void deleteDocumentElement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateDocumentElement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void displayElement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
