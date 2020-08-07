package bank;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transfer extends TRANSACTION {
	private int Destination;
	public Transfer(int accountnumber,int amount,int Destination,int Accountbalance) {
		this.setAccountnumber(accountnumber);
		this.setAmount(amount);
		this.setAccountBalance(Accountbalance);
		this.Destination=Destination;
		this.setDESCRIPTION("Transferred Succesfully.");
		this.setReceiptTime();
		this.setReceiptDate();
	}

	private int getDestination(){
		return this.Destination;
	}

	@Override
	void Backup() throws IOException {
		//TODO
		String File_path="C:\\Users\\datacomputer\\Desktop\\Backup_receipts\\Transfers.txt";
		FileWriter writer = new FileWriter(File_path);
		PrintWriter pw = new PrintWriter(writer);
		pw.printf("Account number: %s\nDestination: %d\nAmount: %s\nDatetime: %s - %s\n%s\nDescription: %s\n=====================================",this.getAccountnumber(),this.getAMOUNT(),this.getReceiptDate(),this.getReceiptTime(),this.getDESCRIPTION());
		pw.close();
	}


	@Override
	public String toString(){
		return String.format("Transfer Receipt\nAccountnumber: %d\nAmount: %d\nTo account: %d\nAccount Balance: %d\nDate: %s\nTime: %s\nDescription: %s\n",this.getAccountnumber(),this.getAMOUNT(),this.getDestination(),this.getAccountBalance(),this.getReceiptDate(),this.getReceiptTime(),this.getDESCRIPTION());
	}


}
