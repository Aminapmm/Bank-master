package bank;

public class Transfer extends TRANSACTION {
	private int Destination=0;
	public Transfer(int ACCOUNTNUMBER,int AMOUNT,int Destination,int Accountbalance,String description) {

		super(ACCOUNTNUMBER, AMOUNT,Accountbalance,description);
		this.Destination=Destination;
	}

	private int getDestination(){
		return this.Destination;
	}

	@Override
	void Backup() {
		//TODO
	}

	@Override
	public void Print_Receipt(){
			System.out.printf("		Transfer Receipt\nAccountnumber: %d\nAmount: %d\nDestination: %d\nAccount Balance: %d\nDate: %s\nTime: %s\n",this.getACCOUNTNUMBER(),this.getAMOUNT(),this.getDestination(),this.getAccountBalance(),this.getReceiptDate(),this.getReceiptTime());
	}

	public static void main(String[] args) {
		Transfer t = new Transfer(1000,154000,5003,200000,"done");
		t.Print_Receipt();
	}
}
