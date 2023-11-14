package GUI;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import BusinessLayer.*;
public class HomePage extends JFrame {

    User user = null;
    UserDocumentManager manager;

    private JPanel DocumentsPanel1;
    private JPanel FavouritesPanel;
    
    private JButton profile;
    private JButton newDocument;
    private JButton OpenDocument;
    private JButton viewFavDoc;
    private JButton viewAllDoc;
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    
    private JPanel sidepanel;
    private JPanel mainpanel;
    
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    public HomePage() {
        initComponents();       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setVisible(true);
    }

    public void setUser(User user){
        if(this.user == null){
           this.user = user;
           manager = new UserDocumentManager(user);
           //System.out.println(this.user.getUsername());
        }
        if(user == null){
            this.user = null;
        }
        
    }               
    private void initComponents() {
       
        sidepanel = new javax.swing.JPanel();
        mainpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        
        profile = new javax.swing.JButton();
        newDocument = new javax.swing.JButton();
        OpenDocument = new javax.swing.JButton();
        
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        
        viewFavDoc = new javax.swing.JButton();
        viewAllDoc = new javax.swing.JButton();
        FavouritesPanel = new javax.swing.JPanel();
        DocumentsPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidepanel.setBackground(new java.awt.Color(242, 240, 240));
        mainpanel.setBackground(new java.awt.Color(0, 102, 102));
       
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SmartScript");

        //Profile Button
        profile.setBackground(new java.awt.Color(0, 102, 102));
        profile.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setText("Profile");
        profile.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            
            }
        });
        
              
        newDocument.setBackground(new java.awt.Color(0, 102, 102));
        newDocument.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        newDocument.setForeground(new java.awt.Color(255, 255, 255));
        newDocument.setText("New");
        newDocument.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JFrame newDocumentFrame = new JFrame("New Document");
                System.out.println(user.getUsername());
               newDocumentFrame.setSize(400, 200);
               newDocumentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
               newDocumentFrame.setLocationRelativeTo(null); 

       
              JLabel nameLabel = new JLabel("Enter Document Name:");
              JTextField nameTextField = new JTextField(20);
              JButton createButton = new JButton("Create");
   
              newDocumentFrame.setLayout(new FlowLayout());

              newDocumentFrame.add(nameLabel);
              newDocumentFrame.add(nameTextField);
              newDocumentFrame.add(createButton);
              
                createButton.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                     String documentName = nameTextField.getText();
                     Document document = manager.addDocument(documentName);
                     if(document == null){
                       JOptionPane.showMessageDialog(null, "A Document with this name already exists", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                     else{
                         DocumentPage docpage = new DocumentPage();
                         docpage.setDocument(document, user);
                         newDocumentFrame.dispose();
                     }
                     
                   }
                });
            newDocumentFrame.setVisible(true);
           }

        });

        OpenDocument.setBackground(new java.awt.Color(0, 102, 102));
        OpenDocument.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        OpenDocument.setForeground(new java.awt.Color(255, 255, 255));
        OpenDocument.setText("Open");
        OpenDocument.addActionListener(new ActionListener(){
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                //open some Document
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(mainpanel);
        mainpanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(OpenDocument, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                    .addComponent(newDocument, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                    .addComponent(profile, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(profile, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(newDocument, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(OpenDocument, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jLabel2.setFont(new Font("Segoe UI", 1, 24));
        jLabel2.setForeground(new Color(0, 102, 102));
        jLabel2.setText("Your Documents");

        jLabel3.setFont(new Font("Segoe UI", 1, 24)); 
        jLabel3.setForeground(new Color(0, 102, 102));
        jLabel3.setText("Favourite Documents");

        jSeparator1.setForeground(new Color(0, 102, 102));

        jSeparator2.setForeground(new Color(0, 102, 102));

        
        //favourite documents button
        viewFavDoc.setBackground(new Color(0, 102, 102));
        viewFavDoc.setForeground(new Color(255, 255, 255));
        viewFavDoc.setText("View All");
        viewFavDoc.setActionCommand("ViewAllFavouriteDocuments");
        viewFavDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               //view All Favourite Documents
            }
        });

        viewAllDoc.setBackground(new Color(0, 102, 102));
        viewAllDoc.setForeground(new Color(255, 255, 255));
        viewAllDoc.setText("View All");
        viewAllDoc.setActionCommand("ViewAllDocuments");
        viewAllDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //view All Documents
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setUser(null);
            }
        });

        GroupLayout FavouritesPanelLayout = new GroupLayout(FavouritesPanel);
        FavouritesPanel.setLayout(FavouritesPanelLayout);
        FavouritesPanelLayout.setHorizontalGroup(
            FavouritesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        FavouritesPanelLayout.setVerticalGroup(
            FavouritesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        GroupLayout DocumentsPanel1Layout = new GroupLayout(DocumentsPanel1);
        DocumentsPanel1.setLayout(DocumentsPanel1Layout);
        DocumentsPanel1Layout.setHorizontalGroup(
            DocumentsPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DocumentsPanel1Layout.setVerticalGroup(
            DocumentsPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        GroupLayout jPanel1Layout = new GroupLayout(sidepanel);
        sidepanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(viewFavDoc))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewAllDoc))
                    .addComponent(FavouritesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DocumentsPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(viewAllDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DocumentsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(viewFavDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FavouritesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sidepanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(sidepanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }                      
          
}

