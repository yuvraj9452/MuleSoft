/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;
import java.sql.*;
/**
 *
 * @author yuvraj
 */
public class Database {
    Connection con=null;
    PreparedStatement pst=null;
    public static Connection dbConnect()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn=DriverManager.getConnection("jdbc:mysql://loalhost:3306/movie","root","");
            return conn;
        }
        catch(Exception e2)
        {
            System.out.println("Error!!!");
            return null;
        }
    }
}
