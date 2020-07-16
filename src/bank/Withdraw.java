package bank;

public class Withdraw extends TRANSACTION {
private int withdrawn_amount;
	Withdraw(long ACCOUNTNUMBER, int AMOUNT,int withdrawn_amount) {
		super(ACCOUNTNUMBER, AMOUNT);
		this.withdrawn_amount=withdrawn_amount;

	}

	public int getWithdrawn_amount() {
		return this.withdrawn_amount;
	}

	@Override
	void Backup() {
		//TODO
	}

	@Override
	public void Print_Receipt(){
		System.out.printf("		Withdraw Receipt\nAccountnumber: %d\nRemainingAmount: %d\nDate: %s\nTime: %s\nDescription:%d Withdrawn Succesfully.",this.getACCOUNTNUMBER(),this.getAMOUNT(),this.getReceiptDate(),this.getReceiptTime(),this.getWithdrawn_amount());
	}

	public static void main(String[] args) {
		Withdraw w = new Withdraw(1000,52000,2000);
		w.Print_Receipt();
	}
}
