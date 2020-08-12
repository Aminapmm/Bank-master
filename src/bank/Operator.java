package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import bank.time.*;

public class Operator {

    private static Operator OperatorInstance;

    private Operator() {
    }

    public static Operator get_instance() {
        if (OperatorInstance == null) {
            return OperatorInstance = new Operator();
        }

        return OperatorInstance;
    }


    public Person FillPersonalinfo() throws SQLException {
        Scanner input = new Scanner(System.in);

        String firstname;
        String lastname;
        String phonenumber;
        int accountnumber;
        String nationalid;
        PersianDate birthdate;
        Person person;


        System.out.println("Enter Your Firstname:");
        firstname = input.next();
        System.out.println("Enter Your Lastname:");
        lastname = input.next();

        System.out.println("Enter Your National ID:");
        nationalid = input.next();
        Exceptions.nationalidcheck(nationalid);

        System.out.println("Enter Your Phonenumber:");
        phonenumber = input.next();
        Exceptions.phonenumbercheck(phonenumber);

        System.out.println("Enter Your Birthdate year:");
        int yy = input.nextInt();

        System.out.println("Enter Your Birthdate month:");
        int MM = input.nextInt();

        System.out.println("Enter Your Birthdate day:");
        int dd = input.nextInt();

        birthdate = PersianDate.of(yy, MM, dd);

        person = new Person.Personbuilder(firstname, lastname, nationalid, phonenumber, birthdate).getperson();
        return person;

    }


    public CheckingAccount buildCheckingAccount(Checkingaccountbuilder builder, Person customer) {
        Scanner input = new Scanner(System.in);
        builder.setOwner(customer);
        builder.setAccountnumber();
        builder.setRamzeavval();
        System.out.println("Enter Your First amount you wanna Save:");
        int balance = input.nextInt();
        builder.setAccountbalance(balance);
        builder.setAccountnumber();
        builder.setRamzeavval();
        builder.setRamzedovom();
        builder.setType();
        return builder.getAccount();
    }

    public Savingaccount buildSavingaccount(Savingaccountbuilder builder, Person customer) {

        Scanner input = new Scanner(System.in);
        builder.setOwner(customer);
        builder.setAccountnumber();
        builder.setRamzeavval();
        System.out.println("Enter Your First amount you wanna Save:");
        int balance = input.nextInt();
        builder.setAccountbalance(balance);
        builder.setAccountnumber();
        builder.setRamzeavval();
        builder.setRamzedovom();
        builder.setType();
        System.out.println("Enter Interest rate:");
        int interestrate = input.nextInt();
        builder.setInterestrate(interestrate);
        System.out.println("How long you want to save your principle:");
        int holdduration = input.nextInt();
        builder.setHoldDuration(holdduration);
        builder.setMonthlypayout();
        return builder.getaccount();


    }



}

