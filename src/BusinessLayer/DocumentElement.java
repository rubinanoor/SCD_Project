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

    // New data members for style information
    private String fontFamily;
    private int fontSize;
    private java.awt.Color fontColor;
    private boolean isBold;
    private boolean isItalic;

    public DocumentElement(Document doc) {
        content = "";
        document = doc;
    }

    // Getter and setter methods for the new data members
    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public java.awt.Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(java.awt.Color fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean isBold) {
        this.isBold = isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean isItalic) {
        this.isItalic = isItalic;
    }

    public void setRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setDocument(Document doc) {
        this.document = doc;
    }

    public Document getDocument() {
        return document;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
       // System.out.println("New Content is: " + content);
    }

    public AttributeSet getStyle() {
        return style;
    }

    public void setStyle(AttributeSet style) {
        this.style = style;
        setFontFamily(StyleConstants.getFontFamily(style));
        setFontSize(StyleConstants.getFontSize(style));
        setFontColor(StyleConstants.getForeground(style));
        setBold(StyleConstants.isBold(style));
        setItalic(StyleConstants.isItalic(style));
       // displayDocumentElement();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public int getStart(){
        return start;
    }
    
    public int getEnd(){
        return end;
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
        System.out.println("Style Information:");

        // Display the new style information
        System.out.println("Font Family: " + fontFamily);
        System.out.println("Font Size: " + fontSize);
        System.out.println("Font Color: " + fontColor);
        System.out.println("Is Bold: " + isBold);
        System.out.println("Is Italic: " + isItalic);
    }
}
