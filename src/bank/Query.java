package bank;
import bank.time.PersianDate;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.System.*;

public class Query{
private static Connection conn;


    public static void main(String[] args) {
        try {


           Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");
           // String query = "UPDATE CUSTOMERSINFO SET AMOUNT=? WHERE ACCOUNTNUMBER=1000";
          // System.out.println(UpdateRecords(1000,12,"100000"));
            //Query q = new Query();
           UpdateRecords(2000,12,"60500");
           // double current_amount = ShowInformation(1000).getDouble("amount");
            //pstmt.setDouble(1,current_amount-5000);
           // pstmt.execute();

            /***
            pstmt.setDate(1,Date.valueOf("2001-05-13"));
            pstmt.setLong(2,2000);
            pstmt.setString(3,"Just some tests");
            pstmt.setString(4,"Transfer");
            pstmt.setDouble(5,50000);
            pstmt.setLong(6,1000);
            pstmt.setLong(7,1000);
            pstmt.execute();
             ***/
                //
               // System.out.println(ShowInformation(1000).isLast());
           // System.out.println(OnlineAuthentication(1000,5678));
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static boolean Authentication(long accountnumber,int ramzeAvval){
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
            out.println(e.getMessage());
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
            out.println(e.getMessage());
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
            out.println(e.getMessage());
        }
    return rs;
    }

    public static  void UpdateRecords(long accountnumber,int field_index,String data){
//TODO:UPDATE ACCOUNTS INFO IN DATABASE LIKE CHANGE THE AMOUNT AFTER TRANSFER...OR DEPOSIT OR ...WITHDRAW


        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");


            String query1 = "SELECT * FROM CUSTOMERSINFO";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            int field_type = rs.getMetaData().getColumnType(field_index);
            String field_name = rs.getMetaData().getColumnName(field_index).toUpperCase();

            String query = "UPDATE CUSTOMERSINFO SET %s = ?  WHERE ACCOUNTNUMBER = ?";
            query = String.format(query, field_name);

            PreparedStatement pstmt = conn.prepareStatement(query);

            switch (field_type) {
                case  12:
                    pstmt.setString(1, data);
                    pstmt.setDouble(2, accountnumber);
                     pstmt.execute();
                    //System.out.println(status);
                    break;
                case 1:
                    pstmt.setString(1, data);
                    pstmt.setDouble(2, accountnumber);
                     pstmt.execute();
                    //System.out.println(status);
                    break;
                case 91:
                    pstmt.setDate(1, Date.valueOf(data));
                    pstmt.setDouble(2, accountnumber);
                    pstmt.execute();
                    // System.out.println(status);
                    break;
                   case 4:
                    pstmt.setDouble(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);
                    pstmt.execute();
                    // System.out.println(status1);

                       break;
                  case 5:
                    pstmt.setInt(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);
                    pstmt.execute();
                    break;


                case -5 :
                    pstmt.setInt(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);
                     pstmt.execute();
                    // System.out.println(status);
                    break;
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void InsertRecords(){

    }


}
