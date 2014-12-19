package PO;

import java.io.Serializable;

public class AccountPO implements Serializable{
	private String name ; 
	private double balance ; 
	public AccountPO(String theName ,double theBalance){
		name = theName ;
		balance = theBalance ;
	}
	public AccountPO(){
		name = null ;
		balance =  0 ;
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
