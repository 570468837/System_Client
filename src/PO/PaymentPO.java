package PO;

public class PaymentPO {
	String number ;
	CustomerPO customer ;//�ͻ�
	UserPO user ;//����Ա
	TransferListPO tfList = null ;//ת���б�
	double sum ;//�ܽ��
	public PaymentPO(String theNumber,CustomerPO theCustomer,UserPO theUser,TransferListPO theTfList,double theSum){
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
	
}
