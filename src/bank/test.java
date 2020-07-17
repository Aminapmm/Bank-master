package bank;
import bank.time.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class test {



    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/Bankaccounts?useLegacyDatetimeCode=false", "root", "13801380");
            Scanner input = new Scanner(System.in);
           // DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
           // String DATETIME = PersianDate.now()+" "+LocalTime.now().format(format);
          //  System.out.println(Timestamp.valueOf(DATETIME));
            //Query.InsertTransactionsRecord(1000,"Withdraw",16000,0,0,"KHARID AZ SUPERMARKET");
            //Timestamp.valueOf();

            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
