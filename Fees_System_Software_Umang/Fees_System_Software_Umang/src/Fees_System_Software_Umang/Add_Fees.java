/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fees_System_Software_Umang;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class Add_Fees extends javax.swing.JFrame {

    public Add_Fees() {
       initComponents();
       displayCashFirst();  // Call Counc
       fillComboBox();      //Call Method
       //aa niche nu text box edit no thay ae mate lakhyu che. jemke sgst ne koi edit na karvu joye
       txt_courseName.setEditable(false);
       txt_cgst.setEditable(false);
       txt_sgst.setEditable(false);
       txt_total.setEditable(false);
       txt_total_words.setEditable(false);
       txt_GSTNo.setEditable(false);//gst no. pan edit nay thay sake 
       
        
        int receiptNo = getReceiptNo();  //sem class ni Andar Object Vagar Call kari Sakay Method ne (Jo static hoy to Object thi call Thay baki call no kari sakay)
        txt_receiptNo.setText(Integer.toString(receiptNo));
    }
 public void displayCashFirst()  //Counc
    {
        lbl_DDNo.setVisible(false);
        lbl_ChequeNo.setVisible(false);
        lbl_bankName.setVisible(false);
        
        txt_DDNo.setVisible(false);
        txt_ChequeNo.setVisible(false);
        txt_bankName.setVisible(false);
        
    }
    
    public boolean validation()  //Method
    {
        if(txt_receivedFrom.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter User Name");  //Name Check
            return false; 
        }
        if(txt_rollNo.getText().equals(""))
        {
             JOptionPane.showMessageDialog(this,"Please Enter Roll No");  //Roll No Check
            return false; 
        }
        if(dateChooser.getDate() == null)
        {
            JOptionPane.showMessageDialog(this,"Please Select A Date");  //Date Check
            return false;
        }
        
        
        if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("cheque"))  // Cheque Check
        {
            if(txt_ChequeNo.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Cheque Number");
                return false;
            }
            if(txt_bankName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Bank Name");
                return false;
            }
        }    
            if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("dd"))  //DD Validation 
            {
//                if(txt_DDNo.getText().equals(""))
//                {
//                 JOptionPane.showMessageDialog(this,"Please Enter DD Number");
//                 return false;
//                }
                if(txt_DDNo.getText().equals(""))
                {
                 JOptionPane.showMessageDialog(this,"Please Enter DD Number");
                 return false;
                }
                if(txt_bankName.getText().equals(""))
                {
                JOptionPane.showMessageDialog(this,"Please Enter Bank Name");
                return false;
                }
            }        
            if(combo_PaymentMode.getSelectedItem().toString().equalsIgnoreCase("Card"))  //Card Validation
            {
                if(txt_bankName.getText().equals(""))
                {
                JOptionPane.showMessageDialog(this,"Please Enter Bank Name");
                return false;
                }
              
            }
            
            if(txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false)  //Amount Check
               {
            JOptionPane.showMessageDialog(this,"Please Enter Amount(in Number)");
            return false;
               }
            
        return true;
    }
    
    public void fillComboBox()  //Method 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_system","root","");
//            System.out.println(con.getClass().getName());
            PreparedStatement pst = con.prepareStatement("select cname from course");
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                comboCourse.addItem(rs.getString("cname"));
            }

        }
        catch(Exception e)
                {
                    e.printStackTrace();
//                    e.toString();
//                    System.out.println(e.getMessage());
                            
                }
    }
        
    public int getReceiptNo()
    {
        int receiptNo = 0; //Local Variable
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("Select max(reciept_no) from fees_details");
            ResultSet rs = pst.executeQuery();  //Je Result aave te if Statement Ma jay
            
            if(rs.next() == true)  //ResultSet Mathi Ave te
            {
                receiptNo = rs.getInt(1);
                //rs.getInt("reciept_no");
                
            }
        }
        catch(Exception e)
                {
                    e.printStackTrace();    
                }
        return receiptNo+1;
        
    }
    
    public String insertData()
    {
        String status = "";
        
        int recieptNo = Integer.parseInt(txt_receiptNo.getText());
        String studentName = txt_receivedFrom.getText();
        String rollNo = txt_rollNo.getText();
        String paymentMode = combo_PaymentMode.getSelectedItem().toString();
        String chequeNo = txt_ChequeNo.getText().trim();
        System.out.println("Cheque no: " + chequeNo);
        if(chequeNo.isEmpty()){
            chequeNo = null;
        }
        String bankName = txt_bankName.getText();
        String ddNo = txt_DDNo.getText().trim();
        System.out.println("Cheque no: " + ddNo);
        if(ddNo.isEmpty()){
            ddNo = null;
        }
        String courseName = jlabel.getText();
        String gstin = txt_GSTNo.getText();
        float totalAmount =Float.parseFloat(txt_total.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String Date = dateFormat.format(dateChooser.getDate());
        float initialAmount = Float.parseFloat(txt_amount.getText());
        float cgst =Float.parseFloat(txt_cgst.getText());
        float sgst =Float.parseFloat(txt_sgst.getText());
        String totalInWords = txt_total_words.getText();
        String remark = txt_remark.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        //String gstno="22hvsj55";
        try
        {
            Connection con = DBConnection.getConnection();
          //  PreparedStatement pst = con.prepareStatement("insert into fees_details (reciept_no,student_name,payment_mode,cheque_no,bank_name,dd_no,courses,gstin,total_amount,date,amount,cgst,sgst,total_in_words,remark,year1,year2,roll_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              PreparedStatement pst = con.prepareStatement("insert into fees_details(reciept_no,student_name,roll_no,payment_mode,cheque_no,bank_name,dd_no,courses,gstin,total_amount,date,amount,cgst,sgst,total_in_words,remark,year1,year2) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //pst.setInt(1,recieptNo);
            pst.setInt(1,recieptNo);
            pst.setString(2,studentName);
            pst.setString(3,rollNo);
            pst.setString(4, paymentMode);
            pst.setString(5,chequeNo);
            pst.setString(6, bankName);
            pst.setString(7, ddNo);
            pst.setString(8, courseName);
//            
            pst.setString(9, gstin);
            pst.setFloat(10, totalAmount);
            pst.setString(11, Date);
            pst.setFloat(12, initialAmount);
            pst.setFloat(13,cgst);
            pst.setFloat(14, sgst);
            pst.setString(15,totalInWords);
            pst.setString(16, remark);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
              
            
            //pst.executeUpdate();
            
            int rowCount = pst.executeUpdate();
            //pst.executeUpdate();
            if(rowCount == 1)
            {
                status = "Success";
               
            }
            else
            {
               status = "Failed";
                
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //System.out.println(e);
        }
          return status;
       
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelEdit = new javax.swing.JPanel();
        btnEdit = new javax.swing.JLabel();
        panelCourseList = new javax.swing.JPanel();
        btnCourseList = new javax.swing.JLabel();
        panelViewAllRecord = new javax.swing.JPanel();
        btnViewAllRecord = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnBack = new javax.swing.JLabel();
        panelSreach = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnLogout = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlabel11 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_receivedFrom = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        jlabel = new javax.swing.JLabel();
        comboCourse = new javax.swing.JComboBox<>();
        txt_amount = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        txt_courseName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_total_words = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbl_ChequeNo = new javax.swing.JLabel();
        txt_DDNo = new javax.swing.JTextField();
        lbl_DDNo = new javax.swing.JLabel();
        txt_bankName = new javax.swing.JTextField();
        lbl_bankName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txt_rollNo = new javax.swing.JTextField();
        txt_ChequeNo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        txt_GSTNo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(171, 140, 17));
        jPanel3.setPreferredSize(new java.awt.Dimension(410, 500));

        panelHome.setBackground(new java.awt.Color(171, 140, 17));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnHome.setText("Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(btnHome)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        panelEdit.setBackground(new java.awt.Color(171, 140, 17));
        panelEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnEdit.setText("Edit Courses ");

        javax.swing.GroupLayout panelEditLayout = new javax.swing.GroupLayout(panelEdit);
        panelEdit.setLayout(panelEditLayout);
        panelEditLayout.setHorizontalGroup(
            panelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(btnEdit)
                .addGap(59, 59, 59))
        );
        panelEditLayout.setVerticalGroup(
            panelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCourseList.setBackground(new java.awt.Color(171, 140, 17));
        panelCourseList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCourseList.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnCourseList.setText("Course List");

        javax.swing.GroupLayout panelCourseListLayout = new javax.swing.GroupLayout(panelCourseList);
        panelCourseList.setLayout(panelCourseListLayout);
        panelCourseListLayout.setHorizontalGroup(
            panelCourseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCourseListLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(btnCourseList)
                .addGap(75, 75, 75))
        );
        panelCourseListLayout.setVerticalGroup(
            panelCourseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCourseListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCourseList, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelViewAllRecord.setBackground(new java.awt.Color(171, 140, 17));
        panelViewAllRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnViewAllRecord.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnViewAllRecord.setText("View All Record");

        javax.swing.GroupLayout panelViewAllRecordLayout = new javax.swing.GroupLayout(panelViewAllRecord);
        panelViewAllRecord.setLayout(panelViewAllRecordLayout);
        panelViewAllRecordLayout.setHorizontalGroup(
            panelViewAllRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewAllRecordLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(btnViewAllRecord)
                .addGap(38, 38, 38))
        );
        panelViewAllRecordLayout.setVerticalGroup(
            panelViewAllRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewAllRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBack.setBackground(new java.awt.Color(171, 140, 17));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnBack.setText("Back");

        javax.swing.GroupLayout panelBackLayout = new javax.swing.GroupLayout(panelBack);
        panelBack.setLayout(panelBackLayout);
        panelBackLayout.setHorizontalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBackLayout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(116, 116, 116))
        );
        panelBackLayout.setVerticalGroup(
            panelBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelSreach.setBackground(new java.awt.Color(171, 140, 17));
        panelSreach.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel11.setText("Seach Record");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSreachLayout = new javax.swing.GroupLayout(panelSreach);
        panelSreach.setLayout(panelSreachLayout);
        panelSreachLayout.setHorizontalGroup(
            panelSreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSreachLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        panelSreachLayout.setVerticalGroup(
            panelSreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSreachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelLogout.setBackground(new java.awt.Color(171, 140, 17));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        btnLogout.setText("Logout");

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
            .addGroup(panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLogoutLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnLogout)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
            .addGroup(panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLogoutLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnLogout)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelSreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelViewAllRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSreach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCourseList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelViewAllRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 670));

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlabel11.setText("Reciept  No               :-");
        jPanel1.add(jlabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, -1));

        txt_receiptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 210, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Date        :-");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 88, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("GST No   :- ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 88, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Recieved From         :-");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 160, -1));

        txt_receivedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receivedFromActionPerformed(evt);
            }
        });
        jPanel1.add(txt_receivedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 210, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("The FolloWing Payment In The College Office For The Year :-");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 470, -1));

        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 80, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("  -");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 37, 14));
        jPanel1.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 80, -1));

        jlabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jlabel.setText("Course                        :-");
        jPanel1.add(jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, -1));

        comboCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        comboCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseActionPerformed(evt);
            }
        });
        jPanel1.add(comboCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 210, -1));

        txt_amount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_amountFocusLost(evt);
            }
        });
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        jPanel1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 146, -1));

        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        jPanel1.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 146, -1));

        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        jPanel1.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, 146, -1));

        txt_courseName.setText("Your Choosen Course will appear here .........");
        txt_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 260, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("CGST 9%");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 74, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("SGST 9%");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 146, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("TOTAL FEE");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, 80, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("TOTAL IN WORDS :-");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 180, 40));
        jPanel1.add(txt_total_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 374, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("PRINT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 590, 90, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Remark  :-");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 100, -1));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 370, 60));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Heads");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 73, 16));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setText("Amount(₨)");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, 111, 16));

        lbl_ChequeNo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_ChequeNo.setText("Cheque No                :-");
        jPanel1.add(lbl_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 26));

        txt_DDNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_DDNo.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                txt_DDNoComponentRemoved(evt);
            }
        });
        txt_DDNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DDNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 210, -1));

        lbl_DDNo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_DDNo.setText("DD No                        :-");
        jPanel1.add(lbl_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 26));

        txt_bankName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 210, -1));

        lbl_bankName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_bankName.setText("Bank Name               :-");
        jPanel1.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 26));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Mode of Payment   :-");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 26));

        combo_PaymentMode.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.setToolTipText("");
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        jPanel1.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 210, -1));
        jPanel1.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 140, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Roll No                       :-");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 160, 26));

        txt_rollNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 210, -1));

        txt_ChequeNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_ChequeNo.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                txt_ChequeNoComponentRemoved(evt);
            }
        });
        txt_ChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ChequeNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 210, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("_____________________________________________________________________________________________________________________________________________________________________________________________");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 960, -1));

        txt_GSTNo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_GSTNo.setText("GHEKJQ1234");
        txt_GSTNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GSTNoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_GSTNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 140, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("_____________________________________________________________________________________________________________________________________________________________________________________________");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 960, -1));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 590, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 950, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txt_receiptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptNoActionPerformed

    private void txt_receivedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receivedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receivedFromActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void comboCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseActionPerformed
        // TODO add your handling code here:
        txt_courseName.setText(comboCourse.getSelectedItem().toString());

    }//GEN-LAST:event_comboCourseActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sgstActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void txt_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courseNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                /*if(validation() == true)
                {
                    //JOptionPane.showMessageDialog(this,"Validation Done");
                        String result = insertData();
            
                        if(result.equals("Success"))
                        {
                                JOptionPane.showMessageDialog(this, "Record Inserted SuccessFully");
                                
                                //Object*/
                                Print_Receipt p = new Print_Receipt();
                                p.setVisible(true);
                                this.dispose();
                     /*   }
                        else
                        {
                                JOptionPane.showMessageDialog(this, "Record Insertion  Fail");
                        }
                    }*/
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_DDNoComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_DDNoComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoComponentRemoved

    private void txt_DDNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DDNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoActionPerformed

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
        // TODO add your handling code here:
        /*if(combo_PaymentMode.getSelectedIndex() == 0)  //Select
        {
                
        }
*/
        if(combo_PaymentMode.getSelectedIndex() == 0)  //DD
        {
            lbl_DDNo.setVisible(true);
            txt_DDNo.setVisible(true);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if(combo_PaymentMode.getSelectedIndex() == 1)  //CHECK
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(true);
            txt_ChequeNo.setVisible(true);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if(combo_PaymentMode.getSelectedIndex() == 2) //CASH
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(false);
            txt_bankName.setVisible(false);
        }
        if(combo_PaymentMode.getSelectedItem().equals("Card"))  //CARD
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void txt_ChequeNoComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_ChequeNoComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoComponentRemoved

    private void txt_ChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ChequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoActionPerformed

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        // TODO add your handling code here:

        //Enter Mouse Color Change
        Color clr = new Color(0,153,153); //Object
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        // TODO add your handling code here:

        //Mouse Exit Color Change
        Color clr = new Color(0,50,103); //Object
        panelHome.setBackground(clr);
    }//GEN-LAST:event_btnHomeMouseExited

    private void txt_amountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_amountFocusLost
       //TOTAL amount mathi TAB press karvathi cgst and sgst automatic aave aena mate che
        float amnt = Float.parseFloat(txt_amount.getText());

        Float cgst = (float)(amnt * 0.09); //CGST (%)
        Float sgst = (float)(amnt * 0.09); //SGST(%)

        txt_cgst.setText(cgst.toString());
        txt_sgst.setText(sgst.toString());

        float total = amnt + cgst + sgst; //TOTAL

        txt_total.setText(Float.toString(total));

        txt_total_words.setText(NumberToWordsConverter.convert((int)total) + " Only");

    }//GEN-LAST:event_txt_amountFocusLost

    private void txt_GSTNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GSTNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GSTNoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                if(validation() == true)
                {
                    //JOptionPane.showMessageDialog(this,"Validation Done");
                        String result = insertData();
            
                        if(result.equals("Success"))
                        {
                                JOptionPane.showMessageDialog(this, "Record Inserted SuccessFully");
                        }
                        else
                        {
                                JOptionPane.showMessageDialog(this, "Record Insertion  Fail");
                        }
                    }

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Fees().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel11;
    private javax.swing.JLabel lbl_ChequeNo;
    private javax.swing.JLabel lbl_DDNo;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSreach;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JTextField txt_GSTNo;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankName;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_courseName;
    private javax.swing.JTextField txt_receiptNo;
    private javax.swing.JTextField txt_receivedFrom;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_rollNo;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_words;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables

}
