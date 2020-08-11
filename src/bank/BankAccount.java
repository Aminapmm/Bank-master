package bank;

import org.apache.commons.lang3.RandomStringUtils;
import bank.time.*;

import java.util.Scanner;


 abstract class BankAccount {

    private String Firstname;
    private String Lastname;
    private String Fathername;
    private String NationalID;
    private String Phonenumber;
    private int Accountnumber;
    private PersianDate Birthdate;
    private String Accountype;
    private int RamzeAvval;
    private int RamzeDovom;
    private int Accountbalance;
    private PersianDate RegistrationDate;
    private String Status;
    private final int MIN_ACCOUNTBALANCE=50000;

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

            if(b.Accountbalance>=MIN_ACCOUNTBALANCE) {
                this.Accountbalance = b.Accountbalance;
            }

            this.RamzeAvval = b.RamzeAvval;


            this.RamzeDovom = b.RamzeDovom;

            this.RegistrationDate = b.Registrationdate;

            this.Status = b.Status;

    }


     String getFirstname () {
        return this.Firstname;
    }




     String getLastname () {
        return this.Lastname;
    }



     String getFathername () {
        return this.Fathername;
    }



    String getNationalID () {
        return this.NationalID;
    }



     String getPhonenumber () {
        return this.Phonenumber;
    }


     PersianDate getBirthdate () {
        return this.Birthdate;
    }

     int getAccountnumber() {
        return this.Accountnumber;
    }

     String getAccountype () {
        return this.Accountype;
    }



     int getAccountbalance () {
        return this.Accountbalance;
    }



     int getRamzeAvval () {
        return this.RamzeAvval;
    }

     String getStatus(){return this.Status;}

     int getRamzeDovom () {
        return this.RamzeDovom;
    }

     PersianDate getRegistrationDate() {
        return RegistrationDate;
    }


     static abstract class builder{

        private String Firstname;
        private String Lastname;
        private String Fathername;
        private String NationalID;
        private String Phonenumber;
        private int Accountnumber;
        private PersianDate Birthdate;
        private PersianDate Registrationdate;
        private String Accountype;
        private int RamzeAvval;
        private int RamzeDovom;
        private int Accountbalance;
        private String Status;


         builder setStatus(){
            this.Status="Open";
            return this;
        }
         builder setFirstname(String firstname) {
            Firstname = firstname;
            return this;
        }

         builder setLastname(String lastname) {
            Lastname = lastname;
            return this;
        }

         builder setFathername(String fathername) {
            Fathername = fathername;
            return this;
        }

         builder setNationalID(String nationalID) {
            NationalID = nationalID;
            return this;
        }

         builder setPhonenumber(String phonenumber) {
            Phonenumber = phonenumber;
            return this;
        }

         builder setAccountnumber(int accountnumber) {
            Accountnumber = accountnumber;
            return this;
        }

         builder setBirthdate(PersianDate birthdate) {
            Birthdate = birthdate;
            return this;
        }

         builder setRegistrationdate(PersianDate registrationdate) {
            Registrationdate = registrationdate;
            return this;
        }

         builder setAccountype(String accountype) {
            Accountype = accountype;
            return this;
        }

         builder setRamzeAvval(int ramzeAvval) {
            RamzeAvval = ramzeAvval;
            return this;
        }

         builder setRamzeDovom(int ramzeDovom) {
            RamzeDovom = ramzeDovom;
            return this;
        }

         builder setAccountbalance(int accountbalance) {
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


         int getTimeperiod(){
           return this.Timeperiod;
       }

         int getMONTHLY_PAYOUT_AMOUNT() {
            return this.MONTHLY_PAYOUT_AMOUNT;
        }

         int getInterestrate(){
           return this.Interestrate;
        }

         void setPayoutamount(){
             this.MONTHLY_PAYOUT_AMOUNT = (this.Interestrate*super.getAccountbalance()*this.Timeperiod/100)/12;
         }

         @Override
         public String toString(){
           return String.format("Firstname: %s\nLastname: %s\nNationalID: %s\nBirthdate: %s\nPhonenumber: %s\nAccountnumber: %d\nAccountbalance: %d\nMonthlyPayout: %d\nInterestrate: %d\nHoldDuration: %d\nRamze-Avval: %d\nRamze-Dovom: %d\nRegisterdate: %s\n",this.getFirstname(),this.getLastname(),this.getNationalID(),this.getBirthdate(),this.getPhonenumber(),this.getAccountnumber(),this.getAccountbalance(),this.getMONTHLY_PAYOUT_AMOUNT(),this.getInterestrate(),this.getTimeperiod(),this.getRamzeAvval(),this.getRamzeDovom(),this.getRegistrationDate());
         }

        static class Savingaccountbuilder extends BankAccount.builder {

           private int Timeperiod;
           private int MONTHLY_PAYOUT_AMOUNT;
           private int Interestrate;


             Savingaccountbuilder setpayoutamount(int amount){
                this.MONTHLY_PAYOUT_AMOUNT=amount;
                return this;
            }

             Savingaccountbuilder setTimeperiod(int timeperiod){

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
            public Savingaccountbuilder setNationalID(String nationalID) {
                 super.setNationalID(nationalID);
                 return this;
            }

            @Override
            public Savingaccountbuilder setPhonenumber(String phonenumber) {
                super.setPhonenumber(phonenumber);
                return this;
            }


             Savingaccountbuilder setAccountnumber() {
                int accountnumber = Integer.parseInt(RandomStringUtils.randomNumeric(6));
                super.setAccountnumber(accountnumber);
                return this;
            }

             Savingaccountbuilder setBirthdate(int yy,int mm,int dd) {
                 PersianDate birthdate = PersianDate.of(yy, mm, dd);
                 super.setBirthdate(birthdate);
                 return this;
            }

             Savingaccountbuilder setRegistrationdate() {
                PersianDate registrationdate = PersianDate.now();
                super.setRegistrationdate(registrationdate);
                return this;
            }


             Savingaccountbuilder setAccountype() {
                super.setAccountype("SAVING");
                return this;
            }

             Savingaccountbuilder setRamzeAvval() {

                 int ramzeAvval = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                 super.setRamzeAvval(ramzeAvval);
                 return this;
            }

             Savingaccountbuilder setRamzeDovom() {

                 int ramzeDovom = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                 super.setRamzeDovom(ramzeDovom);
                 return this;
            }

            @Override
            public Savingaccountbuilder setAccountbalance(int accountbalance) {
                 super.setAccountbalance(accountbalance);
                 return this;
            }


             Savingaccountbuilder setInterestrate(int interestrate) {
                this.Interestrate=interestrate;
                 return this;
            }

            public Savingaccountbuilder setStatus(){
                super.setStatus();
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
            return String.format("Firstname: %s\nLastname: %s\nNationalID: %s\nBirthdate: %s\nAccountnumber: %d\nRamze-Avval: %d\nRamze-Dovom: %d\nRegisterdate: %s\nAccountbalance: %d \nPhonenumber: %s\n ",this.getFirstname(),this.getLastname(),this.getNationalID(),this.getBirthdate(),this.getAccountnumber(),this.getRamzeAvval(),this.getRamzeDovom(),this.getRegistrationDate(),this.getAccountbalance(),this.getPhonenumber());
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
            public Checkingbuilder setNationalID(String nationalID) {
                super.setNationalID(nationalID);
                return this;
            }

            @Override
            public Checkingbuilder setPhonenumber(String phonenumber) {
                 super.setPhonenumber(phonenumber);
                 return this;
            }

             Checkingbuilder setAccountnumber() {

                int accountnumber = Integer.parseInt(RandomStringUtils.randomNumeric(6));
                super.setAccountnumber(accountnumber);
                return this;
            }


             Checkingbuilder setBirthdate(int yy,int mm,int dd) {
                PersianDate birthdate = PersianDate.of(yy,mm,dd);
                super.setBirthdate(birthdate);
                return this;
            }

            public Checkingbuilder setStatus(){
                super.setStatus();
                return this;
            }


            Checkingbuilder setRegistrationdate() {

                 PersianDate registrationdate = PersianDate.now();
                 super.setRegistrationdate(registrationdate);
                 return this;
            }

            public Checkingbuilder setAccountype() {
                 super.setAccountype("CHECKING");
                 return this;
            }


             Checkingbuilder setRamzeAvval() {

                int ramzeAvval = Integer.parseInt(RandomStringUtils.randomNumeric(4));
                super.setRamzeAvval(ramzeAvval);
                return this;
            }

             Checkingbuilder setRamzeDovom() {

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




