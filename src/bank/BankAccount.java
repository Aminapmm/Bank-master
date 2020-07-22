package bank;

import org.apache.commons.lang3.RandomStringUtils;

import bank.time.*;

import java.math.BigDecimal;
import java.nio.charset.CharsetEncoder;
import java.sql.SQLException;
import java.util.Currency;
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
    private double Interestrate;
    private PersianDate RegistrationDate;

    static Scanner input = new Scanner(System.in);



    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setFathername(String fathername) {
        Fathername = fathername;
    }

    public void setNationalID(long nationalID) {
        NationalID = nationalID;
    }

    public void setPhonenumber(long phonenumber) {
        Phonenumber = phonenumber;
    }

    public void setAccountnumber(int accountnumber) {
        Accountnumber = accountnumber;
    }

    public void setBirthdate(PersianDate birthdate) {
        Birthdate = birthdate;
    }

    public void setAccountype(String accountype) {
        Accountype = accountype;
    }

    public void setRamzeAvval(int ramzeAvval) {
        RamzeAvval = ramzeAvval;
    }

    public void setRamzeDovom(int ramzeDovom) {
        RamzeDovom = ramzeDovom;
    }

    public void setAccountbalance(int accountbalance) {
        Accountbalance = accountbalance;
    }

    public void setInterestrate(double interestrate) {
        Interestrate = interestrate;
    }

    public void setRegistrationDate(PersianDate registrationDate) {
        RegistrationDate = registrationDate;
    }


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



    //TODO:fix these two subclass



    public static void main(String[] args) throws SQLException {

       // BankAccount b = new Savingaccount.Savingaccountbuilder().getAccount();
        //BankAccount A = new Savingaccount(b);
       // builder b = new builder();


        //System.out.println(s.getAccountype());
    //BankAccount.Transfer(1000,5678,2000,184000);
        //Operations.Deposit(1000,2356,5000);
        //BankAccount.Withdraw(1000,2356,4000000);
       // System.out.println(Query.OnlineAuthentication(1000,5678));
       // System.out.println(Query.ShowInformation(1000).isFirst());
        //Checkingaccount account1 = new Checkingaccount.Checkingaccountbuilder().setLastname("ahmadi").getAccount();



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
        private double Interestrate;

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

        public builder setInterestrate(double interestrate) {
            Interestrate = interestrate;
            return this;
        }

        public  abstract BankAccount getAccount();


    }





}

class Savingaccount extends BankAccount {
    private int Periodtime;

   private Savingaccount(Savingaccountbuilder Builder){
       super(Builder);
   }

    public static void main(String[] args) {
        Savingaccount savingaccount = new Savingaccountbuilder().setLastname("amin").getAccount();
        System.out.println(savingaccount.getLastname());

    }



    public void calculateinterest() {}


    static class Savingaccountbuilder extends BankAccount.builder {


        @Override
        public Savingaccountbuilder setFirstname(String firstname) {
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

        @Override
        public Savingaccountbuilder setAccountnumber(int accountnumber) {
            super.setAccountnumber(accountnumber);
            return this;
        }

        @Override
        public Savingaccountbuilder setBirthdate(PersianDate birthdate) {
             super.setBirthdate(birthdate);
             return this;
        }

        @Override
        public Savingaccountbuilder setRegistrationdate(PersianDate registrationdate) {
            super.setRegistrationdate(registrationdate);
            return this;
        }

        @Override
        public Savingaccountbuilder setAccountype(String accountype) {
            super.setAccountype(accountype);
            return this;
        }

        @Override
        public Savingaccountbuilder setRamzeAvval(int ramzeAvval) {
             super.setRamzeAvval(ramzeAvval);
             return this;
        }

        @Override
        public Savingaccountbuilder setRamzeDovom(int ramzeDovom) {
             super.setRamzeDovom(ramzeDovom);
             return this;
        }

        @Override
        public Savingaccountbuilder setAccountbalance(int accountbalance) {
             super.setAccountbalance(accountbalance);
             return this;
        }

        @Override
        public Savingaccountbuilder setInterestrate(double interestrate) {
             super.setInterestrate(interestrate);
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

    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount.Checkingbuilder().setAccountbalance(1151511).getAccount();
        System.out.println(account.getAccountbalance());
    }

    private CheckingAccount(Checkingbuilder Builder){
        super(Builder);
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

        @Override
        public Checkingbuilder setAccountnumber(int accountnumber) {
            super.setAccountnumber(accountnumber);
            return this;
        }

        @Override
        public Checkingbuilder setBirthdate(PersianDate birthdate) {
            super.setBirthdate(birthdate);
            return this;
        }

        @Override
        public Checkingbuilder setRegistrationdate(PersianDate registrationdate) {
             super.setRegistrationdate(registrationdate);
             return this;
        }

        @Override
        public Checkingbuilder setAccountype(String accountype) {
             super.setAccountype(accountype);
             return this;
        }

        @Override
        public Checkingbuilder setRamzeAvval(int ramzeAvval) {
            super.setRamzeAvval(ramzeAvval);
            return this;
        }

        @Override
        public Checkingbuilder setRamzeDovom(int ramzeDovom) {
            super.setRamzeDovom(ramzeDovom);
            return this;
        }

        @Override
        public Checkingbuilder setAccountbalance(int accountbalance) {
            super.setAccountbalance(accountbalance);
            return this;
        }

        @Override
        public Checkingbuilder setInterestrate(double interestrate) {
            super.setInterestrate(interestrate);
            return this;
        }

        @Override
        public CheckingAccount getAccount(){
            return new CheckingAccount(this);
        }
    }
}




