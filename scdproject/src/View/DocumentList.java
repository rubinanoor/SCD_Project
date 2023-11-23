package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentList {
    private JFrame frame;
    private JList<String> documentList;
    private JButton editButton;

    public DocumentList() {
        frame = new JFrame("Document List");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample document list, replace it with data from your DocumentManager
        String[] documents = {"Document 1", "Document 2", "Document 3"};
        documentList = new JList<>(documents);

        editButton = new JButton("Edit Document");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDocumentEditor(documentList.getSelectedValue());
            }
        });

        JPanel panel = new JPanel();
        panel.add(documentList);
        panel.add(editButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void openDocumentEditor(String selectedDocument) {
        frame.dispose(); // Close the document list window

        // Pass the selected document to the DocumentEditorUI
        new DocumentEditor(selectedDocument);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DocumentList();
            }
        });
    }
}
