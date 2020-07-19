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
            PreparedStatement pstmt = conn.prepareStatement("select * from customersinfo where accountnumber=?");
            pstmt.setInt(1,1000);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            if(rs.getInt("RAMZEAVVAL")==2356){
                boolean access=true;
             System.out.println(access);



             }


            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
