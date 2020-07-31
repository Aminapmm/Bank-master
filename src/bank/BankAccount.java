package bank;

import org.apache.commons.lang3.RandomStringUtils;
import bank.time.*;
import javax.management.ServiceNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class BankAccount {

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
    private PersianDate RegistrationDate;

    static Scanner input = new Scanner(System.in);


    public BankAccount(builder b) {


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


     static abstract class builder{

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


        public builder setFirstname(String firstname) {
            Firstname = firstname;
            return this;
        }

        public builder setLastname(String lastname) {
            Lastname = lastname;
            return this;
        }

        public builder setFathername(String fathername) {
            Fathername = fathername;
            return this;
        }

        public builder setNationalID(long nationalID) {
            NationalID = nationalID;
            return this;
        }

        public builder setPhonenumber(long phonenumber) {
            Phonenumber = phonenumber;
            return this;
        }

        public builder setAccountnumber(int accountnumber) {
            Accountnumber = accountnumber;
            return this;
        }

        public builder setBirthdate(PersianDate birthdate) {
            Birthdate = birthdate;
            return this;
        }

        public builder setRegistrationdate(PersianDate registrationdate) {
            Registrationdate = registrationdate;
            return this;
        }

        public builder setAccountype(String accountype) {
            Accountype = accountype;
            return this;
        }

        public builder setRamzeAvval(int ramzeAvval) {
            RamzeAvval = ramzeAvval;
            return this;
        }

        public builder setRamzeDovom(int ramzeDovom) {
            RamzeDovom = ramzeDovom;
            return this;
        }

        public builder setAccountbalance(int accountbalance) {
            Accountbalance = accountbalance;
            return this;
        }


        public  abstract BankAccount getAccount();

    }


}


     class Savingaccount extends BankAccount {

        private int Timeperiod;
        private int MONTHLY_PAYOUT_AMOUNT;
        private int Interestrate;

       private Savingaccount(Savingaccountbuilder Builder){
           super(Builder);
           this.Timeperiod=Builder.Timeperiod;
           this.MONTHLY_PAYOUT_AMOUNT=Builder.MONTHLY_PAYOUT_AMOUNT;
           this.Interestrate=Builder.Interestrate;
       }


        public int getTimeperiod(){
           return this.Timeperiod;
       }

        public int getMONTHLY_PAYOUT_AMOUNT() {
            return this.MONTHLY_PAYOUT_AMOUNT;
        }

        public int getInterestrate(){
           return this.Interestrate;
        }

         public void setPayoutamount(){
             this.MONTHLY_PAYOUT_AMOUNT = (this.Interestrate*super.getAccountbalance()*this.Timeperiod/100)/12;
         }

         //TODO:NOT COMPLETE
         @Override
         public String toString(){
           return String.format("Firstname: %s\nLastname: %s\nNationalID: %d\nBirthdate: %s\nPhonenumber: %d\nAccountnumber: %d\nAccountbalance: %d\nMonthlyPayout: %d\nInterestrate: %d\nHoldDuration: %d\nRamze-Avval: %d\nRamze-Dovom: %d\nRegisterdate: %s\n",this.getFirstname(),this.getLastname(),this.getNationalID(),this.getBirthdate(),this.getPhonenumber(),this.getAccountnumber(),this.getAccountbalance(),this.getMONTHLY_PAYOUT_AMOUNT(),this.getInterestrate(),this.getTimeperiod(),this.getRamzeAvval(),this.getRamzeAvval(),this.getRegistrationDate());
         }

        static class Savingaccountbuilder extends BankAccount.builder {

           private int Timeperiod;
           private int MONTHLY_PAYOUT_AMOUNT;
           private int Interestrate;


            public Savingaccountbuilder setpayoutamount(int amount){
                this.MONTHLY_PAYOUT_AMOUNT=amount;
                return this;
            }

            public Savingaccountbuilder setTimeperiod(int timeperiod){

               this.Timeperiod = timeperiod;
               return this;

           }

            //Annual Interest = The formula for Simple Interest (SI) is “principal x rate of interest x time period divided by 100” or (P x Rx T/100).
            //Monthly-interest = Annual/12

            @Override
            public Savingaccountbuilder setFirstname(String firstname) {
               super.setFirstname(firstname);
               return this;
            }

            @Override
            public Savingaccountbuilder setLastname(String lastname) {
                 super.setLastname(lastname);
                 return this;
            }

            @Override
            public Savingaccountbuilder setFathername(String fathername) {
                 super.setFathername(fathername);
                 return this;
            }

            @Override
            public Savingaccountbuilder setNationalID(long nationalID) {
                 super.setNationalID(nationalID);
                 return this;
            }

            @Override
            public Savingaccountbuilder setPhonenumber(long phonenumber) {
                super.setPhonenumber(phonenumber);
                return this;
            }


            public Savingaccountbuilder setAccountnumber() {
                int accountnumber = Integer.parseInt(RandomStringUtils.randomNumeric(6));
                super.setAccountnumber(accountnumber);
                return this;
            }

            public Savingaccountbuilder setBirthdate(int yy,int mm,int dd) {
                 PersianDate birthdate = PersianDate.of(yy, mm, dd);
                 super.setBirthdate(birthdate);
                 return this;
            }

            public Savingaccountbuilder setRegistrationdate() {
                PersianDate registrationdate = PersianDate.now();
                super.setRegistrationdate(registrationdate);
                return this;
            }


            public Savingaccountbuilder setAccountype() {
                super.setAccountype("SAVING");
                return this;
            }

            public Savingaccountbuilder setRamzeAvval() {

                 int ramzeAvval = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                 super.setRamzeAvval(ramzeAvval);
                 return this;
            }

            public Savingaccountbuilder setRamzeDovom() {

                 int ramzeDovom = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                 super.setRamzeDovom(ramzeDovom);
                 return this;
            }

            @Override
            public Savingaccountbuilder setAccountbalance(int accountbalance) {
                 super.setAccountbalance(accountbalance);
                 return this;
            }


            public Savingaccountbuilder setInterestrate(int interestrate) {
                this.Interestrate=interestrate;
                 return this;
            }


            @Override
            public Savingaccount getAccount() {
                Savingaccount savingaccount = new Savingaccount(this);
                return savingaccount;
            }
        }


    }

    class CheckingAccount extends BankAccount{


        private CheckingAccount(Checkingbuilder Builder){
            super(Builder);
        }

        @Override
        public String toString(){
            return String.format("Firstname: %s\nLastname: %s\nNationalID: %d\nBirthdate: %s\nAccountnumber: %d\nRamze-Avval: %d\nRamze-Dovom: %d\nRegisterdate: %s\nAccountbalance: %d \nPhonenumber: %d\n ",this.getFirstname(),this.getLastname(),this.getNationalID(),this.getBirthdate(),this.getAccountnumber(),this.getRamzeAvval(),this.getRamzeDovom(),this.getRegistrationDate(),this.getAccountbalance(),this.getPhonenumber());
        }

        static class Checkingbuilder extends builder{



            @Override
            public Checkingbuilder setFirstname(String firstname) {
                 super.setFirstname(firstname);
                 return this;
            }

            @Override
            public Checkingbuilder setLastname(String lastname) {
                 super.setLastname(lastname);
                 return this;
            }

            @Override
            public Checkingbuilder setFathername(String fathername) {
                super.setFathername(fathername);
                return this;
            }

            @Override
            public Checkingbuilder setNationalID(long nationalID) {
                super.setNationalID(nationalID);
                return this;
            }

            @Override
            public Checkingbuilder setPhonenumber(long phonenumber) {
                 super.setPhonenumber(phonenumber);
                 return this;
            }

            public Checkingbuilder setAccountnumber() {

                int accountnumber = Integer.parseInt(RandomStringUtils.randomNumeric(6));
                super.setAccountnumber(accountnumber);
                return this;
            }


            public Checkingbuilder setBirthdate(int yy,int mm,int dd) {
                PersianDate birthdate = PersianDate.of(yy,mm,dd);
                super.setBirthdate(birthdate);
                return this;
            }


            public Checkingbuilder setRegistrationdate() {

                 PersianDate registrationdate = PersianDate.now();
                 super.setRegistrationdate(registrationdate);
                 return this;
            }

            public Checkingbuilder setAccountype() {
                 super.setAccountype("CHECKING");
                 return this;
            }


            public Checkingbuilder setRamzeAvval() {

                int ramzeAvval = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                super.setRamzeAvval(ramzeAvval);
                return this;
            }

            public Checkingbuilder setRamzeDovom() {

                int ramzeDovom = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                super.setRamzeDovom(ramzeDovom);
                return this;
            }

            @Override
            public Checkingbuilder setAccountbalance(int accountbalance) {
                super.setAccountbalance(accountbalance);
                return this;
            }

            @Override
            public CheckingAccount getAccount(){
                return new CheckingAccount(this);
            }
        }
    }




