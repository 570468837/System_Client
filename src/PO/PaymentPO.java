package PO;

import java.util.ArrayList;

public class PaymentPO {
	String number ;
	CustomerPO customer ;//�ͻ�
	UserPO user ;//����Ա
	ArrayList<TransferListItem> tfList = null ;//转账列表
	double sum ;//�ܽ��
	public PaymentPO(String theNumber,CustomerPO theCustomer,UserPO theUser,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		sum = theSum ;
	}
	public PaymentPO(){
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
}
