package bank;

import org.apache.commons.lang3.RandomStringUtils;

import bank.time.*;

import java.sql.SQLException;
import java.util.Scanner;



public class BankAccount {

    private String Firstname;
    private String Lastname;
    private String Fathername;
    private long NationalID;
    private long Phonenumber;
    private int Accountnumber;
    private PersianDate Birthdate;
    private String Accountype;
    private int RamzeAvval;
    private int RamzeDovom;
    private int Accountbalance;
    private double Interestrate;
    private PersianDate RegistrationDate;

    static Scanner input = new Scanner(System.in);


    private BankAccount(builder b) {


            this.Firstname = b.Firstname;


            this.Lastname = b.Lastname;


            this.Fathername = b.Fathername;


            this.NationalID = b.NationalID;


            this.Phonenumber = b.Phonenumber;


            this.Birthdate = b.Birthdate;


            this.Accountype = b.Accountype;

            this.Accountnumber = b.Accountnumber;


            this.Accountbalance = b.Accountbalance;


            this.RamzeAvval = b.RamzeAvval;


            this.RamzeDovom = b.RamzeDovom;

            this.RegistrationDate = b.Registrationdate;

    }


    public String getFirstname () {
        return this.Firstname;
    }


    public double getInterestrate () {
        return this.Interestrate;
    }


    public String getLastname () {
        return this.Lastname;
    }



    public String getFathername () {
        return this.Fathername;
    }



    public long getNationalID () {
        return this.NationalID;
    }



    public long getPhonenumber () {
        return this.Phonenumber;
    }


    public PersianDate getBirthdate () {
        return this.Birthdate;
    }

    public int getAccountnumber() {
        return this.Accountnumber;
    }

    public String getAccountype () {
        return this.Accountype;
    }



    public int getAccountbalance () {
        return this.Accountbalance;
    }



    public int getRamzeAvval () {
        return this.RamzeAvval;
    }



    public int getRamzeDovom () {
        return this.RamzeDovom;
    }

    public PersianDate getRegistrationDate() {
        return RegistrationDate;
    }



    public static class builder{

        private String Firstname;
        private String Lastname;
        private String Fathername;
        private long NationalID;
        private long Phonenumber;
        private int Accountnumber;
        private PersianDate Birthdate;
        private PersianDate Registrationdate;
        private String Accountype;
        private int RamzeAvval;
        private int RamzeDovom;
        private int Accountbalance;
        private double Interestrate;


        public builder setFirstname(String Firstname) {
            this.Firstname = Firstname;
            return this;
        }



        public builder setLastname(String Lastname) {
            this.Lastname = Lastname;
            return this;
        }



        public builder setFathertname(String fathertname) {
            this.Fathername = fathertname;
            return this;
        }



        public builder setNationalID(long NationalID) {
            this.NationalID = NationalID;
            return this;
        }



        public builder setPhonenumber(long Phonenumber) {
            this.Phonenumber = Phonenumber;
            return this;
        }



        public builder setBirthdate(int yy, int MM, int dd) {
            this.Birthdate = PersianDate.of(yy,MM,dd);
            return this;
        }

        public builder setRegistrationdate(){
            this.Registrationdate=PersianDate.now();
            return this;
        }

        public builder setAccountnumber(int Accountnumber){
            this.Accountnumber=Accountnumber;
            return this;
        }



        public builder setAccounttype(String Type) {
            this.Accountype = Type;
            return this;
        }



        public builder setAccountbalance(int Accountbalance) {
            this.Accountbalance = Accountbalance;
            return this;
        }



        public builder setRamzeAvval(int RamzeAvval) {
            this.RamzeAvval = RamzeAvval;
            return this;
        }



        public builder setRamzeDovom(int RamzeDovom) {
            this.RamzeDovom = RamzeDovom;
            return this;
        }



        public builder setInterestrate(double Interstrate) {
            this.Interestrate = Interstrate;
            return this;
        }


        public builder GenerateAccountnumber(){
            this.Accountnumber=Integer.parseInt(RandomStringUtils.randomNumeric(8));
            return this;
        }

        public builder GenerateRamzeAvval() {
            this.RamzeAvval=Integer.parseInt(RandomStringUtils.randomNumeric(4));
            return this;
        }



        public builder GenerateRamzeDovom() {
            this.RamzeDovom=Integer.parseInt(RandomStringUtils.randomNumeric(8));
            return this;
        }


        public BankAccount getAccount(){return new BankAccount(this);}


    }



    public static void main(String[] args) throws SQLException {

    //BankAccount.Transfer(1000,5678,2000,184000);
        Operations.Deposit(1000,2356,5000);
        //BankAccount.Withdraw(1000,2356,4000000);
       // System.out.println(Query.OnlineAuthentication(1000,5678));
       // System.out.println(Query.ShowInformation(1000).isFirst());



    }
}