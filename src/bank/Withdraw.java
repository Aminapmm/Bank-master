package bank;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Withdraw extends TRANSACTION {

	Withdraw(int ACCOUNTNUMBER, int AMOUNT,int AccountBalance) {
		this.setAccountnumber(ACCOUNTNUMBER);
		this.setAmount(AMOUNT);
		this.setAccountBalance(AccountBalance);

	}

	@Override
	void Backup() throws IOException {
		//TODO
		String File_path="C:\\Users\\datacomputer\\Desktop\\Backup_receipts\\Withdraws.txt";
		FileWriter writer = new FileWriter(File_path);
		PrintWriter pw = new PrintWriter(writer);
		pw.printf("Account number: %s\nAmount: %s\nDatetime: %s - %s\nDescription: %s\n=====================================",this.getAccountnumber(),this.getAMOUNT(),this.getReceiptDate(),this.getReceiptTime(),this.getDESCRIPTION());
		pw.close();
	}

	@Override
	public String toString(){
		return String.format("======================\nWithdraw Receipt\nAccountnumber: %d\nAccountBalance: %d\nDate: %s\nTime: %s\nDescription: %s\n=====================",this.getAccountnumber(),this.getAccountBalance(),this.getReceiptDate(),this.getReceiptTime(),this.getAMOUNT());
	}

}
