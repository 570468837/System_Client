package VO;

import java.util.ArrayList;


public class PaymentVO {
	String number ;
	String customer ;
	String user ;
	ArrayList<TransferListItemVO> trList = new ArrayList<TransferListItemVO>();//转账列表
	double total ;
	
	public PaymentVO(){
		number = null ;
		customer = null ;
		user = null ;
		total = 0 ;
	}
	public PaymentVO(String theNumber,String theCustomer,String theUser,TransferListItemVO theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		total = theSum ;
	}
	public ArrayList<TransferListItemVO> getTrList() {
		return trList;
	}
	public void setTrList(ArrayList<TransferListItemVO> trList) {
		this.trList = trList;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void add(TransferListItemVO theItem){
		trList.add(theItem);
	}
	public double getTotal(){
		double total = 0 ;
		for(TransferListItemVO theItem:trList){
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
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
