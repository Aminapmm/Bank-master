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

  Operations.Transfer(2000,1234,5006,145000);
    //System.out.println(Query.ShowInformation(1000).getString("FIRSTNAME"));
        //out.println( String.format(ex,"Amin"));

    }

}