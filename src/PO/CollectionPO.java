package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionPO implements Serializable{

	String number ;
	String customer ;
	String user ;
	ArrayList<TransferListItemPO> tfList = null ;//转账列表
	double sum ;
	public CollectionPO(String theNumber,String theCustomer,String theUser,ArrayList<TransferListItemPO> theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		tfList = theTfList ;
		sum = theSum ;
	}
	public ArrayList<TransferListItemPO> getTfList() {
		return tfList;
	}
	public void setTfList(ArrayList<TransferListItemPO> tfList) {
		this.tfList = tfList;
	}
	public CollectionPO(){
		number = null ;
	    customer = null ;
	    user = null ;
	    tfList = null ;
	    sum = 0 ;
	}
	public void addItem(TransferListItemPO theItem){
		this.tfList.add(theItem) ;
	}
	public double getTotal(){
		double total = 0 ;
		for(TransferListItemPO theItem : tfList){
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
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}