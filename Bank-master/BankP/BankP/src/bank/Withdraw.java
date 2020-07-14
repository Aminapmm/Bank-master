package bank;

public class Withdraw extends TRANSACTION {

	Withdraw(long ACCOUNTNUMBER, double AMOUNT, String DESCRIPTION) {
		super(ACCOUNTNUMBER, AMOUNT, DESCRIPTION);

	}

	@Override
	void Backup() {
		//TODO
	}
}
