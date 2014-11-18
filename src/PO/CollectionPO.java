package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionPO implements Serializable{

	String number ;
	CustomerPO customer ;
	UserPO user ;
	ArrayList<TransferListItem> tfList = null ;//转账列表
	double sum ;
	public CollectionPO(String theNumber,CustomerPO theCustomer,UserPO theUser,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		sum = theSum ;
	}
	public CollectionPO(){
		number = null ;
	    customer = null ;
	    user = null ;
	    tfList = null ;
	    sum = 0 ;
	}
	public void addItem(TransferListItem theItem){
		this.tfList.add(theItem) ;
	}
	public double getTotal(){
		double total = 0 ;
		for(TransferListItem theItem : tfList){
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
	public CustomerPO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}
	public UserPO getUser() {
		return user;
	}
	public void setUser(UserPO user) {
		this.user = user;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}