package VO;

public class AccountVO {
	private String name ; 
	private double balance ; 
	public AccountVO(String theName ,double theBalance){
		name = theName ;
		balance = theBalance ;
	}
	
	
	
	
	public AccountVO() {
		// TODO Auto-generated constructor stub
	}

	public void changeBalance(double sum){
		balance = balance + sum ;
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
