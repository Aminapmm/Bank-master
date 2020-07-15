package bank;
import bank.time.*;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Scanner;

public class test {



    public static void main(String[] args) {
        try {

            Scanner input = new Scanner(System.in);
          //  System.out.println(Query.OnlineAuthentication(1000, 5678));
          //  double current_amount = Query.ShowInformation(1000).getInt("amount");
            Query.UpdateRecords(5006,12,"155400");
            System.out.println(String.valueOf(5451));
           // System.out.println(Query.ShowInformation(2000).isFirst());
            //Deposit receipt = new Deposit(1000,50000,"Success");
            //receipt.Print_Receipt();
            int b =6;
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
