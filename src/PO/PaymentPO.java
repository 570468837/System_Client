package PO;

public class PaymentPO {
	String number ;
	CustomerPO customer ;//客户
	UserPO user ;//操作员
	TransferListPO tfList = null ;//转账列表
	double sum ;//总金额
	public PaymentPO(String theNumber,CustomerPO theCustomer,UserPO theUser,TransferListPO theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		tfList = theTfList ;
		sum = theSum ;
	}
	
}
