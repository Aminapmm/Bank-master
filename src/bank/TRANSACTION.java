package bank;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
	private String type;

	public void setAccountnumber(int Accountnumber) {
		this.Accountnumber = Accountnumber;
	}

	public void setAmount(int Amount) {
		this.Amount = Amount;
	}

	public void setType(String type){this.type=type;}

	public void setDESCRIPTION(String Description) {
		this.Description = Description;
	}

	public void setReceiptTime() {
		DateTimeFormatter std=DateTimeFormatter.ofPattern("HH:mm:ss");
		this.ReceiptTime=LocalTime.now().format(std);
	}

	public void setReceiptDate(LocalDate date){
		this.ReceiptDate = PersianDate.of(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
	}

	public void setReceiptTime(Time time){
		this.ReceiptTime = time.toString();
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

	public String getType(){return this.type;}

	public int getAccountBalance() {
		return this.AccountBalance;
	}

	abstract void Backup() throws IOException;

}
