package bank;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;


public class Deposit extends TRANSACTION {


	Deposit(long ACCOUNTNUMBER, double AMOUNT,String Description) {
		super(ACCOUNTNUMBER, AMOUNT);
	}

	@Override
	void Backup() {
		//TODO
		try{
			String File_path="C:\\Users\\datacomputer\\Desktop\\receipts.txt";
			FileWriter writer = new FileWriter(File_path);
			PrintWriter pw = new PrintWriter(writer);
			pw.printf("Account number: %s\nAmount: %s\nDatetime: %s - %s\nDescription: %s\n====================",this.getACCOUNTNUMBER(),this.getAMOUNT(),this.getReceiptDate(),this.getReceiptTime(),this.getDESCRIPTION());
			pw.close();

		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void Print_Receipt(){
		System.out.printf("			Deposit Receipt\nAccountnumber: %d\nDepositor: %s\nDate: %s\nTime: %s\nAmount: %.0f\n",this.getACCOUNTNUMBER(),0,this.getReceiptDate(),this.getReceiptTime(),this.getAMOUNT());
	}

	public static void main(String[] args) {
		Deposit d = new Deposit(1000,15000,"done");
		d.Backup();
	}
}