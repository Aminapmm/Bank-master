package bank;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import bank.time.*;

abstract class TRANSACTION {

	private int ACCOUNTNUMBER;
	private int AMOUNT;
	private String DESCRIPTION="";
	private	String ReceiptTime;
	private PersianDate ReceiptDate;
	private int AccountBalance;

	TRANSACTION(int ACCOUNTNUMBER,int AMOUNT,int Balance,String DESCRIPTION)
	{
		this(ACCOUNTNUMBER,AMOUNT,Balance);
		this.DESCRIPTION=DESCRIPTION;

	}

	TRANSACTION(int ACCOUNTNUMBER,int AMOUNT,int Balance){
		this.ReceiptDate=PersianDate.now();
		this.ACCOUNTNUMBER=ACCOUNTNUMBER;
		this.AMOUNT=AMOUNT;
		this.ReceiptDate=PersianDate.now();
		DateTimeFormatter std=DateTimeFormatter.ofPattern("HH:mm:ss");
		this.ReceiptTime=LocalTime.now().format(std);
		this.AccountBalance = Balance;
	}

	public int getACCOUNTNUMBER(){
		return this.ACCOUNTNUMBER;
	}

	public int getAMOUNT() {
		return this.AMOUNT;
	}

	public PersianDate getReceiptDate() {
		return this.ReceiptDate;
	}

	public String getReceiptTime() {
		return this.ReceiptTime;
	}

	public String getDESCRIPTION(){
		return this.DESCRIPTION;
	}

	public int getAccountBalance() {
		return this.AccountBalance;
	}


	//TODO:THESE METHOD SHOULD BE CHANGE WITH Overrided toString method.
	abstract void Print_Receipt();



}
