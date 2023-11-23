package models;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public class Styleandtheme {

    private Map<String, Font> textFonts;
    private Map<String, Color> textColors;
    private Color documentColor;

    public Styleandtheme() {
        this.textFonts = new HashMap<>();
        this.textColors = new HashMap<>();
        this.documentColor = Color.WHITE; // Default document color
    }

    public void setTextFont(String styleName, Font font) {
        textFonts.put(styleName, font);
    }

    public Font getTextFont(String styleName) {
        return textFonts.getOrDefault(styleName, new Font("Arial", Font.PLAIN, 12)); // Default font if style not found
    }

    public void setTextColor(String styleName, Color color) {
        textColors.put(styleName, color);
    }

    public Color getTextColor(String styleName) {
        return textColors.getOrDefault(styleName, Color.BLACK); // Default to black if style not found
    }

    public void setDocumentColor(Color color) {
        this.documentColor = color;
    }

    public Color getDocumentColor() {
        return documentColor;
    }

    // Additional methods for style and theme management can be added here

    // Example method for applying styles to text
    public void applyStyleToText(String text, String styleName) {
        Font font = getTextFont(styleName);
        Color color = getTextColor(styleName);

        // Apply the style to the text (e.g., set font and color)
        // This is a simplified example; you may need a more complex rendering mechanism
        System.out.println("Applying style: Font=" + font + ", Color=" + color + " to text: " + text);
    }
    public static void main(String[] args) {
        // Create an instance of Styleandtheme
        Styleandtheme styleandtheme = new Styleandtheme();

        // Set text font and color for a style
        styleandtheme.setTextFont("Heading1", new Font("Arial", Font.BOLD, 20));
        styleandtheme.setTextColor("Heading1", Color.BLUE);

        // Retrieve text font and color for a style
        Font headingFont = styleandtheme.getTextFont("Heading1");
        Color headingColor = styleandtheme.getTextColor("Heading1");

        System.out.println("Heading Font: " + headingFont);
        System.out.println("Heading Color: " + headingColor);

        // Apply style to text (for demonstration purposes)
        styleandtheme.applyStyleToText("This is a heading", "Heading1");
    }
}
