package bank;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Deposit extends TRANSACTION {


	Deposit(int ACCOUNTNUMBER, int AMOUNT,int AccountBalance) {

		this.setAccountnumber(ACCOUNTNUMBER);
		this.setAmount(AMOUNT);
		this.setAccountBalance(AccountBalance);

	}

	@Override
	void Backup() throws IOException {
		//TODO

			String File_path="C:\\Users\\datacomputer\\Desktop\\Backup_receipts\\Deposits.txt";
			FileWriter writer = new FileWriter(File_path);
			PrintWriter pw = new PrintWriter(writer);
			pw.printf("Account number: %s\nAmount: %s\nDatetime: %s - %s\nDescription: %s\n====================",this.getAccountnumber(),this.getAMOUNT(),this.getReceiptDate(),this.getReceiptTime(),this.getDESCRIPTION());
			pw.close();

		}


	@Override
	public String toString(){
		return String.format("Deposit Receipt\nAccountnumber: %d\nDepositor: %s\nDate: %s\nTime: %s\nAmount: %d\nAccountBalance: %d\nDescription: %s\n",this.getAccountnumber(),0,this.getReceiptDate(),this.getReceiptTime(),this.getAMOUNT(),this.getAccountBalance(),this.getDESCRIPTION());
	}

}
