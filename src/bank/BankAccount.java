package bank;

import org.apache.commons.lang3.RandomStringUtils;
import bank.time.*;

class Person {

    private String firstname;
    private String lastname;
    private String nationalid;
    private String phonenumber;
    private PersianDate birthdate;


    private Person(Personbuilder personbuilder) {

        this.firstname = personbuilder.firstname;
        this.lastname = personbuilder.lastname;
        this.birthdate = personbuilder.birthdate;
        this.nationalid = personbuilder.nationalid;
        this.phonenumber = personbuilder.phonenumber;

    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNationalid() {
        return nationalid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public PersianDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString(){
        return String.format("Firstname: %s\nLastname: %s\nNationalID: %s\nPhonenumber: %s\nBirthdate: %s\n",firstname,lastname,nationalid,phonenumber,birthdate);
    }

    static class Personbuilder {

        private String firstname;
        private String lastname;
        private String nationalid;
        private String phonenumber;
        private PersianDate birthdate;

        public Personbuilder(String firstname, String lastname, String nationalid, String phonenumber, PersianDate birthdate) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.nationalid = nationalid;
            this.phonenumber = phonenumber;
            this.birthdate = birthdate;
        }

        public Personbuilder setFirstname(String firstname){
            this.firstname=firstname;
            return this;
        }
        public Personbuilder setLastname(String lastname){
            this.lastname=lastname;
            return this;
        }

        public Personbuilder setNatonalid(String nationalid){
            this.nationalid=nationalid;
            return this;
        }

        public Personbuilder setPhoneumber(String phonenumber){
            this.phonenumber=phonenumber;
            return this;
        }

        public Personbuilder setBirthdate(int yy, int mm, int dd){
            this.birthdate=PersianDate.of(yy,mm,dd);
            return this;
        }

        public Person getperson(){
            return new Person(this);
        }


    }
}

    interface Builder{
        void setType();
        void setRamzeavval();
        void setRamzedovom();
        void setAccountnumber();
        void setAccountbalance(int accountbalance);
        void setOwner(Person accountholder);
    }

    enum Status{
        OPEN,
        CLOSE,
        SUSPENDED,
    }

class CheckingAccount{

    private Person Owner;
    private int accountnumber;
    private int ramzeavval;
    private final String type;
    private int Accountbalance;
    private int ramzedovom;
    private final   PersianDate Registerdate = PersianDate.now();
    private Status state=Status.OPEN;


    public CheckingAccount(Person person, int accountnumber, int Accountbalance, String type, int ramzeavval, int ramzedovom){
        this.Owner=person;
        this.accountnumber=accountnumber;
        this.Accountbalance=Accountbalance;
        this.type=type;
        this.ramzeavval=ramzeavval;
        this.ramzedovom=ramzedovom;

    }

    public void setState(Status state){
        this.state=state;
    }

    public Status getStatus(){
        return  this.state;
    }

    @Override
    public String toString(){
        return Owner.toString() +  String.format("Accountnumber: %d\nRamzeavval: %d\nRamzedovom: %d\nType: %s\nAccountbalance: %d\n",accountnumber,ramzeavval,ramzedovom,type,Accountbalance);
    }

    public void setRamzeavval(int ramzeavval) {
        this.ramzeavval = ramzeavval;
    }

    public void setAccountbalance(int accountbalance) {
        Accountbalance = accountbalance;
    }

    public void setRamzedovom(int ramzedovom) {
        this.ramzedovom = ramzedovom;
    }

    public Person getOwner() {
        return Owner;
    }

    public PersianDate getRegisterdate() {
        return Registerdate;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public int getRamzeavval() {
        return ramzeavval;
    }

    public String getAccountType() {
        return type;
    }

    public int getAccountbalance() {
        return this.Accountbalance;
    }

    public int getRamzedovom() {
        return ramzedovom;
    }
}

class Checkingaccountbuilder implements Builder {

    private Person accountholder;
    private int accountnumber;
    private int ramzeavval;
    private String type;
    private int Accountbalance;
    private int ramzedovom;



    @Override
    public void setAccountbalance(int accountbalance) {
        this.Accountbalance = accountbalance;
    }

    @Override
    public void setOwner(Person accountholder) {
        this.accountholder = accountholder;
    }


    @Override
    public void setAccountnumber() {
        this.accountnumber = Integer.parseInt(RandomStringUtils.random(8,49,57,false,true));

    }

    @Override
    public void setRamzeavval() {
        this.ramzeavval = Integer.parseInt(RandomStringUtils.random(4,49,57,false,true));
    }

    @Override
    public void setRamzedovom() {
        this.ramzedovom = Integer.parseInt(RandomStringUtils.random(4,49,57,false,true));
    }

    @Override
    public void setType() {
        this.type = "Checking";
    }

    public CheckingAccount getAccount() {
        return new CheckingAccount(accountholder, accountnumber, Accountbalance,type,ramzeavval,ramzedovom);
    }
}

class Savingaccountbuilder implements Builder {

    private Person accountholder;
    private int accountnumber;
    private int ramzeavval;
    private String type;
    private int Accountbalance;
    private int ramzedovom;
    private int Interestrate;
    private int Monthlypayout;
    private int Holdduration;


    public void setType(String type) {
        this.type = type;
    }


    public void setMonthlypayout() {
        this.Monthlypayout = (Interestrate*Accountbalance*Holdduration/100)/12;
    }


    public void setHoldDuration(int time) {
        this.Holdduration=time;
    }



    @Override
    public void setType() {
        this.type="Saving";
    }


    public void setInterestrate(int interestrate) {
        this.Interestrate=interestrate;
    }

    @Override
    public void setRamzeavval() {
        this.ramzeavval = Integer.parseInt(RandomStringUtils.random(4,49,57,false,true));
    }

    @Override
    public void setRamzedovom() {
        this.ramzedovom = Integer.parseInt(RandomStringUtils.random(4,49,57,false,true));
    }

    @Override
    public void setAccountnumber() {
        this.accountnumber = Integer.parseInt(RandomStringUtils.random(8,49,57,false,true));
    }

    @Override
    public void setAccountbalance(int accountbalance) {
        this.Accountbalance=accountbalance;
    }

    @Override
    public void setOwner(Person accountholder) {
        this.accountholder = accountholder;
    }

    public Savingaccount getaccount(){
        return new Savingaccount(accountholder,accountnumber,Accountbalance,ramzeavval,ramzedovom,Interestrate,Monthlypayout,Holdduration,type);
    }

}

class Savingaccount{

    private Person Owner;
    private int accountnumber;
    private int ramzeavval;
    private String type;
    private int Accountbalance;
    private int ramzedovom;
    private int Interestrate;
    private int Monthlypayout;
    private int Holdduration;
    private final  PersianDate Registerdate = PersianDate.now();
    private Status status=Status.OPEN;

    public Savingaccount(Person accountholder, int accountnumber, int accountbalance, int ramzeavval, int ramzedovom, int interestrate, int monthlypayout, int holdduration, String type){

        this.Owner=accountholder;
        this.accountnumber=accountnumber;
        this.Accountbalance=accountbalance;
        this.type=type;
        this.ramzeavval=ramzeavval;
        this.ramzedovom=ramzedovom;
        this.Interestrate=interestrate;
        this.Monthlypayout=monthlypayout;
        this.Holdduration=holdduration;
    }


    public void setStatus(Status status){
        this.status=status;
    }

    public Status getStatus(){
        return this.status;
    }

    public Person getOwner() {
        return Owner;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public int getRamzeavval() {
        return ramzeavval;
    }

    public PersianDate getRegisterdate(){
        return this.Registerdate;
    }

    public String getAccounttype() {
        return type;
    }

    public int getAccountbalance() {
        return Accountbalance;
    }

    public int getRamzedovom() {
        return ramzedovom;
    }

    public int getInterestrate() {
        return Interestrate;
    }

    public int getMonthlypayout() {
        return Monthlypayout;
    }

    public int getHoldduration() {
        return Holdduration;
    }

    public void setRamzeavval(int ramzeavval) {
        this.ramzeavval = ramzeavval;
    }

    public void setAccountbalance(int accountbalance) {
        Accountbalance = accountbalance;
    }

    public void setRamzedovom(int ramzedovom) {
        this.ramzedovom = ramzedovom;
    }

    public void setInterestrate(int interestrate) {
        this.Interestrate = interestrate;
    }

    public void setMonthlypayout() {

        this.Monthlypayout =  Interestrate*Accountbalance*Holdduration/1200;
    }

    public void setHoldduration(int holdduration) {
        this.Holdduration = holdduration;
    }
    @Override
    public String toString(){

        return Owner.toString() +  String.format("Accountnumber: %d\nRamzeavval: %d\nRamzedovom: %d\nType: %s\nAccountbalance: %d\nInteresrrate: %d\nHoldDuration: %d\nMonthlypayout: %d\n",accountnumber,ramzeavval,ramzedovom,type,Accountbalance,Interestrate,Holdduration,Monthlypayout);
    }


}














