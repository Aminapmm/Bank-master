package bank;

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
    public static void MenuList(){
        System.out.printf("1)Create an Acoount\n" +
                "2)Edit AccountInformation\n"+
        "3)Show Last10 Transactions\n"
        +"4)Change Passwords\n"+"5)Transfer\n"+
                "6)Withdraw\n"+"7)Deposit\n");
    }

    public static void main(String[] args) {
        MenuList();
        //BankAccount.Transfer();
    }

}


