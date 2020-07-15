package bank;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import bank.time.*;

abstract class TRANSACTION {

	private long ACCOUNTNUMBER;
	private double AMOUNT;
	private String DESCRIPTION="";
	private	String ReceiptTime;
	private PersianDate ReceiptDate;

	TRANSACTION(long ACCOUNTNUMBER,double AMOUNT,String DESCRIPTION)
	{
		this(ACCOUNTNUMBER,AMOUNT);
		this.DESCRIPTION=DESCRIPTION;

	}

	TRANSACTION(long ACCOUNTNUMBER,double AMOUNT){
		this.ReceiptDate=PersianDate.now();
		this.ACCOUNTNUMBER=ACCOUNTNUMBER;
		this.AMOUNT=AMOUNT;
		this.ReceiptDate=PersianDate.now();
		DateTimeFormatter std=DateTimeFormatter.ofPattern("HH:mm:ss");
		this.ReceiptTime=LocalTime.now().format(std);
	}

	public long getACCOUNTNUMBER(){
		return this.ACCOUNTNUMBER;
	}

	public double getAMOUNT() {
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



	abstract  void Backup();
	abstract void Print_Receipt();



}
