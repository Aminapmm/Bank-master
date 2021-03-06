package bank;

import javax.management.ServiceNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

interface Menu {
     void MenuList()throws ServiceNotFoundException,SQLException;

}

    class Operatorsmenu implements Menu {

        static Scanner input = new Scanner(System.in);

        @Override
        public void MenuList() throws ServiceNotFoundException, SQLException {

            System.out.println("Enter Your Username:");
            String username = input.next();

            System.out.println("Enter Your Password:");
            int password = input.nextInt();

            boolean access = Query.OperatorAuthentication(username, password);
            if (access == true) {

                int accountnumber;

                int Option;

                do {

                    Operations.Interestpayout();

                    System.out.printf("0)Exit\n" +
                            "1)Create a CheckingAcoount\n"+"11)Create a Savingaccount\n" +
                            "2)Edit AccountInformation\n" +
                            "3)Show Last10 Transactions\n"
                            + "4)Change Passwords\n" + "5)Transfer\n" +
                            "6)Withdraw\n" + "7)Deposit\n8)CheckAccountBalance\n9)Change State");


                    Option = input.nextInt();

                    switch (Option) {

                        case 1:
                            Operator op = Operator.get_instance();
                            Person customer = op.FillPersonalinfo();
                            Checkingaccountbuilder checkingaccountbuilder = new Checkingaccountbuilder();
                            CheckingAccount checkingAccount = op.buildCheckingAccount(checkingaccountbuilder,customer);
                            Query.InsertCustomersRecords(checkingAccount);
                            System.out.println(checkingAccount);
                            break;

                        case 11:
                             op = Operator.get_instance();
                             customer = op.FillPersonalinfo();
                            Savingaccountbuilder savingaccountbuilder = new Savingaccountbuilder();
                            Savingaccount savingaccount = op.buildSavingaccount(savingaccountbuilder,customer);
                            Query.InsertCustomersRecords(savingaccount);
                            System.out.println(savingaccount);

                            break;


                        case 2:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();
                            Exceptions.accountexist(accountnumber);
                            Exceptions.Checkstate(accountnumber);

                            System.out.printf("Which Field You Want to change:\n1)Firstname\n2)Lastname\n3)Phonenumber\n");
                            String field = input.next();
                            System.out.println(String.format("Enter Your new %s :", field));
                            String data = input.next();
                            Query.UpdateRecords(accountnumber, field, data);
                            break;

                        case 3:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();

                            Exceptions.accountexist(accountnumber);
                            Exceptions.Checkstate(accountnumber);
                            Query.ShowTransactionrecords(accountnumber, 10);
                            break;

                        case 4:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();

                            Operations.Changepassword(accountnumber);
                            break;

                        case 5:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();
                            Exceptions.accountexist(accountnumber);
                            Exceptions.Checkstate(accountnumber);
                            System.out.println("Enter Your Destination Accountnumber:");
                            int Dest = input.nextInt();
                            Exceptions.accountexist(Dest);
                            System.out.println("Enter The Amount for Transfer:");
                            int Amount = input.nextInt();
                            Operations.Transfer(accountnumber, Dest, Amount);
                            break;

                        case 6:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();
                            Exceptions.accountexist(accountnumber);
                            Exceptions.Checkstate(accountnumber);

                            System.out.println("Enter Amount for Withdraw:");
                            int Withdraw_amount = input.nextInt();
                            Operations.Withdraw(accountnumber, Withdraw_amount);
                            break;

                        case 7:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();
                            
                            System.out.println("Enter Amount for Deposit:");
                            int deposit_amount = input.nextInt();
                            Operations.Deposit(accountnumber, deposit_amount);
                            break;

                        case 8:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();

                            Operations.Checkaccountbalance(accountnumber);
                            break;

                        case 9:
                            System.out.println("Enter Your Accountnumber:");
                            accountnumber = input.nextInt();

                            Operations.Changestate(accountnumber);
                            break;
                    }

                }
                while (Option != 0);

            }
        }

    }

 class CustomersMenu implements Menu{

     @Override
     public void MenuList() throws ServiceNotFoundException, SQLException {


         Scanner input = new Scanner(System.in);

         int option;
         System.out.println("Enter Your Accountnumber:");
         int accountnumber = input.nextInt();
         Exceptions.accountexist(accountnumber);
         System.out.println("Enter Your Password:");
         int password = input.nextInt();
         Exceptions.Checkstate(accountnumber);

         do {
             System.out.printf("0)Exit\n"+"1)Show Last10 Transactions\n"
                     + "2)Change Passwords\n" + "3)Transfer\n" +
                     "4)Withdraw\n" + "5)Deposit\n6)CheckAccountBalance\n7)Change password\n");
             option = input.nextInt();

             switch (option) {
                 case 1:
                     Query.ShowTransactionrecords(accountnumber, 10);
                     break;
                 case 2:
                     Operations.Changepassword(accountnumber);
                     break;
                 case 3:
                     System.out.println("Enter the account number of the account you wish to transfer funds to:");
                     int dest = input.nextInt();
                     System.out.println("Enter the Amount you want to pay:");
                     int amount = input.nextInt();
                     Operations.Transfer(accountnumber, dest, amount);
                     break;
                 case 4:
                     System.out.println("Enter the Amount you want to Withdraw:");
                     int withdrawamount = input.nextInt();
                     Operations.Withdraw(accountnumber, withdrawamount);
                     break;
                 case 5:
                     System.out.println("Enter the Amount you want to Deposit:");
                     int depositamount = input.nextInt();
                     Operations.Deposit(accountnumber, depositamount);
                     break;
                 case 6:
                     Operations.Checkaccountbalance(accountnumber);
                     break;
                 case 7:
                     Operations.Changepassword(accountnumber);
                     break;
             }
         }
         while (option!=0);
     }

 }

 class OnlineBanking implements Menu{
     Scanner input = new Scanner(System.in);
     @Override
     public void MenuList() throws ServiceNotFoundException, SQLException {

         int option = 0;

         System.out.println("Enter Your Accountnumber:");
         int accountnumber = input.nextInt();
         System.out.println("Enter Your Password(Ramzedovom):");
         int password = input.nextInt();
         boolean auth = Query.OnlineAuthentication(accountnumber, password);


         if(auth==true){

         do {

             System.out.printf("0)Exit\n1)Transfer\n2)Check Accountbalance\n3)Check Last 10 Transactions\n");
             option = input.nextInt();


             switch (option) {

                 case 0:

                     break;

                 case 1:

                     System.out.println("Enter the account number of the account you wish to transfer funds to:");
                     int dest = input.nextInt();
                     System.out.println("Enter the Amount you want to pay:");
                     int amount = input.nextInt();
                     Operations.Transfer(accountnumber, dest, amount);
                     break;

                 case 2:
                     Operations.Checkaccountbalance(accountnumber);
                     break;
                 case 3:
                     Query.ShowTransactionrecords(accountnumber, 5);
                     break;
             }

         }

         while (option != 0);
        }
     }


     public static void main(String[] args) throws ServiceNotFoundException, SQLException {

         Scanner input = new Scanner(System.in);

         System.out.printf("Which Menu Do you Want To Enter?\n1)Operators Menu\n2)Customers Menu\n3)OnlineBanking Menu");
         int Option = input.nextInt();
         switch (Option) {
             case 1:
                      Operatorsmenu menu1 = new Operatorsmenu();
                      menu1.MenuList();
                      break;

             case 2:
                     CustomersMenu menu2 = new CustomersMenu();
                     menu2.MenuList();
                     break;

             case 3:

                     OnlineBanking menu3 = new OnlineBanking();
                     menu3.MenuList();
                         break;
         }
     }

 }



