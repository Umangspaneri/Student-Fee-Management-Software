/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fees_System_Software_Umang;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


import java.text.SimpleDateFormat;
//import java.util.Date;



/**
 *
 * @author HP
 */
public class UpdateFeesDetails extends javax.swing.JFrame {
    

    /**
     * Creates new form Add_Fees
     */
    public UpdateFeesDetails() {
        initComponents();    //Call Method
        displayCashFirst();  // Call Counc
        fillComboBox();      //Call Method
        
        int receiptNo = getReceiptNo();  //sem class ni Andar Object Vagar Call kari Sakay Method ne (Jo static hoy to Object thi call Thay baki call no kari sakay)
        txt_receiptNo.setText(Integer.toString(receiptNo));
        
        
        setRecords();
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
                if(txt_DDNo.getText().equals(""))
                {
                 JOptionPane.showMessageDialog(this,"Please Enter DD Number");
                 return false;
                }
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
            
            if(txt_amount.getText().equals(""))  //Amount Check
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
    
    public String UpdateData()
    {
        String status = "";
        
        int recieptNo = Integer.parseInt(txt_receiptNo.getText());
        String studentName = txt_receivedFrom.getText();
        String rollNo = txt_rollNo.getText();
        String paymentMode = combo_PaymentMode.getSelectedItem().toString();
        String chequeNo = txt_ChequeNo.getText().trim();
        if(chequeNo.isEmpty()){
            chequeNo = null;
        }
        
        String bankName = txt_bankName.getText();
        String ddNo = txt_DDNo.getText().trim();
        if(ddNo.isEmpty()){
            ddNo = null;
        }
        String courseName = txt_courseName.getText();
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
              PreparedStatement pst = con.prepareStatement("update fees_details set student_name = ?, roll_no = ?, payment_mode = ?, cheque_no = ?, bank_name = ?, dd_no = ?, courses = ?, gstin = ?, total_amount = ?, date = ?, amount = ?, cgst = ?, sgst = ?, total_in_words = ?, remark = ?, year1 = ?, year2 = ? where reciept_no = ?");
              // (reciept_no,student_name,roll_no,payment_mode,cheque_no,bank_name,dd_no,courses,gstin,total_amount,date,amount,cgst,sgst,total_in_words,remark,year1,year2)
              // values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            //pst.setInt(1,recieptNo);
            
            pst.setString(1,studentName);
            pst.setString(2,rollNo);
            pst.setString(3, paymentMode);
            pst.setString(4,chequeNo);
            pst.setString(5, bankName);
            pst.setString(6, ddNo);
            pst.setString(7, courseName);
//            
            pst.setString(8, gstin);
            pst.setFloat(9, totalAmount);
            pst.setString(10, Date);
            pst.setFloat(11, initialAmount);
            pst.setFloat(12,cgst);
            pst.setFloat(13, sgst);
            pst.setString(14,totalInWords);
            pst.setString(15, remark);
            pst.setInt(16, year1);
            pst.setInt(17, year2);
            pst.setInt(18,recieptNo);
            
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
    
    
     //Method
    public void setRecords()
    {
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details order by reciept_no desc limit 1");
           //SELECT * FROM fees_details LIMIT 1 
            ResultSet rs = pst.executeQuery();
            rs.next();
            
            txt_receiptNo.setText(rs.getString("reciept_no"));
            combo_PaymentMode.setSelectedItem(rs.getString("payment_mode"));
            txt_ChequeNo.setText(rs.getString("cheque_no"));
            txt_DDNo.setText(rs.getString("dd_no"));
            txt_bankName.setText(rs.getString("bank_name"));
            txt_receivedFrom.setText(rs.getString("student_name"));
            dateChooser.setDate(rs.getDate("date"));
            txt_year1.setText(rs.getString("year1"));
            txt_year2.setText(rs.getString("year2"));
            txt_rollNo.setText(rs.getString("roll_no"));
            comboCourse.setSelectedItem(rs.getString("courses"));
            txt_amount.setText(rs.getString("amount"));
            txt_sgst.setText(rs.getString("sgst"));
            txt_cgst.setText(rs.getString("cgst"));
            txt_total.setText(rs.getString("total_amount"));
            txt_total_words.setText(rs.getString("total_in_words"));
            txt_remark.setText(rs.getString("remark"));
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }

   
    
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelParent = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_receiptNo = new javax.swing.JTextField();
        combo_PaymentMode = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_DDNo = new javax.swing.JTextField();
        lbl_bankName = new javax.swing.JLabel();
        txt_bankName = new javax.swing.JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();
        lbl_DDNo = new javax.swing.JLabel();
        lbl_ChequeNo = new javax.swing.JLabel();
        txt_ChequeNo = new javax.swing.JTextField();
        panelChild = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_receivedFrom = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        comboCourse = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_rollNo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_courseName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_total_words = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        txt_GSTNo = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add_Fees");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelParent.setBackground(new java.awt.Color(255, 255, 0));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mode Of PayMent  :-");
        panelParent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 75, -1, 26));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Reciept  No :-");
        panelParent.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 31, -1, 26));

        txt_receiptNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_receiptNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_receiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 31, 177, -1));

        combo_PaymentMode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        combo_PaymentMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "Cash", "Card" }));
        combo_PaymentMode.setSelectedIndex(2);
        combo_PaymentMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_PaymentModeActionPerformed(evt);
            }
        });
        panelParent.add(combo_PaymentMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 75, 177, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Date :-");
        panelParent.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 31, 88, 26));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("GSTIN  :-");
        panelParent.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 71, 88, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("x");
        jLabel17.setToolTipText("");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        panelParent.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1001, 0, -1, 33));

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
        panelParent.add(txt_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 119, 177, -1));

        lbl_bankName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_bankName.setText("BANK Name :-");
        panelParent.add(lbl_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 166, -1, 26));

        txt_bankName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bankNameActionPerformed(evt);
            }
        });
        panelParent.add(txt_bankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 163, 177, -1));

        dateChooser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelParent.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 31, 170, 26));

        lbl_DDNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DDNo.setText("DD No :-");
        panelParent.add(lbl_DDNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 119, -1, 26));

        lbl_ChequeNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ChequeNo.setText("Cheque No :-");
        panelParent.add(lbl_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 26));

        txt_ChequeNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_ChequeNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ChequeNoActionPerformed(evt);
            }
        });
        panelParent.add(txt_ChequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 119, 177, -1));

        panelChild.setBackground(new java.awt.Color(255, 255, 0));
        panelChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Recieved From :-");
        panelChild.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 6, 123, -1));

        txt_receivedFrom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_receivedFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receivedFromActionPerformed(evt);
            }
        });
        panelChild.add(txt_receivedFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 6, 262, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("The FolloWing Payment In The College Office For The Year :-");
        panelChild.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 50, 422, -1));

        txt_year1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelChild.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 77, 71, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("  -");
        panelChild.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 76, 37, 14));

        txt_year2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelChild.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 77, 71, -1));

        comboCourse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B.C.A", "B.SC.I.T", "B.SC", "B.COM", "B.B.A" }));
        comboCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseActionPerformed(evt);
            }
        });
        panelChild.add(comboCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 121, 200, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Course  :-");
        panelChild.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 121, 99, 26));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Roll No : -");
        panelChild.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 165, -1, 26));

        txt_rollNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelChild.add(txt_rollNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 165, 200, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Sr. NO");
        panelChild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 229, 63, 16));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Heads");
        panelChild.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 229, 73, 16));

        txt_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courseNameActionPerformed(evt);
            }
        });
        panelChild.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 272, 399, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("___________________________________________________________________________________________________________________________________________________________________________________________________________");
        panelChild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 209, 983, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("___________________________________________________________________________________________________________________________________________________________________________________________________________");
        panelChild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 238, 983, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Amount(₨)");
        panelChild.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(713, 229, 111, 16));

        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        panelChild.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 272, 146, -1));
        panelChild.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 312, 146, -1));
        panelChild.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 352, 146, -1));
        panelChild.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(697, 402, 146, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("CGST 9%");
        panelChild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 312, 74, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("SGST 9%");
        panelChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 352, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("TOTAL FEES");
        panelChild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 402, 97, -1));

        jLabel15.setText("_________________________________________________________________________________________________________________________________________");
        panelChild.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 380, 668, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("TOTAL IN WORDS");
        panelChild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 436, 170, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Remark :");
        panelChild.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 470, 83, -1));
        panelChild.add(txt_total_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 439, 374, 25));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane1.setViewportView(txt_remark);

        panelChild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 470, 374, 59));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setText("Reciver Signature");
        panelChild.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 470, -1, -1));

        btn_print.setBackground(new java.awt.Color(171, 140, 17));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_print.setForeground(new java.awt.Color(204, 0, 0));
        btn_print.setText("PRINT");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        panelChild.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 510, -1, -1));

        panelParent.add(panelChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        txt_GSTNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_GSTNo.setText("22HVSJH55");
        panelParent.add(txt_GSTNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 71, 90, -1));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 1020, 800));

        jPanel3.setBackground(new java.awt.Color(171, 140, 17));

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
                .addGap(109, 109, 109)
                .addComponent(btnHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout panelSreachLayout = new javax.swing.GroupLayout(panelSreach);
        panelSreach.setLayout(panelSreachLayout);
        panelSreachLayout.setHorizontalGroup(
            panelSreachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSreachLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(83, 83, 83)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSreach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCourseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelViewAllRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(panelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
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
                .addGap(109, 109, 109))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void txt_ChequeNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ChequeNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ChequeNoActionPerformed

    private void txt_bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bankNameActionPerformed

    private void txt_DDNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DDNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoActionPerformed

    private void txt_DDNoComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_DDNoComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DDNoComponentRemoved

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void txt_receiptNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptNoActionPerformed

    private void txt_receivedFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receivedFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receivedFromActionPerformed

    private void comboCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseActionPerformed
        // TODO add your handling code here:
         txt_courseName.setText(comboCourse.getSelectedItem().toString());
        
    }//GEN-LAST:event_comboCourseActionPerformed

    private void txt_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courseNameActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_txt_courseNameActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        // Print Receipt
        //insertData();

                if(validation() == true)
                {
                    //JOptionPane.showMessageDialog(this,"Validation Done");
                        String result = UpdateData();
            
                        if(result.equals("Success"))
                        {
                                JOptionPane.showMessageDialog(this, "Record UPDATEED SuccessFully");
                                
                                //print receipt nu thy jay pachi comment kadhvi
                                /*Print_Receipt p = new Print_Receipt();
                                p.setVisible(true);
                                this.dispose();*/
                        }
                        else
                        {
                                JOptionPane.showMessageDialog(this, "Record UPDATE  Fail");
                        }
                    }
    }//GEN-LAST:event_btn_printActionPerformed

    private void combo_PaymentModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_PaymentModeActionPerformed
        // TODO add your handling code here:
        if(combo_PaymentMode.getSelectedIndex() == 0)  //DD
        { 
            lbl_DDNo.setVisible(true);
            txt_DDNo.setVisible(true);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if(combo_PaymentMode.getSelectedIndex() == 1)
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(true);
            txt_ChequeNo.setVisible(true);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
        if(combo_PaymentMode.getSelectedIndex() == 2)
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(false);
            txt_bankName.setVisible(false);
        }
        if(combo_PaymentMode.getSelectedItem().equals("Card"))
        {
            lbl_DDNo.setVisible(false);
            txt_DDNo.setVisible(false);
            lbl_ChequeNo.setVisible(false);
            txt_ChequeNo.setVisible(false);
            lbl_bankName.setVisible(true);
            txt_bankName.setVisible(true);
        }
    }//GEN-LAST:event_combo_PaymentModeActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        // TODO add your handling code here:
        
        //TOTAL And CGST, SGST (%) MATE che 
       float amnt = Float.parseFloat(txt_amount.getText());
       
       Float cgst = (float)(amnt * 0.09); //CGST (%)
       Float sgst = (float)(amnt * 0.09); //SGST(%)
       
       txt_cgst.setText(cgst.toString()); 
       txt_sgst.setText(sgst.toString());
       
       float total = amnt + cgst + sgst; //TOTAL
       
       txt_total.setText(Float.toString(total));
       
       txt_total_words.setText(NumberToWordsConverter.convert((int)total) + " Only");
       
    }//GEN-LAST:event_txt_amountActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UpdateFeesDetails().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCourseList;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnViewAllRecord;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JComboBox<String> combo_PaymentMode;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_ChequeNo;
    private javax.swing.JLabel lbl_DDNo;
    private javax.swing.JLabel lbl_bankName;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelCourseList;
    private javax.swing.JPanel panelEdit;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSreach;
    private javax.swing.JPanel panelViewAllRecord;
    private javax.swing.JTextField txt_ChequeNo;
    private javax.swing.JTextField txt_DDNo;
    private javax.swing.JLabel txt_GSTNo;
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