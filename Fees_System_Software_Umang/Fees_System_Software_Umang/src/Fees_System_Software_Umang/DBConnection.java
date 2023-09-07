/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fees_System_Software_Umang;
import java.sql.*;
//import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class DBConnection {
    
    public static Connection getConnection() // method
    {
        Connection con = null;  //connection
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_system","root","");
        }
        catch(Exception e)
        {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
    
}
