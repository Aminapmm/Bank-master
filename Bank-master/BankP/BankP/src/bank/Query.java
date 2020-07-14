package bank;
import java.sql.*;
public class Query{

    public  Query(long accountnumber){

        try {
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");
    }

    catch (Exception e){
        System.out.println(e.getMessage());
    }

    }
    public static void main(String[] args) {
        try {

           // Connection conn;
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");
                //ShowInformation(1000).next();
                System.out.println(ShowInformation(1000).isLast());
           // System.out.println(OnlineAuthentication(1000,5678));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean Authentication(long accountnumber,int ramzeAvval,Connection conn){
        boolean access=false;

        try {

            String query = "SELECT * FROM CUSTOMERSINFO WHERE ACCOUNTNUMBER = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1,accountnumber);
            ResultSet rs = pstmt.executeQuery(query);
            rs.next();
            if(rs.getInt("Ramzeavval")==ramzeAvval){
               access=true;

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return access;

    }
    public static boolean OnlineAuthentication(long accountnumber,int ramzeDovom){
        boolean access=false;

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");

            String query = "SELECT Ramzedovom FROM CUSTOMERSINFO WHERE accountnumber=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1,accountnumber);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("Ramzedovom")==ramzeDovom){
                access=true;

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return access;
    }



    public static  ResultSet ShowInformation(long accountnumber) {
        ResultSet rs=null;
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");
            String sqlquery = "SELECT * FROM CUSTOMERSINFO WHERE ACCOUNTNUMBER=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlquery);
            pstmt.setLong(1, accountnumber);
             rs = pstmt.executeQuery();
             rs.next();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    return rs;
    }


}
