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

  //Operations.Transfer(2000,1234,5006,145000);
        //CheckingAccount account = new CheckingAccount.Checkingbuilder().setFirstname("Amin").setLastname("Ahmadpour").setPhonenumber(930023195).setBirthdate(1380,02,23).setNationalID(1810607418).setAccountnumber().setRamzeAvval().setRamzeDovom().setAccountype().setAccountbalance(520000).setRegistrationdate().getAccount();
       // Savingaccount account1 = new Savingaccount.Savingaccountbuilder().setFirstname("Amin").setLastname("Ahmadpour").setPhonenumber(930023195).setBirthdate(1380,02,23).setNationalID(1810607418).setAccountnumber().setRamzeAvval().setRamzeDovom().setAccountype().setAccountbalance(520000).setRegistrationdate().setTimeperiod(5).setInterestrate(18).getAccount();
       // account1.setPayoutamount();
        //out.println(account);
       // Query.InsertCustomersRecords(account);
        //Operations.Transfer(  714634,5487,3000,125000);
        Operations.Changepassword(2000);

        //out.println(RandomStringUtils.randomNumeric(6));
        //out.println(Query.Authentication(1000,1200));
        //System.out.println(Query.ShowInformation(1000).getString("FIRSTNAME"));
        //out.println( String.format(ex,"Amin"));

    }

}