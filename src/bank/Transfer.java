package bank;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transfer extends TRANSACTION {
	private int Source;
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

	public Transfer(int accountnumber,int amount,int Source,int Destination,int Accountbalance){
		this(accountnumber, amount, Destination, Accountbalance);
		this.Source=Source;
	}

	private int getDestination(){
		return this.Destination;
	}

	private int getSource(){return this.Source;}

	@Override
	void Backup() throws IOException {
		//TODO
		String File_path="C:\\Users\\datacomputer\\Desktop\\Backup_receipts\\Transfers.txt";
		FileWriter writer = new FileWriter(File_path);
		PrintWriter pw = new PrintWriter(writer);

		if(this.Source==0) {
			pw.printf("Transfer Receipt\nAccountnumber: %d\nAmount: %d\nTo account: %d\nAccount Balance: %d\nDate: %s\nTime: %s\nDescription: %s\n", this.getAccountnumber(), this.getAMOUNT(), this.getDestination(), this.getAccountBalance(), this.getReceiptDate(), this.getReceiptTime(), this.getDESCRIPTION());
		}
		else if(this.Destination==0){
			pw.printf("Transfer Receipt\nAccountnumber: %d\nAmount: %d\nFrom Account: %d\nAccount Balance: %d\nDate: %s\nTime: %s\nDescription: %s\n", this.getAccountnumber(), this.getAMOUNT(), this.getSource(), this.getAccountBalance(), this.getReceiptDate(), this.getReceiptTime(), this.getDESCRIPTION());

		}
		pw.close();
	}


	@Override
	public String toString(){

		if(this.Source==0) {
			return String.format("Transfer Receipt\nAccountnumber: %d\nAmount: %d\nTo account: %d\nAccount Balance: %d\nDate: %s\nTime: %s\nDescription: %s\n", this.getAccountnumber(), this.getAMOUNT(), this.getDestination(), this.getAccountBalance(), this.getReceiptDate(), this.getReceiptTime(), this.getDESCRIPTION());
			}
		else if(this.Destination==0){
			return String.format("Transfer Receipt\nAccountnumber: %d\nAmount: %d\nFrom Account: %d\nAccount Balance: %d\nDate: %s\nTime: %s\nDescription: %s\n", this.getAccountnumber(), this.getAMOUNT(), this.getSource(), this.getAccountBalance(), this.getReceiptDate(), this.getReceiptTime(), this.getDESCRIPTION());

			}
		return "";
	}


}
