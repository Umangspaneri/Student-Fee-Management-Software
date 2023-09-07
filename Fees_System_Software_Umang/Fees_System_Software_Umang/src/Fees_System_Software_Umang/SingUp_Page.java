//record insert thaya pachi name aavu joye ke welcome umang aem
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fees_System_Software_Umang;

import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*; // Package
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingUp_Page extends javax.swing.JFrame {

    String fname,lname,uname,password,con_pass,contact_no; //Local Variable (Global variable)
    Date dob;
    
    public SingUp_Page()
    {
        initComponents();   
        //getId();
        
        //int id = getId();  //sem class ni Andar Object Vagar Call kari Sakay Method ne (Jo static hoy to Object thi call Thay baki call no kari sakay)
        //getId.setText(Integer.toString(getId));
    }
    /*public int getId()
    {
        ResultSet rs = null;
        int id = 0;
        try   
        {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_system","root","");
                String sql = "Select max(id) from signup_user";
                Statement st = con.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next())
                {
                      id++;
                    id = rs.getInt(1);
                }
                
        }
        catch (ClassNotFoundException | SQLException e)
        {
            
        }
        return id;
    }*/
    
        boolean validation()
        {               
            fname = txt_firstname.getText();
            lname = txt_lastname.getText();
            uname = txt_username.getText();
            password = txt_password.getText();
            con_pass = txt_con_password.getText();
            contact_no = txt_contact.getText();
            dob = txt_dob.getDate();
         
            /* aa comment validation ni che try thay jay pachi kadhvi
            if(fname.equals(""))
            {
                // First Name No Enter Kare To Error Mate
                JOptionPane.showMessageDialog(this,"Plaease Enter FirstName ");
                return false;
            }
            if(lname.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Plaease Enter LastName ");
                return false;
            }
            if(uname.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Plaease Enter UserName ");
                return false;
            }
            if(password.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Plaease Enter PassWord ");
                return false;
            }
            if(con_pass.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Plaease Enter Confirm Password ");
                return false;
            }
            if(dob == null)
            {
            
                JOptionPane.showMessageDialog(this,"Plaease Enter Date Of Birth  ");
                return false;
            }
            if(contact_no.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Plaese Enter Contact Nomber");
            }
            
            
            // Check To Matched Condition Password
            if(password.length()<8)
            {
                lbl_password_error.setText("PassWord Sould be Of 8 Digit");
            }
            if(!password.equals(con_pass))
            {
                JOptionPane.showMessageDialog(this,"PassWord Not Matched ");
                return false;
            }*/
            return true;
            
            
        }
        
        // Check Password 8 Digit
        /*
        public void checkPassword() //Method Creat
        {
            password = txt_password.getText();
            if(password.length()<8)
            {
                lbl_password_error.setText("PassWord Sould be Of 8 Digit");
            }
            else
            {
                lbl_password_error.setText("");
            }
        }
        
        //Check Contact Nomber 10 Digit
        /*
        public void checkContactNo() // Method Creat
        {
            contact_no = txt_contact.getText();
            if(contact_no.length()==10)
            {
                lbl_contact_error.setText(""); //Ture
            }
            else
                lbl_contact_error.setText("Contact No Should Be Of 10 Digits"); //False
        }*/
        
        void insertDetails()
        {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String myDob = format.format(dob);  //Variable
//            String className = "com.mysql.jdbc.Driver";
            try
            {
//             
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_system","root","");
                String sql = "insert into signup_user (firstname,lastname,username,password,dob,contact_no) values(?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                //stmt.setInt(1,getId());
                stmt.setString(1,fname);
                stmt.setString(2,lname);
                stmt.setString(3,uname);
                stmt.setString(4,password);
                stmt.setString(5,myDob);
                stmt.setString(6,contact_no);
                
                int i=stmt.executeUpdate();
                if(i > 0)
                {
                    JOptionPane.showMessageDialog(this, "Record insert...");
                    /*jaja data enter mate SingUp_Page si = new SingUp_Page();
                    si.show();*/
                    Login_Page login = new Login_Page();
                    login.show();
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Record Not Insert...");
                }
              
                        
            }
            catch (Exception e)
            {
                System.out.println(e); 
                e.getStackTrace();
            }
        }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_firstname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_lastname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txt_con_password = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txt_dob = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        btn_Login = new javax.swing.JButton();
        btn_SingUp = new javax.swing.JButton();
        lbl_password_error = new javax.swing.JLabel();
        lbl_contact_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SingUp");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 680));

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("SingUp");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name ");

        txt_firstname.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_firstname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_firstnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_firstnameFocusLost(evt);
            }
        });
        txt_firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_firstnameActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Last Name ");

        txt_lastname.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lastnameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("User Name : -");

        txt_username.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Password : -");

        txt_password.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_passwordKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Confirm Password : -");

        txt_con_password.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txt_con_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_con_passwordActionPerformed(evt);
            }
        });
        txt_con_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_con_passwordKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("D.O.B :-");

        txt_dob.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_dob.setMinimumSize(new java.awt.Dimension(81, 31));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Contact No :-");

        txt_contact.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        txt_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contactKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactKeyReleased(evt);
            }
        });

        btn_Login.setBackground(new java.awt.Color(171, 140, 17));
        btn_Login.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_Login.setText("Login");
        btn_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LoginMouseClicked(evt);
            }
        });
        btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });

        btn_SingUp.setBackground(new java.awt.Color(171, 140, 17));
        btn_SingUp.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_SingUp.setText("SingUp");
        btn_SingUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SingUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SingUpMouseEntered(evt);
            }
        });
        btn_SingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SingUpActionPerformed(evt);
            }
        });
        btn_SingUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_SingUpKeyPressed(evt);
            }
        });

        lbl_password_error.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_password_error.setForeground(new java.awt.Color(255, 0, 0));

        lbl_contact_error.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_contact_error.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(170, 170, 170))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_con_password)
                                    .addComponent(txt_dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_contact, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_username, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_lastname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_firstname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                            .addComponent(lbl_password_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_contact_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btn_SingUp)
                        .addGap(69, 69, 69)
                        .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_password_error, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_con_password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_contact_error, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Login)
                    .addComponent(btn_SingUp))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 490, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginActionPerformed
        Login_Page login = new Login_Page(); //Object
        login.show();
        this.dispose();
    }//GEN-LAST:event_btn_LoginActionPerformed

    private void btn_SingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SingUpActionPerformed
        // TODO add your handling code here:
        //SignUp button thi data insert mate 
        if(validation())
        {
           insertDetails(); 
        }
        
    }//GEN-LAST:event_btn_SingUpActionPerformed

    private void txt_lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lastnameActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_con_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_con_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_con_passwordActionPerformed

    private void txt_con_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_con_passwordKeyPressed
        // TODO add your handling code here:
        //checkPassword(); //Method Call
    }//GEN-LAST:event_txt_con_passwordKeyPressed

    private void txt_firstnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_firstnameFocusGained
        // TODO add your handling code here:
        Color clr = new Color(188,143,133); //Object
/* This work like PLACEHOLDEER         txt_firstname.setBackground(clr);
        if(txt_firstname.getText().equals(" "))
        {
            txt_firstname.setText(" Enter First Name ");
           txt_firstname.setForeground(new Color(153,140,153));
        }*/
    }//GEN-LAST:event_txt_firstnameFocusGained

    private void txt_firstnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_firstnameFocusLost
//        // TODO add your handlinqg code here:
Color clr = new Color(255,255,190); //Object
/* This work like PLACEHOLDEER         txt_firstname.setBackground(clr);
//        if(txt_firstname.getText().equals(""))
//        {
//            txt_firstname.setText("Enter First Name");
//           txt_firstname.setForeground(new Color(153,153,153));
        }*/
    }//GEN-LAST:event_txt_firstnameFocusLost

    private void txt_passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyReleased
        // TODO add your handling code here:
        //checkPassword(); // Method Call
    }//GEN-LAST:event_txt_passwordKeyReleased

    private void txt_contactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactKeyReleased
        // TODO add your handling code here:
        //checkContactNo(); //Method Call
    }//GEN-LAST:event_txt_contactKeyReleased

    private void txt_contactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactKeyPressed
        // TODO add your handling code here:
        //checkContactNo(); //Method Call
    }//GEN-LAST:event_txt_contactKeyPressed

    private void btn_SingUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SingUpMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_SingUpMouseEntered

    private void btn_SingUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_SingUpKeyPressed
        // TODO add your handling code here:
    if(validation())
        {
           insertDetails(); 
        }
    }//GEN-LAST:event_btn_SingUpKeyPressed

    private void txt_firstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_firstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_firstnameActionPerformed

    private void btn_SingUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SingUpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SingUpMouseClicked

    private void btn_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LoginMouseClicked
        Login_Page login = new Login_Page(); //Object
        login.show();
        this.dispose();
        
    }//GEN-LAST:event_btn_LoginMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SingUp_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SingUp_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SingUp_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SingUp_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SingUp_Page().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Login;
    private javax.swing.JButton btn_SingUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_contact_error;
    private javax.swing.JLabel lbl_password_error;
    private javax.swing.JPasswordField txt_con_password;
    private javax.swing.JTextField txt_contact;
    private com.toedter.calendar.JDateChooser txt_dob;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
