package bank;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;


public class Deposit extends TRANSACTION {
	long acnumber;

	Deposit(long ACCOUNTNUMBER, double AMOUNT, String DESCRIPTION) {
		super(ACCOUNTNUMBER, AMOUNT, DESCRIPTION);


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

	public static void main(String[] args) {
		Deposit d = new Deposit(1000,15000,"done");
		d.Backup();
	}
}
