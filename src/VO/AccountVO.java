package VO;

public class AccountVO {
	private String name ; //客户名称
	private double balance ; //余额
	public AccountVO(String theName ,double theBalance){
		name = theName ;
		balance = theBalance ;
	}
	
	
	
	
	public AccountVO() {
		// TODO Auto-generated constructor stub
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
