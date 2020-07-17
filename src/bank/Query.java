package bank;

import bank.time.PersianDate;

import javax.management.ServiceNotFoundException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;

import static java.lang.System.*;

public class Query{
private static Connection conn;


    public static void main(String[] args) {
        try {


           Connection conn;
            conn = DriverManager.getConnection( "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false", "root", "13801380");



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

    public static boolean UpdateRecords(long accountnumber, int field_index, String data){
//TODO:UPDATE ACCOUNTS INFO IN DATABASE LIKE CHANGE THE AMOUNT AFTER TRANSFER...OR DEPOSIT OR ...WITHDRAW
        boolean status=false;

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

                    break;
                case 1:
                    pstmt.setString(1, data);
                    pstmt.setDouble(2, accountnumber);

                   break;
                case 91:
                    pstmt.setDate(1, Date.valueOf(data));
                    pstmt.setDouble(2, accountnumber);


                   break;
                   case 4:
                    pstmt.setDouble(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);

                       break;
                  case 5:
                    pstmt.setInt(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);
                   break;


                case -5 :
                    pstmt.setInt(1, Integer.parseInt(data));
                    pstmt.setDouble(2, accountnumber);

                 break;

                 default:
                     throw new ServiceNotFoundException();
            }
            status = (pstmt.executeUpdate()==1?true:false);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
return status;
    }

    public static void InsertCustomersRecords(){
//TODO
    }

    public static boolean InsertTransactionsRecord(int accountnumber, String type, int Amount, int source, int destination, String description,int AccountBalance){

        boolean status=false;

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");

            String query = "INSERT INTO TRANSACTIONS (datetime,Destination,description,amount,accountnumber,source,receipttype,Accountbalance) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt =conn.prepareStatement(query);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
            String DATETIME = PersianDate.now()+" "+ LocalTime.now().format(format);
            Timestamp datetime = Timestamp.valueOf(DATETIME);

            pstmt.setTimestamp(1,datetime);

            pstmt.setInt(2,destination);

            pstmt.setString(3,description);

            pstmt.setInt(4,Amount);

            pstmt.setInt(5,accountnumber);

            pstmt.setLong(6,source);

            pstmt.setString(7,type);

            pstmt.setInt(8,AccountBalance);

            status= (pstmt.executeUpdate()==1);
        }

        catch (Exception e){
            out.println(e.getMessage());
        }

return  status;
    }


}
