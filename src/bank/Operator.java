package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import bank.time.*;

public class Operator {

    private static Operator OperatorInstance;

    private Operator() {}

    public static Operator get_instance() {
        if (OperatorInstance == null) {
            return OperatorInstance = new Operator();
        }

        return OperatorInstance;
    }


    public void buildaccount() throws SQLException {

                String firstname ;
                String lastname ;
                String fathername ;
                String phonenumber ;
                int accountnumber ;
                String nationalid ;
                PersianDate birthdate;
                // PersianDate registrationdate;
                String accountype;
                // int ramzeavval;
                // int ramzedovom;
                int accountbalance;
                int interestrate;


                Scanner input = new Scanner(System.in);
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
                int yy =input.nextInt();

                System.out.println("Enter Your Birthdate month:");
                int MM =input.nextInt();

                System.out.println("Enter Your Birthdate day:");
                int dd = input.nextInt();

                birthdate = PersianDate.of(yy,MM,dd);

                System.out.printf("What type of Account do you want to open:\n1)Checking\n2)Saving\n");
                accountype = input.next().toUpperCase();

                System.out.println("Enter Your Amount:");

                accountbalance = input.nextInt();


        switch (accountype) {

            case "SAVING":

            System.out.println("Enter Your Interest RATE:");

            interestrate = input.nextInt();

            System.out.println("How long you want to keep your money in the bank?");

            int time_period = input.nextInt();

                Savingaccount.Savingaccountbuilder savingaccountbuilder = new Savingaccount.Savingaccountbuilder();
                savingaccountbuilder.setFirstname(firstname);
                savingaccountbuilder.setLastname(lastname);
                savingaccountbuilder.setNationalID(nationalid);
                savingaccountbuilder.setPhonenumber(phonenumber);
                savingaccountbuilder.setBirthdate(yy,MM,dd);
                savingaccountbuilder.setAccountnumber();
                savingaccountbuilder.setRamzeAvval();
                savingaccountbuilder.setRamzeDovom();
                savingaccountbuilder.setRegistrationdate();
                savingaccountbuilder.setInterestrate(interestrate);
                savingaccountbuilder.setTimeperiod(time_period);
                savingaccountbuilder.setAccountbalance(accountbalance);
                savingaccountbuilder.setAccountype();
                savingaccountbuilder.setStatus();
                Savingaccount savingaccount = savingaccountbuilder.getAccount();
                savingaccount.setPayoutamount();
                System.out.println(savingaccount);
                Query.InsertCustomersRecords(savingaccount);
                break;

                case "CHECKING":

                CheckingAccount.Checkingbuilder Checkingaccountbuilder = new CheckingAccount.Checkingbuilder();
                Checkingaccountbuilder.setFirstname(firstname);
                Checkingaccountbuilder.setLastname(lastname);
                Checkingaccountbuilder.setNationalID(nationalid);
                Checkingaccountbuilder.setBirthdate(yy,MM,dd);
                Checkingaccountbuilder.setPhonenumber(phonenumber);
                Checkingaccountbuilder.setAccountbalance(accountbalance);
                Checkingaccountbuilder.setAccountype(accountype);
                Checkingaccountbuilder.setRegistrationdate();
                Checkingaccountbuilder.setAccountnumber();
                Checkingaccountbuilder.setRamzeAvval();
                Checkingaccountbuilder.setRamzeDovom();
                Checkingaccountbuilder.setStatus();
                CheckingAccount checkingAccount = Checkingaccountbuilder.getAccount();
                System.out.println(checkingAccount);
                Query.InsertCustomersRecords(checkingAccount);
                break;
        }

    }

    public static void main(String[] args) throws SQLException {
        Operator op = Operator.get_instance();
        op.buildaccount();
    }

}
