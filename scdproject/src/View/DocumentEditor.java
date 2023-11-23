package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentEditor {
    private JFrame frame;
    private JTextArea textArea;
    private JButton saveButton;
    private String documentName;

    public DocumentEditor(String documentName) {
        this.documentName = documentName;

        frame = new JFrame("Document Editor - " + documentName);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDocument(documentName, textArea.getText());
            }
        });

        // Load existing content if the document already exists
        String existingContent = DocumentManager.getInstance().getDocumentContent(documentName);
        textArea.setText(existingContent);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void saveDocument(String documentName, String content) {
        DocumentManager.getInstance().saveDocument(documentName, content);

        // Close the editor window after saving
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DocumentEditor("New Document");
            }
        });
    }
}

