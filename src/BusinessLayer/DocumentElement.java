package BusinessLayer;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;

public abstract class DocumentElement {
    protected int position = -1;
    private AttributeSet style;
    private String content;
    private Document document;
    private int start;
    private int end;
    
    public DocumentElement(Document doc, int position){
        content="";
        this.position = position;
        document = doc;
    }
    
    public void setRange(int start, int end){
        this.start = start;
        this.end = end;
    }
        
    public void setDocument(Document doc){
        this.document = doc;
    }
    
    public Document getDocument(){
        return document;
    }
       
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        System.out.println("New Content is: " + content);
    }
    
    public AttributeSet getStyle() {
        return style;
    }

    public void setStyle(AttributeSet style) {
        this.style = style;
        displayDocumentElement();
    }
    
  
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public abstract void addDocumentElement();
    public abstract void deleteDocumentElement();
    public abstract void updateDocumentElement();
    public abstract void displayElement();
    
    public void displayDocumentElement() {
    System.out.println("Document Element Information:");
    System.out.println("Position: " + position);
    System.out.println("Document ID: " + document.getDocid());
    System.out.println("Content: " + content);

    if (style != null) {
        System.out.println("Style Information:");

        // Use a while loop to iterate over the Enumeration
        java.util.Enumeration<?> attributeNames = style.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            Object attribute = attributeNames.nextElement();

            // Handle special cases, e.g., StyleConstants
            if (attribute instanceof StyleConstants) {
                System.out.println(attribute + ": " + StyleConstants.getFontSize(style));
                // Add more cases as needed
            } else {
                // Handle other attributes
                System.out.println(attribute + ": " + style.getAttribute(attribute));
            }
        }
    } else {
        System.out.println("Style: null");
    }
}

}
