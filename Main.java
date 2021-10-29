import java.sql.*;
import java.util.*;

public class DB{
    public static void main(String []args) throws IOException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no of movies to insert");
        int no_of_movies=sc.nextInt();

        //QUERY TO CREATE A TABLE
        String q1="CREATE TABLE MOVIES( MOV_NAME VARCHAR(20) PRIMARYKEY, LACTOR VARCHAR(20) NOT NULL," +
                "LACTRESS VARCHAR(20) NOT NULL, YEAR INT NOT NULL, DIR_NAME VARCHAR(20) NOT NULL)";

        // QUERY TO INSERT INTO TABLE
        String q2="INSERT INTO MOVIES(MOV_NAME, LACTOR, LACTRESS, YEAR, DIR_NAME) VALUES(?,?,?,?,?)";

        // QUERY TO PERFORM OPERATION
        String q3="SELECT * FROM MOVIES" +
                "WHERE ?=?";

        String url="jdbc:mysql://localhost/";
        DriverManager.registerDriver( new com.mysql.jdbc.Driver());
        Connection con=null;
        try{

            con=DriverManager.getConnection(url,"root","password");
            if(con==null)
                return;
            con.setAutoCommit(false);

            preparedStatement pst=con.prepareStatement(q1);
            pst.execute();

            pst=con.prepareStatement(q2);
            pst.executeUpdate();

            while(no_of_movies)
            {
                String mov=sc.nextLine();
                String actor=sc.nextLine();
                String actress=sc.nextLine();
                int year=sc.nextInt();
                String dir=sc.nextLine();
                pst.setString(1,mov);
                pst.setString(2,actor);
                pst.setString(3,actress);
                pst.setInt(4,year);
                pst.setString(5,dir);

                pst.addBatch();
            }
            pst.executeUpdate();

            System.out.println("Want to perform a Search query on Database? Y/N");
            char ans=sc.next();
            if(ans=='Y') {
                pst = con.prepareStatement(q3);
                System.out.println("Enter your Search Parameter: For Director's Name -> DIR_NAME/Actor's Name -> LACTOR/ For Actress's Name -> LACTRESS");
                String n1=sc.nextLine();
                System.out.println("Enter Name whose movies you'd like to watch");
                String n2=sc.nextLine();
                pst.setString(1,n1);
                pst.setString(2,n2);
                ResultSet rs = pst.executeQuery(q3);
                System.out.println("Movie Name   ||   Lead Actor   ||   Lead Actress   ||   Year of Release   ||   Director's Name");
                while(rs.next())
                {
                    System.out.println( rs.getString(MOV_NAME)+"        "+rs.getString(LACTOR)"        "+rs.getString(LACTRESS)+"        "+rs.getInt(YEAR)+"        "rs.getString(DIR_NAME));
                }
            }
            con.commit();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        finally {
            pst.close();
            con.close();
        }

        }

}
