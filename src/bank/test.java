package bank;
import bank.time.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.management.ServiceNotFoundException;
import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

import static java.lang.System.out;

public class test {



    public static void main(String[] args) throws SQLException, ServiceNotFoundException {

        Connection conn = DriverManager.getConnection(Query.DB_URL,Query.DB_USERNAME,Query.DB_PASSWORD);
        Query.UpdateRecords(3000,"ACCOUNTBALANCE","244000");



        //String.format(query,"Accountholders");
        //System.out.println(String.format("UPDATE %s SET ? = ? WHERE ACCOUNTNUMBER = ?"," ACCOUNTHOLDERS "));
       //out.println(query);

        //pstmt.setInt(1,3000);
       // boolean a = pstmt.execute();
       // ResultSet RS = Query.ShowInformation(3000);

    // System.out.println(Integer.valueOf("1515554424"));
        //Savingaccount account = new Savingaccount.Savingaccountbuilder().setFirstname("Mohammad").setLastname("Heydari").setAccountbalance(154000).setAccountnumber(3000).setAccountype().setNationalID(111111).setPhonenumber(999955554).setBirthdate(PersianDate.now()).setRamzeAvval(1234).setRamzeDovom(1155).setRegistrationdate(PersianDate.now()).setTimeperiod(5).setInterestrate(22).getAccount();
        //account.setPayoutamount();
        //Query.InsertCustomersRecords(account);
//
        /***
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false", "root", "13801380");
            Scanner input = new Scanner(System.in);
            PreparedStatement pstmt = conn.prepareStatement("select * from customersinfo where accountnumber=?");
            pstmt.setInt(1,1000);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            if(rs.getInt("RAMZEAVVAL")==2356){
                boolean access=true;
             System.out.println(access);

             }

            
        }
         ***/
    }

}