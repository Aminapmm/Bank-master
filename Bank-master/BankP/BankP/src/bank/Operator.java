package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import bank.java.com.github.mfathi91.time.PersianDate;

public class Operator {

    private static Operator OperatorInstance;
    private Operator(){};
    public static Operator get_instance(){
        if(OperatorInstance==null){
            return OperatorInstance=new Operator();
        }

        return OperatorInstance;
    }



    public static BankAccount buildaccount(int Case) {

        String firstname = "";
        String lastname = "";
        String fathername = "";
        long phonenumber = 0;
        long accountnumber = 0;
        long nationalid = 0;
        PersianDate birthdate;
        // PersianDate registrationdate;
        String accountype;
        // int ramzeavval;
        // int ramzedovom;
        double amount;
        double interestrate;

        BankAccount.builder Builder ;
        Builder = new BankAccount.builder();

        switch (Case) {



            case (1):

            Scanner input = new Scanner(System.in);
            System.out.println("Enter Your Firstname:");
            firstname = input.next();
            System.out.println("Enter Your Lastname:");
            lastname = input.next();
            System.out.println("Enter you Fathername:");
            fathername = input.next();
            System.out.println("Enter Your National ID:");
            nationalid = input.nextLong();
            System.out.println("Enter Your Phonenumber:");
            phonenumber = input.nextLong();

            System.out.println("Enter Your Birthdate (YEAR-MONTH-DAY):");

            String birthdate1 = input.next();

            int yy = Integer.parseInt(birthdate1, 0, 4, 1);

            int MM = Integer.parseInt(birthdate1, 5, 7, 1);

            int dd = Integer.parseInt(birthdate1, 8, 10, 1);

            //birthdate = PersianDate.of(yy,MM,dd);

            System.out.println("Enter Your Account TYPE:");

            accountype = input.next();

            System.out.println("Enter Your Interest RATE:");

            interestrate = input.nextDouble();

            System.out.println("Enter Your Amount:");

            amount = input.nextDouble();


            Builder.setFirstname(firstname);
            Builder.setLastname(lastname);
            Builder.setFathertname(fathername);
            Builder.setNationalID(nationalid);
            Builder.setPhonenumber(phonenumber);
            Builder.setBirthdate(yy, MM, dd);
            Builder.setFirstname(firstname);
            Builder.setAccounttype(accountype);
            Builder.setInterestrate(interestrate);
            Builder.setAmount(amount);
            Builder.GenerateAccountnumber();
            Builder.GenerateRamzeAvval();
            Builder.GenerateRamzeDovom();
            Builder.setRegistrationdate();

            //return Builder.getAccount();

            case (2):
                try {
                    Connection conn;
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bankaccounts", "root", "13801380");

                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                int a = 1000;
                //Builder.setFirstname();
                /***
                Builder.setLastname(lastname);
                Builder.setFathertname(fathername);
                Builder.setNationalID(nationalid);
                Builder.setPhonenumber(phonenumber);
                Builder.setBirthdate(yy, MM, dd);
                Builder.setFirstname(firstname);
                Builder.setAccounttype(accountype);
                Builder.setInterestrate(interestrate);
                Builder.setAmount(amount);
                Builder.GenerateAccountnumber();
                Builder.GenerateRamzeAvval();
                Builder.GenerateRamzeDovom();
                Builder.setRegistrationdate();

***/
                //return Builder.getAccount();
        }
        return Builder.getAccount();
    }
}
