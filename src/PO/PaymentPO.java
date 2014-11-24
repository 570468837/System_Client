package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentPO implements Serializable {
	String number ;
	String customer ;
	String user ;
	ArrayList<TransferListItemPO> tfList = null ;//转账列表
	double sum ;
	public PaymentPO(String theNumber,String theCustomer,String theUser,ArrayList<TransferListItemPO> theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		tfList = theTfList ;
		sum = theSum ;
	}
	public PaymentPO(){
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
}
