package bank;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import bank.time.*;

abstract class TRANSACTION {

	private int Accountnumber;
	private int Amount;
	private String Description="";
	private	String ReceiptTime;
	private PersianDate ReceiptDate;
	private int AccountBalance;

	public void setAccountnumber(int Accountnumber) {
		this.Accountnumber = this.Accountnumber;
	}

	public void setAmount(int Amount) {
		this.Amount = Amount;
	}

	public void setDESCRIPTION(String Description) {
		this.Description = Description;
	}

	public void setReceiptTime() {
		DateTimeFormatter std=DateTimeFormatter.ofPattern("HH:mm:ss");
		this.ReceiptTime=LocalTime.now().format(std);
	}

	public void setReceiptDate() {
		ReceiptDate = PersianDate.now();
	}

	public void setAccountBalance(int accountBalance) {
		AccountBalance = accountBalance;
	}

	public int getAccountnumber(){
		return this.Accountnumber;
	}

	public int getAMOUNT() {
		return this.Amount;
	}

	public PersianDate getReceiptDate() {
		return this.ReceiptDate;
	}

	public String getReceiptTime() {
		return this.ReceiptTime;
	}

	public String getDESCRIPTION(){
		return this.Description;
	}

	public int getAccountBalance() {
		return this.AccountBalance;
	}


	//TODO:THESE METHOD SHOULD BE CHANGE WITH Overrided toString method.
	abstract void Backup() throws IOException;



}
