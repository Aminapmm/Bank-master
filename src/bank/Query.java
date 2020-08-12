package bank;

import bank.time.PersianDate;

import javax.management.ServiceNotFoundException;

import java.sql.*;
import java.sql.Date;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static java.lang.System.*;

public class Query{

    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "13801380";
    static Scanner input = new Scanner(in);

    public static boolean OperatorAuthentication(String username, int password) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        String query = "SELECT * FROM OPERATORS WHERE USERNAME =? AND PASSWORD = ? ";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,username);
        pstmt.setInt(2,password);
        ResultSet rs = pstmt.executeQuery();
        rs.last();
        boolean status = (rs.getRow()==1)?true:false;
        return status;
    }

/***
 *
 *THIS METHOD IS FOT AUTHENTICATING THE BEFORE DOING WORKS LIKE(WITHDRAW,DEPOSIT,CHANGING THE PASSWORDS,...)
 * It'll take  accountnumber & Ramze-avval and  check it with the database then it will  return true/false.
 *
***/

    public static boolean Authentication(int accountnumber,int ramzeavval)throws SQLException{
        boolean access=false;

            Connection conn = DriverManager.getConnection( DB_URL,DB_USERNAME,DB_PASSWORD);

            String ACCOUNTTYPE = Query.ShowInformation(accountnumber).getString("ACCOUNTTYPE")+"ACCOUNTS";

            String query = String.format("SELECT RAMZEAVVAL FROM %s WHERE accountnumber=?",ACCOUNTTYPE);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,accountnumber);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int password = rs.getInt("Ramzeavval");


            for(int i=3;i>0;i--) {

                if ( password == ramzeavval) {
                    access = true;
                    break;
                }

                else{

                    if(i==1){
                        out.println("Unfortunately,Your Daily Limit exceeded");
                        access=false;
                        break;
                    }
                    System.out.printf("Your Entered Password Was Incorrect,Try again(%d attemtpts left)",i-1);
                    ramzeavval = input.nextInt();
                    continue;
                }
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
            int Internet_password = rs.getInt("Ramzedovom");
            for(int i=3;i>0;i--) {
                if (Internet_password == ramzeDovom) {
                    access = true;
                    break;
                }

                else{
                    access=false;
                    out.println("Incorrect Password.");
                    Internet_password = input.nextInt();
                    if(i==1){
                        break;
                    }

                }
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
     *
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
        field = field.toUpperCase();

        switch (field){

            case "RAMZEAVVAL":

                query = String.format("UPDATE %s SET RAMZEAVVAL = ? WHERE ACCOUNTNUMBER=?",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);

                break;

            case "RAMZEDOVOM":

                query = String.format("UPDATE %s SET RAMZEDOVOM = ? WHERE ACCOUNTNUMBER=?",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setInt(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);

                break;

            case "ACCOUNTBALANCE":

                query = String.format("UPDATE  %s  SET ACCOUNTBALANCE = ? WHERE ACCOUNTNUMBER=?",type);

                pstmt=conn.prepareStatement(query);
                pstmt.setLong(1,Integer.parseInt(data));
                pstmt.setInt(2,accountnumber);
                pstmt.executeUpdate();

                break;
            case "FIRSTNAME":

                query = "UPDATE ACCOUNTHOLDERS SET FIRSTNAME = ? WHERE ACCOUNTNUMBER=?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,data);
                pstmt.setInt(2,accountnumber);
                pstmt.executeUpdate();
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

            case "STATUS":

                query = String.format("UPDATE %s SET STATUS = ? WHERE ACCOUNTNUMBER=?",type);
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,data);
                pstmt.setInt(2,accountnumber);
                break;


        }
        status = (pstmt.executeUpdate()==1)?true:false;

        return status;
    }

    public static boolean InsertCustomersRecords(CheckingAccount account)throws SQLException {

                boolean status=false;

                Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

                String query = "";
                PreparedStatement pstmt;


                query = "INSERT INTO ACCOUNTHOLDERS (FIRSTNAME,LASTNAME,NATIONALID,ACCOUNTNUMBER,ACCOUNTTYPE,BIRTHDATE,PHONENUMBER) VALUES (?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(query);

                pstmt.setString(1,account.getOwner().getFirstname());
                pstmt.setString(2,account.getOwner().getLastname());
                pstmt.setString(3,account.getOwner().getNationalid());
                pstmt.setInt(4,account.getAccountnumber());
                pstmt.setString(5,account.getAccountType());
                pstmt.setDate(6,Date.valueOf(account.getOwner().getBirthdate().toString()));
                pstmt.setString(7,account.getOwner().getPhonenumber());

                pstmt.executeUpdate();

                query = "INSERT INTO Checkingaccounts (NATIONALID,ACCOUNTNUMBER,RAMZEAVVAL,RAMZEDOVOM,ACCOUNTBALANCE,REGISTERDATE,STATUS) VALUES(?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(query);

                pstmt.setString(1,account.getOwner().getNationalid());
                pstmt.setInt(2,account.getAccountnumber());
                pstmt.setInt(3,account.getRamzeavval());
                pstmt.setInt(4,account.getRamzedovom());
                pstmt.setInt(5,account.getAccountbalance());
                pstmt.setDate(6,Date.valueOf(account.getRegisterdate().toString()));
                pstmt.setString(7,account.getStatus().toString());


                status=(pstmt.executeUpdate()==1?true:false);

                return status;
        }

    public static boolean InsertCustomersRecords(Savingaccount account)throws SQLException {

        boolean status=false;

        Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        String query = "INSERT INTO ACCOUNTHOLDERS (FIRSTNAME,LASTNAME,NATIONALID,BIRTHDATE,PHONENUMBER,ACCOUNTNUMBER,ACCOUNTTYPE) VALUES(?,?,?,?,?,?,?)";


        PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setString(1,account.getOwner().getFirstname());
        pstmt.setString(2,account.getOwner().getLastname());
        pstmt.setString(3,account.getOwner().getNationalid());
        pstmt.setDate(4,Date.valueOf(account.getOwner().getBirthdate().toString()));
        pstmt.setString(5,account.getOwner().getPhonenumber());
        pstmt.setInt(6,account.getAccountnumber());
        pstmt.setString(7,account.getAccounttype());

        pstmt.executeUpdate();


        String query1 = "INSERT INTO SAVINGACCOUNTS (NATIONALID,ACCOUNTNUMBER,RAMZEAVVAL,RAMZEDOVOM,ACCOUNTBALANCE,REGISTERDATE,MONTHLYPAYOUT,INTERESTRATE,HOLDDURATION,STATUS) VALUES (?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(query1);

        pstmt.setString(1,account.getOwner().getNationalid());
        pstmt.setInt(2,account.getAccountnumber());
        pstmt.setInt(3,account.getRamzeavval());
        pstmt.setInt(4,account.getRamzedovom());
        pstmt.setInt(5,account.getAccountbalance());
        pstmt.setDate(6,Date.valueOf(account.getRegisterdate().toString()));
        pstmt.setInt(7,account.getMonthlypayout());
        pstmt.setInt(8,account.getInterestrate());
        pstmt.setInt(9,account.getHoldduration());
        pstmt.setString(10,account.getStatus().toString());


        status=(pstmt.executeUpdate()==1?true:false);

        return status;
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

                status= (pstmt.executeUpdate()==1)?true:false;

                return  status;
            }

        public static void ShowTransactionrecords(int Accountnumber,int number)throws SQLException{

        //TODO:It's Better to Override toString Method,And Print the Records!

                Connection conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);



                String query = "SELECT * FROM TRANSACTIONS WHERE ACCOUNTNUMBER=? LIMIT ?";
                PreparedStatement pstmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                pstmt.setInt(1,Accountnumber);
                pstmt.setInt(2,number);
                ResultSet rs = pstmt.executeQuery();
                rs.last();
                int Countrows = rs.getRow();
                rs.beforeFirst();

                    while (rs.next()&&rs.getRow()<=Countrows){
                        String receipttype=rs.getString("RECEIPTTYPE").toUpperCase();
                        switch(receipttype){
                            case "DEPOSIT":
                                Deposit deposit = new Deposit(rs.getInt("ACCOUNTNUMBER"),rs.getInt("AMOUNT"),rs.getInt("ACCOUNTBALANCE"));
                                //deposit.setReceiptTime(rs.getTime("RECEIPTTIME"));
                                //deposit.setReceiptDate(rs.getDate("RECEIPTDATE").toLocalDate());
                                deposit.setDESCRIPTION(rs.getString("DESCRIPTION"));
                                out.println(deposit);
                                break;
                            case "WITHDRAW":
                                Withdraw withdraw = new Withdraw(rs.getInt("ACCOUNTNUMBER"),rs.getInt("AMOUNT"),rs.getInt("ACCOUNTBALANCE"));
                                withdraw.setDESCRIPTION(rs.getString("DESCRIPTION"));
                                out.println(withdraw);
                                break;
                            case "TRANSFER":
                                Transfer transfer = new Transfer(rs.getInt("ACCOUNTNUMBER"),rs.getInt("AMOUNT"),rs.getInt("SOURCE"),rs.getInt("DESTINATION"),rs.getInt("ACCOUNTBALANCE"));
                                transfer.setDESCRIPTION(rs.getString("DESCRIPTION"));
                                out.println(transfer);
                                break;
                        }
                        out.println("===============================================");

                    }
                }


    public static void main(String[] args) throws SQLException {
        ShowTransactionrecords(379823,10);
    }

}
