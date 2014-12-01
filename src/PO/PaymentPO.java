package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentPO implements Serializable{
	String number ;
	String customer ;
	String user ;
	ArrayList<TransferListItemPO> tfList =new ArrayList<TransferListItemPO>();//转账列表	
	double total ;
	boolean isProvedByManege = false ;
	boolean isProvedByFinancer = false ;
	
	public PaymentPO(){
		number = null;
		customer = null;
		user = null ;
		total =0;
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

	public PaymentPO(String theNumber,String theCustomer,String theUser,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		total = theSum ;
	}
	

	public void add(TransferListItemPO theItem){
		this.tfList.add(theItem) ;
	}
	
	public double getTotal(){
		double total = 0 ;
		for(TransferListItemPO theItem : tfList){
			total += theItem.getTransferMoney() ;
		}
		return total ;
	}

	public boolean isProvedByManege() {
		return isProvedByManege;
	}

	public void setProvedByManege(boolean isProvedByManege) {
		this.isProvedByManege = isProvedByManege;
	}

	public boolean isProvedByFinancer() {
		return isProvedByFinancer;
	}

	public void setProvedByFinancer(boolean isProvedByFinancer) {
		this.isProvedByFinancer = isProvedByFinancer;
	}
	
	
}
