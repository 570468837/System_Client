package PO;

public class AccountPO {
	private String name ; //¿Í»§Ãû³Æ
	private double balance ; //Óà¶î
	public AccountPO(String theName ,double theBalance){
		name = theName ;
		balance = theBalance ;
	}
	public AccountPO(){
		name = null ;
		balance =  0 ;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
