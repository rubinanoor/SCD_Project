package GUI;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import BusinessLayer.*;
import java.util.HashMap;

public class DocumentPage {
    private JFrame mainframe;
    private JTextPane textPane;
    private BusinessLayer.Document document;
    private AttributeSet lastStyle = null;
    private boolean saved = false;
    private DocumentElement docElement = null;
    private int currentPosition = -1;
    private HashMap<DocumentElement, Range> elementRanges = new HashMap<>();
    private User user = null;
    
    public void setDocument(BusinessLayer.Document doc, User user) {
        this.user = user;
        this.document = doc;
        this.document.setOwner(this.user);
    }
    
    public int incrementPosition(){
        currentPosition++;
        return currentPosition;
    }
    

    public DocumentPage() {
        mainframe = new JFrame();
        textPane = new JTextPane();
        lastStyle = textPane.getStyledDocument().getCharacterElement(0).getAttributes();

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
                    saveDocument();
                    mainframe.dispose();
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
            AttributeSet currentStyle = doc.getCharacterElement(selectionStart).getAttributes();
            docElement.setStyle(currentStyle);

            // Update the range for the new element
            elementRanges.put(docElement, new Range(selectionStart, selectionEnd));

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
          
        textPane.addKeyListener(new KeyAdapter() {
        @Override
       public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                int caretPosition = textPane.getCaretPosition();

                javax.swing.text.Document styledDocument = textPane.getStyledDocument();
                Element paragraphElement = ((StyledDocument) styledDocument).getParagraphElement(caretPosition);
                AttributeSet currentStyle = paragraphElement.getAttributes();

                if (docElement == null || !styleEquals(lastStyle, currentStyle)) {
                    docElement = new TextElement(document, incrementPosition());
                    document.insertElement(docElement);
                    lastStyle = currentStyle;
                    docElement.setStyle(currentStyle);

                    // Store the range of positions for the new element
                    elementRanges.put(docElement, new Range(caretPosition, caretPosition));
                } else {
                    // Update the end position for the existing element
                    Range range = elementRanges.get(docElement);
                    range.setEnd(caretPosition);
                }

                System.out.println("CON: "+docElement.getContent());
                docElement.setContent(docElement.getContent() + typedChar);
            }
        });
    }
    
    private boolean styleEquals(AttributeSet style1, AttributeSet style2) {
        return style1.equals(style2);
    }

    private void addSaveButton() {
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveDocument();
        }
    });

    // Add the Save button to the bottom of the mainPanel
    mainframe.add(saveButton, BorderLayout.SOUTH);
}

    private void saveDocument() {
        
//    for (DocumentElement element : elementRanges.keySet()) {
//        Range range = elementRanges.get(element);
//        if (document.getDocumentElements().contains(element)) {
//            element.setRange(range.getStart(), range.getEnd());
//        }
//    }
//    if(document.saveDocument()){
//        JOptionPane.showMessageDialog(mainframe, "Document saved successfully.", "Save", JOptionPane.INFORMATION_MESSAGE);
//    }
    
    for (DocumentElement element : elementRanges.keySet()) {
        Range range = elementRanges.get(element);
        if (document.getDocumentElements().contains(element)) {
            element.setRange(range.getStart(), range.getEnd());
        }
    }

    // Assuming you want to print the content and styling to the console.
    System.out.println("Full Text Content:");

    StyledDocument styledDoc = textPane.getStyledDocument();
    ElementIterator iterator = new ElementIterator(styledDoc);
    Element element;

    while ((element = iterator.next()) != null) {
        int start = element.getStartOffset();
        int end = element.getEndOffset();

        try {
            String content = styledDoc.getText(start, end - start);
            AttributeSet attributes = element.getAttributes();
            System.out.println("Content: " + content);
            System.out.println("Styling: " + attributes.toString());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    if (document.saveDocument()) {
        JOptionPane.showMessageDialog(mainframe, "Document saved successfully.", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

}

    private void setFontSize(int size) {
        MutableAttributeSet attrs = textPane.getInputAttributes();
        StyleConstants.setFontSize(attrs, size);
        textPane.setCharacterAttributes(attrs, false);
        if (docElement != null) {
        docElement.setStyle(attrs);
        }
    }

    private void setFontName(String fontName) {
        MutableAttributeSet attrs = textPane.getInputAttributes();
        StyleConstants.setFontFamily(attrs, fontName);
        textPane.setCharacterAttributes(attrs, false);
        if (docElement != null) {
        docElement.setStyle(attrs);
        }
    }

    private void setBold() {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();

        Style style = textPane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);

        doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, style, false);
        
        if (docElement != null) {
            Element charElement = doc.getCharacterElement(selectionStart);
            AttributeSet attributes = charElement.getAttributes();
            docElement.setStyle(attributes);
        }
    }

    private void setItalic() {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();

        Style style = textPane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);

        doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, style, false);
        if (docElement != null) {
            Element charElement = doc.getCharacterElement(selectionStart);
            AttributeSet attributes = charElement.getAttributes();
            docElement.setStyle(attributes);
        }
    }

    private void chooseColor() {
        Color selectedColor = JColorChooser.showDialog(mainframe, "Choose Text Color", Color.BLACK);
        if (selectedColor != null) {
            MutableAttributeSet attrs = textPane.getInputAttributes();
            StyleConstants.setForeground(attrs, selectedColor);
            textPane.setCharacterAttributes(attrs, false);
            
            if (docElement != null) {
                docElement.setStyle(attrs);
            }

        }
    }

    private void addBulletPoints() {
    StyledDocument doc = textPane.getStyledDocument();
    int selectionStart = textPane.getSelectionStart();
    int selectionEnd = textPane.getSelectionEnd();

    // Insert a bullet point at the beginning of the line
    try {
        doc.insertString(selectionStart, "\u2022 ", null); // Unicode for bullet point
        docElement = new TextElement(document, incrementPosition());
        document.insertElement(docElement);
        AttributeSet currentStyle = doc.getCharacterElement(selectionStart).getAttributes();
        docElement.setStyle(currentStyle);
        docElement.setContent("\u2022 ");

        // Update the range for the new element
        elementRanges.put(docElement, new Range(selectionStart, selectionStart + 2));

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
                        docElement = new ImageElement(document, incrementPosition());
                        document.insertElement(docElement);

                        ImageElement imageElement = (ImageElement) docElement;
                        imageElement.setSize(new Dimension(width, height));
                        imageElement.setImageDataFromFile(file.toPath());

                        // Store the range of positions for the new element
                        int caretPosition = textPane.getCaretPosition();
                        elementRanges.put(docElement, new Range(caretPosition, caretPosition));

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
    private void deleteElement() {
        int caretPosition = textPane.getCaretPosition();
        for (DocumentElement element : elementRanges.keySet()) {
            Range range = elementRanges.get(element);
            if (caretPosition >= range.getStart() && caretPosition <= range.getEnd()) {
                // Remove the element from the document
                document.deleteElement(element);
                // Remove the range entry from the HashMap
                elementRanges.remove(element);
                break;
            }
        }
    }
    
    private static class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }


}
