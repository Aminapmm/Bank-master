package bank;

import bank.time.PersianDate;
import com.mysql.cj.xdevapi.SqlDataResult;

import javax.management.ServiceNotFoundException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;

import static java.lang.System.*;

public class Query{

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "13801380";



    public static void main(String[] args) {
        try {


            //Connection conn;
           // conn = DriverManager.getConnection( "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false", "root", "13801380");
            System.out.println(Authentication(1000,2356));



        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static boolean Authentication(int accountnumber,int ramzeAvval)throws SQLException{
        boolean access=false;

            Connection conn = DriverManager.getConnection( DB_URL,DB_USERNAME,DB_PASSWORD);
            String query = "SELECT * FROM CUSTOMERSINFO WHERE accountnumber=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,accountnumber);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("Ramzeavval")==ramzeAvval){
               access=true;

            }

        return access;

    }
    public static boolean OnlineAuthentication(int accountnumber,int ramzeDovom)throws SQLException{
        boolean access=false;


            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");

            String query = "SELECT Ramzedovom FROM CUSTOMERSINFO WHERE accountnumber=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,accountnumber);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("Ramzedovom")==ramzeDovom){
                access=true;

            }

        return access;
    }



    public static  ResultSet ShowInformation(int accountnumber) {

        ResultSet rs=null;

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");
            String query = "SELECT * FROM CUSTOMERSINFO WHERE ACCOUNTNUMBER=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, accountnumber);
             rs = pstmt.executeQuery();
             rs.next();


        }
            catch (Exception e) {
                out.println(e.getMessage());
            }
            return rs;
        }

    public static boolean UpdateRecords(int accountnumber, int field_index, String data){

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

    public static boolean InsertCustomersRecords(BankAccount account)throws SQLException {

            boolean status=false;

            Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            String query = "INSERT INTO CUSTOMERSINFO (FIRSTNAME,LASTNAME,NATIONALID,BIRTHDATE,PHONENUMBER,ACCOUNTNUMBER,ACCOUNTTYPE,RAMZEAVVAL,RAMZEDOVOM,ACCOUNTBALANCE,REGISTERDATE) VALUES(?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1,account.getFirstname());
            pstmt.setString(2,account.getLastname());
            pstmt.setLong(3,account.getNationalID());
            pstmt.setDate(4,Date.valueOf(account.getBirthdate().toString()));
            pstmt.setLong(5,account.getPhonenumber());
            pstmt.setInt(6,account.getAccountnumber());
            pstmt.setString(7,account.getAccountype());
            pstmt.setInt(8,account.getRamzeAvval());
            pstmt.setInt(9,account.getRamzeDovom());
            pstmt.setInt(10,account.getAccountbalance());
            pstmt.setDate(11,Date.valueOf(account.getRegistrationDate().toString()));

            status=(pstmt.executeUpdate()==1?true:false);

            return status;
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

        public static void Showlast10transactions(){

        }

}
