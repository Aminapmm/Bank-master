package bank;

public class Transfer extends TRANSACTION {
	private long Source=0;
	private long Destination=0;
	public Transfer(long ACCOUNTNUMBER, double AMOUNT, String DESCRIPTION,long Source,long Destination) {

		super(ACCOUNTNUMBER, AMOUNT, DESCRIPTION);
		this.Source=Source;
		this.Destination=Destination;
	}

	@Override
	void Backup() {
		//TODO
	}
}
