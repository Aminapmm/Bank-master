package bank;
import bank.time.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class test {



    public static void main(String[] args) throws SQLException {

   //BankAccount account = new  BankAccount.builder().setFirstname("Mohammad").setLastname("Heydari").setNationalID(4657798).setAccounttype("Checking").GenerateAccountnumber().GenerateRamzeAvval().setPhonenumber(01151415515).setInterestrate(15).setAccountbalance(2000000).setRegistrationdate().setBirthdate(78,01,01).getAccount();
   //System.out.println(Query.InsertCustomersRecords(account));
    //System.out.println(account.getAccountnumber());

        // final String DB_URL = "jdbc:mysql://localhost/bankaccounts?useLegacyDatetimeCode=false";
       //  final String DB_USERNAME = "root";
        // final String DB_PASSWORD = "13801380";
        // Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);


        //int a = Integer.parseInt(RandomStringUtils.randomNumeric(8));
    //  System.out.println(a);
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