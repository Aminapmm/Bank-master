package bank;

import org.apache.commons.lang3.RandomStringUtils;

import bank.time.*;
import java.time.LocalTime;


public class BankAccount {
    private String Firstname;
    private String Lastname;
    private String Fathername;
    private long NationalID;
    private long Phonenumber;
    private long Accountnumber;
    private PersianDate Birthdate;
    private String Accountype;
    private int RamzeAvval;
    private int RamzeDovom;
    private double Amount;
    private double Interestrate;
    private PersianDate RegistrationDate;


    private BankAccount(builder b) {


            this.Firstname = b.Firstname;


            this.Lastname = b.Lastname;


            this.Fathername = b.Fathername;


            this.NationalID = b.NationalID;


            this.Phonenumber = b.Phonenumber;


            this.Birthdate = b.Birthdate;


            this.Accountype = b.Accountype;


            this.Amount = b.Amount;


            this.RamzeAvval = b.RamzeAvval;


            this.RamzeDovom = b.RamzeDovom;

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



    public String getAccountype () {
        return this.Accountype;
    }



    public double getAmount () {
        return this.Amount;
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
        private long Accountnumber;
        private PersianDate Birthdate;
        private PersianDate Registrationdate;
        private String Accountype;
        private int RamzeAvval;
        private int RamzeDovom;
        private double Amount;
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

        public builder setAccountnumber(long Accountnumber){
            this.Accountnumber=Accountnumber;
            return this;
        }


        public builder setAccounttype(String Type) {
            this.Accountype = Type;
            return this;
        }



        public builder setAmount(double Amount) {
            this.Amount = Amount;
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
            this.Accountnumber=Integer.parseInt(RandomStringUtils.randomNumeric(10));
            return this;
        }

        public void GenerateRamzeAvval() {
            this.RamzeAvval=Integer.parseInt(RandomStringUtils.randomNumeric(4));

        }




        public void GenerateRamzeDovom() {
            this.RamzeDovom=Integer.parseInt(RandomStringUtils.randomNumeric(8));
        }


        public BankAccount getAccount(){return new BankAccount(this);}



    }






        public static  void Transfer(long Source,int ramzedovom,long Destination,double amount){
        //TODO
            boolean Status=false;
            boolean auth=false;
            auth=Query.OnlineAuthentication(Source,ramzedovom);
                try {
                    if (auth == true && amount<Query.ShowInformation(Source).getInt("amount")) {
                        if (Query.ShowInformation(Destination).isFirst() == true) {
                            System.out.printf("Are You Sure that you want to transfer  %s  to \n %s  %s's Account?",amount,Query.ShowInformation(Destination).getString("firstname"),Query.ShowInformation(Destination).getString("lastname"));
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }



        }

        public void Receipt(int Amount,String Type,long source,long destination){
            LocalTime time = LocalTime.now();
            PersianDate date=PersianDate.now();
            System.out.printf("account number:\n");

        }

    public static void main(String[] args) {

    BankAccount.Transfer(1000,5678,2000,5400);


    }
}