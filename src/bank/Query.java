package bank;

import bank.time.PersianDate;
import com.mysql.cj.xdevapi.SqlDataResult;

import javax.management.ServiceNotFoundException;
import java.lang.invoke.SwitchPoint;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;

import static java.lang.System.*;

//TODO:WHEN INSERTING RECORDS IN ACCOUNT TABLES CHECK FOR EXISTANCE OF THE SAME RECORD...AND THEN TAKE THE ACTION

public class Query{

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "13801380";

/***
 *
 *THIS METHOD IS FOT AUTHENTICATING THE BEFORE DOING WORKS LIKE(WITHDRAW,DEPOSIT,CHANGING THE PASSWORDS,...)
 * It'll take  accountnumber & Ramze-avval and  check it with the database then it will  return true/false.
 *
***/
    public static boolean Authentication(int accountnumber,int ramzeAvval)throws SQLException{
        boolean access=false;

            Connection conn = DriverManager.getConnection( DB_URL,DB_USERNAME,DB_PASSWORD);

            String ACCOUNTTYPE = Query.ShowInformation(accountnumber).getString("ACCOUNTTYPE")+"ACCOUNTS";

            String query = String.format("SELECT * FROM %s WHERE accountnumber=?",ACCOUNTTYPE);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,accountnumber);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("Ramzeavval")==ramzeAvval){
               access=true;

            }

        return access;
    }

     /***
      * Another authentication method that takes accountnumber & Ramze-dovom.
      * this method used in Online Services Like (Transfering,Receiving Last n transactions,Receiving the Accountbalance)
      * Same as the Previuos Method it'll Check the Parameter with database then return boolean true/false
      ***/


    public static boolean OnlineAuthentication(int accountnumber,int ramzeDovom)throws SQLException{

            boolean access=false;

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String accountype = Query.ShowInformation(accountnumber).getString("ACCOUNTTYPE")+"ACCOUNTS";

            String query = String.format("SELECT RAMZEDOVOM FROM  %s  WHERE accountnumber=?",accountype);

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,accountnumber);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("Ramzedovom")==ramzeDovom){
                access=true;

            }

        return access;
    }

    /***
     * This method is To Show an accountholder information when needed.
     * It'll return a resultset With Personal informarion & Accountinformation
     *
     * @param accountnumber
     * @return
     * @throws SQLException
     */

    public static  ResultSet ShowInformation(int accountnumber)throws SQLException {

        ResultSet rs=null;
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String query="";
        PreparedStatement pstmt;

        pstmt = conn.prepareStatement("SELECT ACCOUNTTYPE FROM ACCOUNTHOLDERS WHERE ACCOUNTNUMBER=?");
        pstmt.setInt(1,accountnumber);

        rs = pstmt.executeQuery();
        rs.next();
        String accounttype = rs.getString("ACCOUNTTYPE").toUpperCase()+"ACCOUNTS";


        query = String.format("SELECT ACCOUNTHOLDERS.*, %s.* FROM ACCOUNTHOLDERS , %s WHERE ACCOUNTHOLDERS.ACCOUNTNUMBER=? AND %s.ACCOUNTNUMBER=?",accounttype,accounttype,accounttype);
       // System.out.println(query);

        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, accountnumber);
        pstmt.setInt(2, accountnumber);
        rs = pstmt.executeQuery();
        rs.next();

            return rs;
        }

    /***
     * This Method Used to Update Records in Account Tables...!
     * More Specifically It's Use for Updating Information (Personal or account info like passwords and Stuff Like that.)
     * The Output is True/False
     *
     * @param accountnumber
     * @param field
     * @param data


     */
    public static boolean UpdateRecords(int accountnumber,String field,String data) throws SQLException, ServiceNotFoundException {


        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        boolean status=false;
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement("SELECT ACCOUNTTYPE FROM ACCOUNTHOLDERS WHERE ACCOUNTHOLDERS.ACCOUNTNUMBER = ?");
        pstmt.setInt(1,accountnumber);
        String query = " ";
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        String type = rs.getString("ACCOUNTTYPE").toUpperCase()+"ACCOUNTS";

        switch (field){

            case "RAMZEAVVAL":

                query = "UPDATE %s SET RAMZEAVVAL = ? WHERE ACCOUNTNUMBER=?".replace("%s",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);

                break;

            case "RAMZEDOVOM":

                query = "UPDATE %s SET RAMZEDOVOM = ? WHERE ACCOUNTNUMBER=?".replace("%s",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);

                break;

            case "ACCOUNTBALANCE":

                query = "UPDATE %s SET ACCOUNTBALANCE = ? WHERE ACCOUNTNUMBER=?".replace("%s",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);

                break;
            case "FIRSTNAME":

                query = "UPDATE ACCOUNTHOLDERS SET FIRSTNAME = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,data);
                pstmt.setInt(2,accountnumber);
                break;

            case"LASTNAME":

                query = "UPDATE ACCOUNTHOLDERS SET LASTNAME = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,data);
                pstmt.setInt(2,accountnumber);
                break;

            case "PHONENUMBER":

                query = "UPDATE ACCOUNTHOLDERS SET PHONENUMBER = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setLong(1, Long.valueOf(data));
                pstmt.setInt(2,accountnumber);
                break;

            case "INTERESTRATE":

                query = "UPDATE SAVINGACCOUNTS SET INTERESTRATE = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);
                break;

            case "MONTHLYPAYOUT":

                query = "UPDATE SAVINGACCOUNTS SET MONTHLYPAYOUT = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);
                break;

        }
        status = pstmt.execute();

        return status;
    }

    public static void InsertCustomersRecords(CheckingAccount account)throws SQLException {

                boolean status=false;

                Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

                String query = "";
                PreparedStatement pstmt;


                query = "INSERT INTO ACCOUNTHOLDERS (FIRSTNAME,LASTNAME,NATIONALID,ACCOUNTNUMBER,ACCOUNTTYPE,BIRTHDATE,PHONENUMBER) VALUES (?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(query);

                pstmt.setString(1,account.getFirstname());
                pstmt.setString(2,account.getLastname());
                pstmt.setLong(3,account.getNationalID());
                pstmt.setInt(4,account.getAccountnumber());
                pstmt.setString(5,account.getAccountype());
                pstmt.setDate(6,Date.valueOf(account.getBirthdate().toString()));
                pstmt.setLong(7,account.getPhonenumber());

                pstmt.executeUpdate();

                query = "INSERT INTO Checkingaccounts (NATIONALID,ACCOUNTNUMBER,RAMZEAVVAL,RAMZEDOVOM,ACCOUNTBALANCE,REGISTERDATE) VALUES(?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(query);

                pstmt.setLong(1,account.getNationalID());
                pstmt.setInt(2,account.getAccountnumber());
                pstmt.setInt(3,account.getRamzeAvval());
                pstmt.setInt(4,account.getRamzeDovom());
                pstmt.setInt(5,account.getAccountbalance());
                pstmt.setDate(6,Date.valueOf(account.getRegistrationDate().toString()));

                pstmt.executeUpdate();


                //status=(pstmt.executeUpdate()==1?true:false);

                //return status;
        }

    public static void InsertCustomersRecords(Savingaccount account)throws SQLException {

        boolean status=false;

        Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        String query = "INSERT INTO ACCOUNTHOLDERS (FIRSTNAME,LASTNAME,NATIONALID,BIRTHDATE,PHONENUMBER,ACCOUNTNUMBER,ACCOUNTTYPE) VALUES(?,?,?,?,?,?,?)";


        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setString(1,account.getFirstname());
        pstmt.setString(2,account.getLastname());
        pstmt.setLong(3,account.getNationalID());
        pstmt.setDate(4,Date.valueOf(account.getBirthdate().toString()));
        pstmt.setLong(5,account.getPhonenumber());
        pstmt.setInt(6,account.getAccountnumber());
        pstmt.setString(7,account.getAccountype());

        pstmt.executeUpdate();


        String query1 = "INSERT INTO SAVINGACCOUNTS (NATIONALID,ACCOUNTNUMBER,RAMZEAVVAL,RAMZEDOVOM,ACCOUNTBALANCE,REGISTERDATE,MONTHLYPAYOUT,INTERESTRATE,HOLDDURATION) VALUES (?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(query1);

        pstmt.setLong(1,account.getNationalID());
        pstmt.setInt(2,account.getAccountnumber());
        pstmt.setInt(3,account.getRamzeAvval());
        pstmt.setInt(4,account.getRamzeDovom());
        pstmt.setInt(5,account.getAccountbalance());
        pstmt.setDate(6,Date.valueOf(account.getRegistrationDate().toString()));
        pstmt.setInt(7,account.getMONTHLY_PAYOUT_AMOUNT());
        pstmt.setInt(8,account.getInterestrate());
        pstmt.setInt(9,account.getTimeperiod());

        pstmt.executeUpdate();

        //status=(pstmt.executeUpdate()==1?true:false);

        //return status;
    }

    public static boolean InsertTransactionsRecord(int accountnumber, String type, int Amount, int source, int destination, String description,int AccountBalance) throws SQLException{

            boolean status=false;

                Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

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

                return  status;
            }

        public static ResultSet ShowTransactionrecords(int Accountnumber,int number)throws SQLException{

        //TODO:It's Better to Override toString Method,And Print the Records!

                Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

                String query = "SELECT * FROM TRANSACTIONS WHERE ACCOUNTNUMBER=? LIMIT ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1,Accountnumber);
                pstmt.setInt(2,number);
                ResultSet rs = pstmt.executeQuery();
                return rs;

            }



}
