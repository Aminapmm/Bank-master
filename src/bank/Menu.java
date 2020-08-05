package bank;

import javax.management.ServiceNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    /***
    private static Menu MenuInstance;
    private Menu(){};
public static Menu get_instance(){
    if(MenuInstance==null){
        return MenuInstance=new Menu();
    }

    return MenuInstance;
}
     ***/

    //TODO:AUTHENTICATING SHOULD BE THE FIRST OF ALL AFTER CHECKING USER PASSWORD WITH DB USER CAN ENTER AND DO HIS WORKS.
    static Scanner input = new Scanner(System.in);
    public static void MenuList()throws ServiceNotFoundException, SQLException {
        System.out.printf("1)Create an Acoount\n" +
                "2)Edit AccountInformation\n"+
        "3)Show Last10 Transactions\n"
        +"4)Change Passwords\n"+"5)Transfer\n"+
                "6)Withdraw\n"+"7)Deposit\n8)CheckAccountBalance\n");
        int Option = input.nextInt();
        System.out.println("Enter Your Accountnumber:");
        int accountnumber=input.nextInt();
        switch (Option){

            case 1:

                Operator op = Operator.get_instance();
                op.buildaccount();
                break;

            case 2:

                System.out.println("Which Field You Want to change:");
                String field = input.next();
                System.out.println(String.format("Enter Your new %s :",field));
                String data = input.next();
                Query.UpdateRecords(accountnumber,field,data);
                break;

            case 3:

                Query.ShowTransactionrecords(accountnumber,10);
                break;

            case 4:

                Operations.Changepassword(accountnumber);
                break;

            case 5:

                System.out.println("Enter Your Destination Accountnumber:");
                int Dest = input.nextInt();
                System.out.println("Enter The Amount for Transfer:");
                int Amount = input.nextInt();
                Operations.Transfer(accountnumber,Dest,Amount);
                break;

            case 6:

                System.out.println("Enter Amount for Withdraw:");
                int Withdraw_amount = input.nextInt();
                Operations.Withdraw(accountnumber,Withdraw_amount);
                break;

            case 7:

                System.out.println("Enter Amount for Deposit:");
                int deposit_amount = input.nextInt();
                Operations.Deposit(accountnumber,deposit_amount);
                break;

            case 8:

                Operations.Checkaccountbalance(accountnumber);
                break;
        }
    }

    public static void main(String[] args) throws ServiceNotFoundException, SQLException {
        MenuList();
    }

}


