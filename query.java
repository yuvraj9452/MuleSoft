/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;
import java.sql.*;
import java.util.*;
import builder.Database;
/**
 *
 * @author manus
 */

public class query {
    
    Scanner sc=new Scanner (System.in);
    Connection con=null;
    PreparedStatement pst=null;
    
    public query()
    {
        con=Database.dbConnect();
        exeQuery();
    }
    
    public void exeQuery()
    {
        System.out.println("Choose Your Filter 1.Movie Name(M) 2.Lead_Actor(L) 3.Lead_Actress(A) 4.Year_of_release(Y) 5.Dir_Name(D)");
        char ch=sc.next().charAt(0);
        System.out.println("Enter your Filter choice Name");
        String s2=sc.next();
        
        try
        {
            switch(ch)
            {
                case 'M':pst=con.prepareStatement("SELECT * FROM movie WHERE Movie_Name=?");
                         pst.setString(1,s2);
                         break;
                case 'L':pst=con.prepareStatement("SELECT * FROM movie WHERE Lead_Actor=?");
                         pst.setString(1,s2);
                         break;
                case 'A':pst=con.prepareStatement("SELECT * FROM movie WHERE Lead_Actress=?");
                         pst.setString(1,s2);
                         break;
                case 'Y':pst=con.prepareStatement("SELECT * FROM movie WHERE Year_of_release=?");
                         pst.setString(1,s2);
                         break;
                case 'D':pst=con.prepareStatement("SELECT * FROM movie WHERE Dir_Name=?");
                         pst.setString(1, s2);
                         break;
                default:System.out.println("Wrong Parameter");
            }
            
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                System.out.println("Movie Name"+rs.getString("Movie_Name"));
                System.out.println("Lead Actor"+rs.getString("Lead_Actor"));
                System.out.println("Lead Actress"+rs.getString("Lead_Actress"));
                System.out.println("Year Of Release"+rs.getString("Year_of_release"));
                System.out.println("Director's Name"+rs.getString("Dir_Name"));
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                pst.close();
            }
            catch(Exception e2)
            {
               System.out.println("Error Closing Connections");
            }
        }
    }
    
}

