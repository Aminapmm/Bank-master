package bank;

import bank.time.PersianDate;

import javax.management.ServiceNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Operations {

    static Scanner input = new Scanner(System.in);

    /***
     * Tranfer Method to transfer money with these parameters.
     * it will take ramze dovom from user and check it with the database then the user'll be allowed to transfer.
     *
     * @param accountnumber
     * @param Destination
     * @param amount
     * @throws SQLException
     * @throws ServiceNotFoundException
     */


    public static void Transfer(int accountnumber, int Destination, int amount)throws SQLException, ServiceNotFoundException {

        //TODO:NEED SOME CLEANING AND TO BE MORE USER FRIENDLY.

            boolean auth=false;
            int Accountbalance;

            Accountbalance = Query.ShowInformation(accountnumber).getInt("accountbalance");


            if (Query.ShowInformation(Destination).isFirst() == true&&amount<Accountbalance) {

                System.out.printf("Are You Sure that you want to transfer  %s  to \n %s  %s's Account?",amount,Query.ShowInformation(Destination).getString("firstname"),Query.ShowInformation(Destination).getString("lastname"));
                String Decision= input.next().toUpperCase();

                    if(Decision.equals("YES")){

                            Accountbalance=Accountbalance-amount;

                            Query.UpdateRecords(accountnumber,"ACCOUNTBALANCE",Integer.toString(Accountbalance));
                            Query.InsertTransactionsRecord(accountnumber,"TRANSFER",amount,0,Destination,String.format("%d Tomans Has been Transfered from This Account.",amount),Accountbalance);
                            Transfer receipt = new Transfer(accountnumber,amount,Destination,Accountbalance);

                            Accountbalance=Query.ShowInformation(Destination).getInt("accountbalance")+amount;

                            Query.UpdateRecords(Destination,"ACCOUNTBALANCE",Integer.toString(Accountbalance+amount));
                            Query.InsertTransactionsRecord(Destination,"TRANSFER",amount,accountnumber,0,String.format("%d Tomans Has been Transfered To This Account.",amount),Accountbalance);
                            System.out.println(receipt);

                    }

            }

                else if(amount>Accountbalance){
                    System.out.println("Unfortunately Your AccountBalance is less than Your Entered Amount");
                }


                else if(!Query.ShowInformation(Destination).isFirst()){
                    System.out.println("Destination Account nubmer not found in System try again.");
                }

            }



    public static void Withdraw(int accountnumber,int amount)throws SQLException,ServiceNotFoundException {
        //TODO:Check this and test it.



                    int Accountbalance = Query.ShowInformation(accountnumber).getInt("Accountbalance");

            if(Accountbalance>=amount) {

                    int AccountBalance=Accountbalance-amount;

                    Query.UpdateRecords(accountnumber, "ACCOUNTBALANCE", Integer.toString(Accountbalance ));


                    String description=String.format("%d Rials Withdrawn Successfully From this Account.",amount);
                    Query.InsertTransactionsRecord(accountnumber,"Withdraw",amount,0,0,description,AccountBalance);

                    Withdraw w = new Withdraw(accountnumber,amount,AccountBalance);
                    w.setReceiptDate();
                    w.setReceiptTime();
                    w.setDESCRIPTION("Succesfull");

                    System.out.println(w);
            }

            else {
                    System.out.println("Your Entered Amount for Withdraw is More Than your Accountbalance. ");
            }
    }

    public static void Deposit(int Accountnumber,int Amount) throws SQLException,ServiceNotFoundException {

                        int Accountbalance = Query.ShowInformation(Accountnumber).getInt("Accountbalance");
                        Accountbalance += Amount;
                        System.out.println(Accountbalance);
                        boolean status = Query.UpdateRecords(Accountnumber,"ACCOUNTBALANCE",Integer.toString(Accountbalance));
                        if(status==true){
                            String description = String.format("%d Rials HAS BEEN DEPOSITED TO THIS ACCOUNT SUCCESFULLY.",Amount);
                            Query.InsertTransactionsRecord(Accountnumber,"Deposit",Amount,0,0,description,Accountbalance);
                            Deposit receipt = new Deposit(Accountnumber,Amount,Accountbalance);
                            receipt.setDESCRIPTION("DONE.");

                            System.out.println(receipt);
                        }

                else {
                    System.out.println("Deposit the Cash Failed,Try Again Later.");
                }
            }



    public static boolean Changepassword(int Accountnumber) throws SQLException, ServiceNotFoundException {

                System.out.printf("Which pass Do you Want to change?\n1)Ramzeavval\n2)Ramzedovom\n");
                int whichpass = input.nextInt();
                String Whichpass = (whichpass==1)?"RAMZEAVVAL":"RAMZEDOVOM";


                System.out.println("Enter Your New Password");
                int newpass=input.nextInt();

                switch (whichpass) {

                    case 1:
                        Query.UpdateRecords(Accountnumber, "RAMZEAVVAL", Integer.toString(newpass));
                        break;

                    case 2:
                        Query.UpdateRecords(Accountnumber, "RAMZEDOVOM", Integer.toString(newpass));
                        break;
                }
                System.out.println("Your Password Changed Succesfully.");
                return true ;
            }


    public static void Changestate(int accountnumber )throws SQLException,ServiceNotFoundException{


            System.out.printf("What do you want to do with this Account?\n1)Open\n2)Close\n3)Suspend");

            int state = input.nextInt();

            switch (state){

                    case 1:
                        Query.UpdateRecords(accountnumber,"STATUS","Open");
                        break;

                    case 2:
                        Query.UpdateRecords(accountnumber,"STATUS","CLOSE");
                        break;

                    case 3:
                        Query.UpdateRecords(accountnumber,"STATUS","Suspended");
                        break;
            }
        }


    public static void Interestpayout()throws ServiceNotFoundException,SQLException{
        Connection conn = DriverManager.getConnection(Query.DB_URL,Query.DB_USERNAME,Query.DB_PASSWORD);
        Statement stmt=conn.createStatement();
        String query="SELECT * FROM SAVINGACCOUNTS";
        ResultSet rs = stmt.executeQuery(query);
        int day_of_month = PersianDate.now().getDayOfMonth();
        while(rs.next()){

             LocalDate Registerdate = rs.getDate("REGISTERDATE").toLocalDate();
             int Registerday = Registerdate.getDayOfMonth();
             int accountnumber = rs.getInt("ACCOUNTNUMBER");
             int payout_amount = rs.getInt("MONTHLYPAYOUT");
             int accountbalance = rs.getInt("ACCOUNTBALANCE");
             String description = String.format("%d Rials DEPOSITED TO YOUR ACCOUNT FOR  MONTHLY INTEREST PAYOUT",payout_amount);

            if(day_of_month==Registerday){
                 Query.UpdateRecords(accountnumber,"accountbalance",Integer.toString(payout_amount+accountbalance));
                 Query.InsertTransactionsRecord(accountnumber,"DEPOSIT",payout_amount,0,0,description,payout_amount+accountbalance);
             }

        }

    }

    public static void main(String[] args) throws ServiceNotFoundException, SQLException {
        Interestpayout();
    }

    public static void Checkaccountbalance(int Accountnumber) throws SQLException {

        int accountbalance = Query.ShowInformation(Accountnumber).getInt("Accountbalance");
        DateTimeFormatter std=DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.printf("=================\nAccountnumber: %d\nAccount Balance: %d\nDate: %s\nTime: %s\n======================",Accountnumber,accountbalance,PersianDate.now(), LocalTime.now().format(std));
    }

}
