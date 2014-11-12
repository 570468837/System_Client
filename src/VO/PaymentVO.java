package VO;

import java.util.ArrayList;


public class PaymentVO {
	String number ;
	CustomerVO customer ;
	UserVO user ;
	ArrayList<TransferListItem> trList = null ;//转账列表
	double sum ;
	public PaymentVO(){
		number = null ;
		customer = null ;
		user = null ;
		sum = 0 ;
	}
	public PaymentVO(String theNumber,CustomerVO theCustomer,UserVO theUser,TransferListItem theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		sum = theSum ;
	}
	public void add(TransferListItem theItem){
		trList.add(theItem) ;
	}
	public double getTotal(){
		double total = 0 ;
		for(TransferListItem theItem:trList){
			total += theItem.getTransferMoney() ;
		}
		return total ;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public CustomerVO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
}
