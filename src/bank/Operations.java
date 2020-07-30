package bank;

import javax.management.ServiceNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

//TODO:Method To Change The Password whenever the account owner wanted.


public class Operations {

    public static void Transfer(int accountnumber, int ramzedovom, int Destination, int amount)throws SQLException, ServiceNotFoundException {
        //TODO

        boolean auth=false;
        int Accountbalance;
        auth= Query.OnlineAuthentication(accountnumber,ramzedovom);
        Scanner input = new Scanner(System.in);

        if (auth == true) {

            Accountbalance = Query.ShowInformation(accountnumber).getInt("accountbalance");


            if (Query.ShowInformation(Destination).isFirst() == true&&amount<Accountbalance) {

                System.out.printf("Are You Sure that you want to transfer  %s  to \n %s  %s's Account?",amount,Query.ShowInformation(Destination).getString("firstname"),Query.ShowInformation(Destination).getString("lastname"));
                String Decision= input.next().toUpperCase();

                    if(Decision.equals("YES")){

                            Accountbalance=Accountbalance-amount;

                            Query.UpdateRecords(accountnumber,"ACCOUNTBALANCE",Integer.toString(Accountbalance));
                            Query.InsertTransactionsRecord(accountnumber,"TRANSFER",amount,0,Destination,String.format("%d Tomans Has been Transfered from THIS ACCOUNT.",amount),Accountbalance);
                            Transfer receipt = new Transfer(accountnumber,Destination,amount,Accountbalance,"Done");

                            Accountbalance=Query.ShowInformation(Destination).getInt("accountbalance")+amount;

                            Query.UpdateRecords(Destination,"ACCOUNTBALANCE",Integer.toString(Accountbalance+amount));
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

    public static void Withdraw(int accountnumber,int ramzeAvval,int amount)throws SQLException,ServiceNotFoundException {
        //TODO:


            boolean auth = Query.Authentication(accountnumber, ramzeAvval);

            Withdraw w;

            int Accountbalance = Query.ShowInformation(accountnumber).getInt("Accountbalance");

            if(Accountbalance>=amount) {

                Query.UpdateRecords(accountnumber, "ACCOUNTBALANCE", Integer.toString(Accountbalance - amount));

                int AccountBalance=Accountbalance-amount;

                String description=String.format("%d TOMANS Withdrawn Successfully From this Account.",amount);
                Query.InsertTransactionsRecord(accountnumber,"Withdraw",amount,0,0,description,AccountBalance);

                w = new Withdraw(accountnumber,amount,AccountBalance);

                w.Print_Receipt();
            }

            else {
                System.out.println("Your Entered Amount for Withdraw is More Than your Accountbalance. ");
            }
    }

    public static void Deposit(int Accountnumber,int Ramzeavval,int Amount) throws SQLException,ServiceNotFoundException {

            boolean auth =  Query.Authentication(Accountnumber,Ramzeavval);

            if(auth==true){
                int Accountbalance = Query.ShowInformation(Accountnumber).getInt("Accountbalance");
                boolean status = Query.UpdateRecords(Accountnumber,"ACCOUNTBALANCE",Integer.toString(Accountbalance+Amount));
                if(status==true){
                    String description = String.format("%d TOMANS HAS BEEN DEPOSITED TO THIS ACCOUNT SUCCESFULLY.",Amount);
                    Query.InsertTransactionsRecord(Accountnumber,"Deposit",Amount,0,0,description,Accountbalance);
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
}
