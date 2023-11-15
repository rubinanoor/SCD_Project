package GUI;

import javax.swing.*;
import BusinessLayer.*;

public class LoginPage extends JFrame {
    private User user = null;
    private JLabel AppName;
    private JButton LoginButton;
    private JPanel SideFrame;
    private JButton SignupButton;
    private JLabel Username;
    private JLabel Password;
    private JLabel jLabel1;
    private JSeparator jSeparator1;
    private JTextField jTextField1;
    private JTextField jTextField2;

    public LoginPage() {
        initComponents();
    }
    public void setUser(User user){
        if(this.user == null){
           this.user = user;
        }
    }

    private void initComponents() {
        this.setUser(new User());
        SideFrame = new javax.swing.JPanel();
        AppName = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Password = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        LoginButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        SignupButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SideFrame.setBackground(new java.awt.Color(0, 102, 102));
        SideFrame.setPreferredSize(new java.awt.Dimension(200, 110));

        AppName.setFont(new java.awt.Font("Segoe UI", 1, 24));
        AppName.setForeground(new java.awt.Color(255, 255, 255));
        AppName.setText("SmartScript");

        javax.swing.GroupLayout SideFrameLayout = new javax.swing.GroupLayout(SideFrame);
        SideFrame.setLayout(SideFrameLayout);
        SideFrameLayout.setHorizontalGroup(
                SideFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SideFrameLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(AppName)
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        SideFrameLayout.setVerticalGroup(
                SideFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SideFrameLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(AppName)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Username.setFont(new java.awt.Font("Segoe UI", 1, 18));
        Username.setForeground(new java.awt.Color(0, 102, 102));
        Username.setText("Username:");
        
        Password.setFont(new java.awt.Font("Segoe UI", 1, 18));
        Password.setForeground(new java.awt.Color(0, 102, 102));
        Password.setText("Password:");

        LoginButton.setBackground(new java.awt.Color(0, 102, 102));
        LoginButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("Login");
        LoginButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean loginSuccess = user.login(jTextField2.getText(), jTextField1.getText().toCharArray());
                if (loginSuccess) {
                    dispose();
                    HomePage homepage = new HomePage();
                    homepage.setUser(user);
                    homepage.setVisible(true);
                } else {
                   JOptionPane.showMessageDialog(null, "Login Failed. Please check your credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         
        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Don't have an account?");

        SignupButton.setBackground(new java.awt.Color(242, 240, 240));
        SignupButton.setFont(new java.awt.Font("Segoe UI", 1, 18));
        SignupButton.setForeground(new java.awt.Color(0, 102, 102));
        SignupButton.setText("SignUp");
        SignupButton.setBorder(null);
        SignupButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SignupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpPage signuppage = new SignUpPage();
                signuppage.setUser(user);
                signuppage.setVisible(true); 
                dispose();
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SignupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 68, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(SideFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Username)
                                                        .addComponent(Password)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(SignupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(102, Short.MAX_VALUE))
                        .addComponent(SideFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
    }

}
