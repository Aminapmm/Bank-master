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
    private long Accountnumber;
    private PersianDate Birthdate;
    private String Accountype;
    private int RamzeAvval;
    private int RamzeDovom;
    private int Amount;
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



    public int getAmount () {
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
        private int Amount;
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



        public builder setAmount(int Amount) {
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


        public static void Transfer(int accountnumber, int ramzedovom, int Destination, int amount){
        //TODO

            boolean auth=false;
            int Accountbalance;
            auth=Query.OnlineAuthentication(accountnumber,ramzedovom);

                try {
                    if (auth == true) {

                        Accountbalance = Query.ShowInformation(accountnumber).getInt("amount");


                        if (Query.ShowInformation(Destination).isFirst() == true&&amount<Accountbalance) {
                            System.out.printf("Are You Sure that you want to transfer  %s  to \n %s  %s's Account?",amount,Query.ShowInformation(Destination).getString("firstname"),Query.ShowInformation(Destination).getString("lastname"));
                            String Decision= input.next().toUpperCase();
                            if(Decision.equals("YES")){

                                Accountbalance=Accountbalance-amount;

                                Query.UpdateRecords(accountnumber,12,Integer.toString(Accountbalance));
                                Query.InsertTransactionsRecord(accountnumber,"TRANSFER",amount,0,Destination,String.format("%d Tomans Has been Transfered from THIS ACCOUNT.",amount),Accountbalance);
                                Transfer receipt = new Transfer(accountnumber,Destination,amount,Accountbalance,"Done");

                                Accountbalance=Query.ShowInformation(Destination).getInt("amount")+amount;

                                Query.UpdateRecords(Destination,12,Integer.toString(Accountbalance));
                                Query.InsertTransactionsRecord(Destination,"TRANSFER",amount,accountnumber,0,String.format("%d Tomans Has been Transfered To THIS ACCOUNT.",amount),Accountbalance);

                                receipt.Print_Receipt();

                            }

                        }

                        else if(amount>Accountbalance){
                            System.out.println("Unfortunately Your AccountBalance is less than Your Entered Amount");
                        }


                        else if(Query.ShowInformation(Destination).isFirst()==false){
                            System.out.println("Destination Account nubmer not found in System try again.");
                        }


                    }

                    else{
                        System.out.println("Access Denied.");
                    }

                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }



        }

        public static void Withdraw(int accountnumber,int ramzeAvval,int amount){
        //TODO:
            try {

                boolean auth = Query.Authentication(accountnumber, ramzeAvval);
                Withdraw w;

                int Accountbalance = Query.ShowInformation(accountnumber).getInt("Amount");

                if(Accountbalance>=amount) {

                    Query.UpdateRecords(accountnumber, 12, Integer.toString(Accountbalance - amount));

                    int AccountBalanceBalance=Accountbalance-amount;

                    w = new Withdraw(accountnumber,amount,AccountBalanceBalance);

                    w.Print_Receipt();
                }

                else {
                    System.out.println("Your Entered Amount for Withdraw is More Than your Accountbalance. ");
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        public static void Deposit(int Accountnumber,int Ramzeavval,int Amount) throws SQLException {
           boolean auth =  Query.Authentication(Accountnumber,Ramzeavval);
           if(auth==true){
               int Accountbalance = Query.ShowInformation(Accountnumber).getInt("Amount");
               boolean status = Query.UpdateRecords(Accountnumber,12,Integer.toString(Accountbalance)+Amount);
               if(status==true){
                   Deposit receipt = new Deposit(Accountnumber,Amount,Accountbalance+Amount);
                   receipt.Print_Receipt();
               }

               else {
                   System.out.println("Deposit the Cash Failed,Try Again Later.");
               }

           }
           else {
               System.out.println("Access Denied.");
           }

        }


    public static void main(String[] args) throws SQLException {

    //BankAccount.Transfer(1000,5678,2000,184000);
        //BankAccount.Deposit(1000,2356,500);
        BankAccount.Withdraw(1000,2356,4000000);
       // System.out.println(Query.OnlineAuthentication(1000,5678));
       // System.out.println(Query.ShowInformation(1000).isFirst());



    }
}