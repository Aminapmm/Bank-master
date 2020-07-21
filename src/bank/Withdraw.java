package bank;

public class Withdraw extends TRANSACTION {

	Withdraw(int ACCOUNTNUMBER, int AMOUNT,int Balance) {
		super(ACCOUNTNUMBER, AMOUNT,Balance);

	}

	@Override
	void Backup() {
		//TODO
	}

	@Override
	public void Print_Receipt(){
		System.out.printf("		Withdraw Receipt\nAccountnumber: %d\nAccountBalance: %d\nDate: %s\nTime: %s\nDescription:%d TOMANS Withdrawn Succesfully.",this.getACCOUNTNUMBER(),this.getAccountBalance(),this.getReceiptDate(),this.getReceiptTime(),this.getAMOUNT());
	}

	public static void main(String[] args) {
		Withdraw w = new Withdraw(1000,22000,50000);
		w.Print_Receipt();
	}
}
