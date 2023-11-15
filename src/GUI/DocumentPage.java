package GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import BusinessLayer.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.StyleConstants;


public class DocumentPage {
    private JFrame mainframe;
    private JTextPane textPane;
    private BusinessLayer.Document document;
    private User user = null;
    private boolean saved = false;
    
    //database things
    //word count
    //line count
    //last modified date    

    public void setDocument(BusinessLayer.Document doc, User user) {
        this.user = user;
        this.document = doc;
        this.document.setOwner(this.user);
    }

    public DocumentPage() {
        //temp
//        this.user = new User();
//        this.document = new BusinessLayer.Document();
//        this.document.setOwner(this.user);

        mainframe = new JFrame();
        textPane = new JTextPane();
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(toolBar.getPreferredSize().width, 50));
        addFormattingButtons(toolBar);
        addSaveButton();
        
        toolBar.setBackground(new Color(0, 102, 102));
        mainframe.add(toolBar, BorderLayout.NORTH);
        mainframe.add(new JScrollPane(textPane), BorderLayout.CENTER);

        mainframe.setTitle("Document Editor");
        mainframe.setSize(1000, 600);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLocationRelativeTo(null);
        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            //problem Here closes it no matter what option you select
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(mainframe, "Do you want to save changes before closing?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if(saveDocument()){
                       mainframe.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(mainframe, "Unable to save the document.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (option == JOptionPane.NO_OPTION) {
                    mainframe.dispose();
                }
            }
        });
        mainframe.setVisible(true);
    }

    private void addFormattingButtons(JToolBar toolBar) {
        JButton setBackgroundColorButton = new JButton("Set Background Color");
        setBackgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(mainframe, "Choose Background Color", textPane.getBackground());
                if (selectedColor != null) {
                    textPane.setBackground(selectedColor);
                    if (document != null) {
                        document.setColor(selectedColor);
                    }
                }
            }
        });
        toolBar.addSeparator(new Dimension(5, 0));
        toolBar.add(setBackgroundColorButton);

        String[] fontSizes = {"12", "14", "16", "18", "20"};
        JComboBox<String> fontSizeComboBox = new JComboBox<>(fontSizes);
        fontSizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(Integer.parseInt((String) fontSizeComboBox.getSelectedItem()));
            }
        });
        fontSizeComboBox.setMaximumSize(new Dimension(60, 30));
        toolBar.add(new JLabel("Font Size: "));
        toolBar.add(fontSizeComboBox);
        toolBar.addSeparator(new Dimension(5, 0));

        // Font ComboBox
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox<String> fontComboBox = new JComboBox<>(fontNames);
        fontComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontName((String) fontComboBox.getSelectedItem());
            }
        });
        fontComboBox.setMaximumSize(new Dimension(150, 30));
        toolBar.add(new JLabel("Font: "));
        toolBar.add(fontComboBox);
        toolBar.addSeparator(new Dimension(5, 0));

        // Bold Button
        JButton boldButton = new JButton("Bold");
        boldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBold();
            }
        });
        boldButton.setMaximumSize(new Dimension(80, 30));
        toolBar.add(boldButton);
        toolBar.addSeparator(new Dimension(5, 0));

        // Italic Button
        JButton italicButton = new JButton("Italic");
        italicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItalic();
            }
        });
        italicButton.setMaximumSize(new Dimension(80, 30));
        toolBar.add(italicButton);
        toolBar.addSeparator(new Dimension(5, 0));

        // Color Button
        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor();
            }
        });
        colorButton.setMaximumSize(new Dimension(80, 30));
        toolBar.add(colorButton);
        toolBar.addSeparator(new Dimension(5, 0));

        String[] headingTypes = {"Heading 1", "Heading 2", "Heading 3"}; 
        for (String headingType : headingTypes) {
        JButton headingButton = new JButton(headingType);
        headingButton.addActionListener(new ActionListener() {
        @Override
       public void actionPerformed(ActionEvent e) {
            StyledDocument doc = textPane.getStyledDocument();
            int selectionStart = textPane.getSelectionStart();
            int selectionEnd = textPane.getSelectionEnd();

            Style style = textPane.addStyle(headingType, null);

            if (headingType.equals("Heading 1")) {
                StyleConstants.setFontSize(style, 36);
            } else if (headingType.equals("Heading 2")) {
                StyleConstants.setFontSize(style, 24);
            } else if (headingType.equals("Heading 3")) {
                StyleConstants.setFontSize(style, 18);
            }

            StyleConstants.setBold(style, true);

            doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, style, false);
        }
    });
        headingButton.setMaximumSize(new Dimension(120, 30));
        toolBar.add(headingButton);
}
        toolBar.addSeparator(new Dimension(5, 0));

        JButton bulletPointsButton = new JButton("Bullet Points");
        bulletPointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBulletPoints();
            }
        });
        
        bulletPointsButton.setMaximumSize(new Dimension(120, 30));
        toolBar.add(bulletPointsButton);
        toolBar.addSeparator(new Dimension(5, 0));

        JButton imageButton = new JButton("Insert Image");
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertImage();
            }
        });
        imageButton.setMaximumSize(new Dimension(120, 30));
        toolBar.add(imageButton);

        JButton tableButton = new JButton("Insert Table");
        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTable();
            }
        });
        tableButton.setMaximumSize(new Dimension(120, 30));
        toolBar.add(tableButton);
        toolBar.addSeparator(new Dimension(5, 0));
    }
    
    private boolean saveDocument(){
        List<DocumentElement> elements = new ArrayList<>();

        StyledDocument styledDoc = textPane.getStyledDocument();
        Element rootElement = styledDoc.getDefaultRootElement();
        
//        System.out.println("RootElement: " + rootElement);
//        System.out.println("RootElement Name: " + rootElement.getName());
//        System.out.println("RootElement Document: " + rootElement.getDocument());
//        System.out.println("RootElement AttributeSet: " + rootElement.getAttributes());
//        
            for (int i = 0; i < rootElement.getElementCount(); i++) {
                Element element = rootElement.getElement(i);
                int start = element.getStartOffset();
                int end = element.getEndOffset();
                String content = "";

                try {
                    content = styledDoc.getText(start, end - start);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainframe, "Fatel Error Occured", "Error", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Element Content AT DocumentPAGE: " + content);

//                System.out.println(element.getName());
//                System.out.println(element.getClass());
//                System.out.println(AbstractDocument.ContentElementName);
//                System.out.println(element.getAttributes().getAttribute(StyleConstants.NameAttribute));
//                System.out.println("\n\nStyledDocument: " + elements.size());
                
                if (isBulletPoint(content)) {
                  DocumentElement bulletElement = new TextElement(document);
                  bulletElement.setContent("\u2022 " + content.substring(2));
                  bulletElement.setRange(start, end);
                  bulletElement.setStyle(element.getAttributes());
                  document.insertElement(bulletElement);
                  //elements.add(bulletElement);
                  System.out.println("Bullet Element Added");
              } else {
                  DocumentElement textElement = new TextElement(document);
                  textElement.setContent(content);
                  textElement.setRange(start, end);
                  textElement.setStyle(element.getAttributes());
                  document.insertElement(textElement);
                  //elements.add(textElement);
                  System.out.println("Text Element Added");
              }
            }
            //document.setDocumentElements(elements);
            //document.printDocumentInfo();
            //color
            //last modified date
            //document.setLastModifiedDate();
            
            //line count
            //Add function to get line count
            document.setLineCount(0);
            
            //word count
            //Add function to get word count
            document.setWordCount(0);
            
            //favourite
            //Add function to set Favourite
            document.setFavourite(false);
            
            if(document.saveDocument()){
                //then dispose
                saved = true;
                return true;
            }
            
        return false;
    }
    private void addSaveButton() {
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveDocument();  
        }
    });
    mainframe.add(saveButton, BorderLayout.SOUTH);
}
    // Check the type of the element and assign the appropriate name
//    if (StyleConstants.NameAttribute.equals(element.getAttributes().getAttribute(StyleConstants.NameAttribute))) {
//        String textContent = "";
//        try {
//            textContent = styledDoc.getText(start, end - start);
//
//            if (isBulletPoint(textContent)) {
//                TextElement bulletElement = new TextElement(document);
//                bulletElement.setContent("\u2022 " + textContent.substring(2));
//                bulletElement.setRange(start, end);
//                bulletElement.setStyle(element.getAttributes());
//                elements.add(bulletElement);
//                System.out.println("Bullet Element Added");
//            } else {
//                TextElement textElement = new TextElement(document);
//                textElement.setContent(textContent);
//                textElement.setRange(start, end);
//                textElement.setStyle(element.getAttributes());
//                elements.add(textElement);
//                System.out.println("Text Element Added");
//            }
//        } catch (BadLocationException ex) {
//            ex.printStackTrace();
//        }
//    } else if (element.getName().equals(StyleConstants.ComponentElementName)) {
//        // Handle ComponentElement (Image)
//        ImageElement imageElement = createImageElement(styledDoc, element);
//        elements.add(imageElement);
//        System.out.println("Image Element Added");
//    }
//}


        // Print information about the extracted text and elements
      
     //   System.out.println("Content: " + content);

//        for (DocumentElement element : elements) {
//            element.displayDocumentElement();
//        }

        
        
        
//        traverseElements(rootElement, styledDoc, elements);
//
//          for (DocumentElement element : elements) {
//            System.out.println("\nElement:");
//            System.out.println("Text: " + element.getText());
//            System.out.println("Start Position: " + element.getStart());
//            System.out.println("End Position: " + element.getEnd());
//            System.out.println("Style Information:");
//            System.out.println("  Font Family: " + element.getFontFamily());
//            System.out.println("  Font Size: " + element.getFontSize());
//            System.out.println("  Text Color: " + element.getTextColor());
//            System.out.println("  Bold: " + element.isBold());
//            System.out.println("  Italic: " + element.isItalic());
//        }
        // Print information about the extracted text
//        System.out.println("\n\nStyledDocument: ");
//        System.out.println("Content: " + getTextFromDocument(styledDoc));
//        System.out.println(styledDoc.getText(0, 0));
//        
//        System.out.println("Document content: " + getTextFromDocument(plainDoc));
//        System.out.println("Root Element Name: " + rootElement.getName());
         //   System.out.println("HH: "+ textPane.get);
          /*
    StyledDocument doc = textPane.getStyledDocument();
  // StyledDocument doc = textPane.getDocument();
    Element root = doc.getDefaultRootElement();

    
    for (int i = 0; i < root.getElementCount(); i++) {
        Element element = root.getElement(i);
        AttributeSet attrs = element.getAttributes();
        int start = element.getStartOffset();
        int end = element.getEndOffset();
//              try {
//                  System.out.print(+"\n\n");
                  System.out.println(element.getDocument().getRootElements());//.getText(start, end - start));
                  Object ele  = element.getDocument().getClass();
                  
                  System.out.println(ele);
//                  
                  
                  */
                  
                 //Check the type of the element and assign the appropriate name

//        Document document = element.getDocument();

    /*             
       if (element.getDocument() != null) {
           switch (elementName) {
               case AbstractDocument.ContentElementName:
                   String textContent = "";
               try {
                   textContent = getTextContent(doc, element);
               } catch (BadLocationException ex) {
                   Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
               }
                   if (isBulletPoint(textContent)) {
                       TextElement bulletElement = new TextElement(document);
                       bulletElement.setContent("\u2022 " + textContent.substring(2));
                       bulletElement.setRange(start,end);
                       bulletElement.setStyle(attrs);
                      // setStyleInfo(bulletElement, attrs);
                       elements.add(bulletElement);
                       System.out.print("ELEMENT ADDED\n\n");
                   } else {
                       TextElement textElement = new TextElement(document);
                       textElement.setContent(textContent);
                       textElement.setRange(start,end);
                       textElement.setStyle(attrs);
                       //setStyleInfo(textElement, attrs);
                       elements.add(textElement);
                       System.out.print("ELEMENT ADDED\n\n");
                   }
                   break;

               case StyleConstants.ComponentElementName:
                   ImageElement imageElement = createImageElement(doc, element);
                   elements.add(imageElement);
                   break;

               default:
                   break;
           }
        }
//             }element catch (BadLocationException ex) {
//                  Logger.getLogger(DocumentPage.class.getName()).log(Level.SEVERE, null, ex);
//              }
      */  
    
   


    private String getTextFromDocument(javax.swing.text.Document doc) {
        try {
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            return "";
        }
    }

private boolean isBulletPoint(String textContent) {
    return textContent.startsWith("\u2022 ");
}

  
private String getTextContent(StyledDocument doc, Element element) throws BadLocationException {
    int start = element.getStartOffset();
    int end = element.getEndOffset();
    return doc.getText(start, end - start);
}


private ImageElement createImageElement(StyledDocument doc, Element element) {
    ImageElement imageElement = new ImageElement(document);

    AttributeSet attrs = element.getAttributes();
    Element imageElementStart = doc.getCharacterElement(element.getStartOffset());
    Element imageElementEnd = doc.getCharacterElement(element.getEndOffset() - 1);
    int imageStartOffset = imageElementStart.getStartOffset();
    int imageEndOffset = imageElementEnd.getEndOffset();

    // Set the content of the image element (you might want to get the image data from the file)
    imageElement.setContent("Image Content");

    // Set the style information
    imageElement.setStyle(attrs);

    // Retrieve the ImageIcon from the element
    Object imageIconObj = StyleConstants.getComponent(attrs);
    if (imageIconObj instanceof ImageIcon) {
        ImageIcon imageIcon = (ImageIcon) imageIconObj;

        // Get the width and height of the image
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();

        // Set the size information
        imageElement.setSize(new Dimension(imageWidth, imageHeight));
    }

    return imageElement;
}

    private void setFontSize(int size) {
        MutableAttributeSet attrs = textPane.getInputAttributes();
        StyleConstants.setFontSize(attrs, size);
        textPane.setCharacterAttributes(attrs, false);
    }

    private void setFontName(String fontName) {
        MutableAttributeSet attrs = textPane.getInputAttributes();
        StyleConstants.setFontFamily(attrs, fontName);
    }

    private void setBold() {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();

        Style style = textPane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);

        doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, style, false);
    }

    private void setItalic() {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();

        Style style = textPane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);

        doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, style, false);
    }

    private void chooseColor() {
        Color selectedColor = JColorChooser.showDialog(mainframe, "Choose Text Color", Color.BLACK);
        if (selectedColor != null) {
            MutableAttributeSet attrs = textPane.getInputAttributes();
            StyleConstants.setForeground(attrs, selectedColor);
            textPane.setCharacterAttributes(attrs, false);
            
//            if (docElement != null) {
//                docElement.setStyle(attrs);
//            }

        }
    }

    private void addBulletPoints() {
    StyledDocument doc = textPane.getStyledDocument();
    int selectionStart = textPane.getSelectionStart();
    int selectionEnd = textPane.getSelectionEnd();

    // Insert a bullet point at the beginning of the line
    try {
        doc.insertString(selectionStart, "\u2022 ", null); // Unicode for bullet point
      //  docElement = new TextElement(document, incrementPosition());
      //  document.insertElement(docElement);
        AttributeSet currentStyle = doc.getCharacterElement(selectionStart).getAttributes();
      //  docElement.setStyle(currentStyle);
        //docElement.setContent("\u2022 ");

        // Update the range for the new element
        //elementRanges.put(docElement, new Range(selectionStart, selectionStart + 2));

    } catch (BadLocationException e) {
        e.printStackTrace();
    }
}

    private void insertImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(mainframe);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.isFile()) {
                // Create a dialog to get the desired size from the user
                String sizeInput = JOptionPane.showInputDialog(this, "Enter image size (e.g., 100x100):");
                if (sizeInput != null && !sizeInput.isEmpty()) {
                    try {
                        String[] sizeArray = sizeInput.split("x");
                        int width = Integer.parseInt(sizeArray[0]);
                        int height = Integer.parseInt(sizeArray[1]);

                        ImageIcon imageIcon = new ImageIcon(file.getPath());
                        Image img = imageIcon.getImage();
                        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        // Create an ImageIcon and insert it into the document
                        ImageIcon scaledIcon = new ImageIcon(img);
                        textPane.insertIcon(scaledIcon);

                        // Create and store the ImageElement
                       // docElement = new ImageElement(document, incrementPosition());
                        //document.insertElement(docElement);

                       // ImageElement imageElement = (ImageElement) docElement;
                       // imageElement.setSize(new Dimension(width, height));
                        //imageElement.setImageDataFromFile(file.toPath());

                        // Store the range of positions for the new element
                       // int caretPosition = textPane.getCaretPosition();
                     //   elementRanges.put(docElement, new Range(caretPosition, caretPosition));

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(mainframe, "Invalid size format. Please use the format 'widthxheight'.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void addTable() {
        // Implement table insertion logic here
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DocumentPage();
            }
        });
    }

}
