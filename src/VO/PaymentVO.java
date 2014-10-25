package VO;


public class PaymentVO extends ReceiptVO{
	String number ;
	CustomerVO customer ;//客户
	UserVO user ;//操作员
	TransferListVO tfList ;//转账列表
	double sum ;//总金额
	public PaymentVO(){
		number = null ;
		customer = null ;
		user = null ;
		tfList = null ; 
		sum = 0 ;
	}
	public PaymentVO(String theNumber,CustomerVO theCustomer,UserVO theUser,TransferListVO theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		tfList = theTfList ;
		sum = theSum ;
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
	public TransferListVO getTfList() {
		return tfList;
	}
	public void setTfList(TransferListVO tfList) {
		this.tfList = tfList;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
}
