package BusinessLayer;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageElement extends DocumentElement {
    private byte[] imageData;
    private Dimension size;

    public ImageElement(Document doc) {
        super(doc);
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Dimension getSize() {
        return size;
    }

    @Override
    public void addDocumentElement() {
        // Implement logic to add the image element to the document
        // You can use imageData and size to insert the image into the document
    }

    @Override
    public void deleteDocumentElement() {
        // Implement logic to delete the image element from the document
    }

    @Override
    public void updateDocumentElement() {
        // Implement logic to update the image element in the document
    }

    @Override
    public void displayElement() {
        // Implement logic to display information about the image element
    }

    public void setFilePath(String filePath) {
        try {
            this.imageData = Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }
    
    public void setImageDataFromFile(Path filePath){
        try{
            this.imageData = Files.readAllBytes(filePath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
