package bank;

import com.mysql.cj.xdevapi.SqlDataResult;

import java.sql.*;
import java.util.Scanner;

/***
 * Some Simple Exceptions for nationalid&phonenumber input.
 * it checks that input length is 10 or not.
 */

public class Exceptions {

    public static class Badphonenumber extends RuntimeException {
        public Badphonenumber() {
            super("Phone number is incorrect.");
        }
    }

    public static class Badnationalid extends RuntimeException{
        public Badnationalid(){
            super("Bad NationalID,check it.");
        }
    }

    public static class Accountnotexist extends RuntimeException{
        public Accountnotexist(){ super("Sorry This Account not found in our Database."); }
    }

    public static class CheckAccountState extends RuntimeException{
        public CheckAccountState(){ super("Unfortunately Your Account is Limited For Some Reasons,Contact the Support. ");}
    }

    public static void phonenumbercheck(String phonenumber) {
        if (phonenumber.length() != 10) {
            throw new Badphonenumber();
        }

    }

    public static void nationalidcheck(String id){
        if(id.length() != 10){
            throw new Badnationalid();
        }
    }

    public static void accountexist(int accountnumber)throws SQLException {

        Connection connection = DriverManager.getConnection(Query.DB_URL,Query.DB_USERNAME,Query.DB_PASSWORD);
        String query = "SELECT * FROM ACCOUNTHOLDERS WHERE ACCOUNTNUMBER = ?";
        PreparedStatement pstmt = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1,accountnumber);
        ResultSet rs = pstmt.executeQuery();
        rs.last();
        if(rs.getRow()==0){
            throw new Accountnotexist();
        }

    }

    public static void Checkstate(int accountnumber)throws SQLException{
        Connection connection = DriverManager.getConnection(Query.DB_URL,Query.DB_USERNAME,Query.DB_PASSWORD);
        String accounttype  = Query.ShowInformation(accountnumber).getString("ACCOUNTTYPE")+"ACCOUNTS";
        String query = String.format("SELECT STATUS FROM %s WHERE ACCOUNTNUMBER = ?",accounttype);
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1,accountnumber);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if(rs.getString("STATUS")!="OPEN"){
            throw new CheckAccountState();
        }
    }

    public static void main(String[] args) throws SQLException{
        //accountexist(93448);
        Checkstate(934048);
    }
}