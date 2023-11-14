package GUI;
import javax.swing.*;
import BusinessLayer.*;

public class SignUpPage extends javax.swing.JFrame {
 
    private javax.swing.JLabel AppName1;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel SideFrame;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel Username;
    private javax.swing.JLabel Password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private User user = null;
    
    public SignUpPage() {
        initComponents();
    }
    
    public void setUser(User user){
        if(this.user == null){
           this.user = user;
           System.out.println(this.user.getUsername());
        }
    }
    private void initComponents() {
        User newuser = new User();
        this.setUser(newuser);
        SideFrame = new javax.swing.JPanel();
        AppName1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Password = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        SignUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(570, 500));

        SideFrame.setBackground(new java.awt.Color(0, 102, 102));
        SideFrame.setPreferredSize(new java.awt.Dimension(200, 110));

        AppName1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        AppName1.setForeground(new java.awt.Color(255, 255, 255));
        AppName1.setText("SmartScript");

        javax.swing.GroupLayout SideFrameLayout = new javax.swing.GroupLayout(SideFrame);
        SideFrame.setLayout(SideFrameLayout);
        SideFrameLayout.setHorizontalGroup(
            SideFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideFrameLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(AppName1)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        SideFrameLayout.setVerticalGroup(
            SideFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideFrameLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(AppName1)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Already have an account?");

        LoginButton.setBackground(new java.awt.Color(242, 240, 240));
        LoginButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        LoginButton.setForeground(new java.awt.Color(0, 102, 102));
        LoginButton.setText("Login");
        
        LoginButton.setBorder(null);
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                LoginPage loginpage = new LoginPage();
                System.out.println(user.getUsername());
                loginpage.setUser(user);
                loginpage.setVisible(true);
            }
        });

        Username.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        Username.setForeground(new java.awt.Color(0, 102, 102));
        Username.setText("Username:");

        Password.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        Password.setForeground(new java.awt.Color(0, 102, 102));
        Password.setText("Password:");

        SignUpButton.setBackground(new java.awt.Color(0, 102, 102));
        SignUpButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        SignUpButton.setForeground(new java.awt.Color(255, 255, 255));
        SignUpButton.setText("SignUp");
        SignUpButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        SignUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean signupSuccess = user.signup(jTextField2.getText(), jTextField1.getText().toCharArray());
                if (signupSuccess) {
                    dispose();
                    HomePage homepage = new HomePage();
                    homepage.setUser(user);
                    homepage.setVisible(true);
                } else {
                   JOptionPane.showMessageDialog(null, "SignUp Failed. Enter A Unique Username.", "SignUp Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Username)
                                        .addComponent(Password)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(114, 114, 114))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SideFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(Username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(SignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                     
    
    public static void main(String args[]) {
        new SignUpPage().setVisible(true);
    }
          
}
