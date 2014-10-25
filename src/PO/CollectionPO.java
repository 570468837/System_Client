package PO;

public class CollectionPO extends ReceiptPO{
//收款单
	String number ;
	CustomerPO customer ;//客户
	UserPO user ;//操作员
	TransferListPO tfList = null ;//转账列表
	double sum ;//总金额
	public CollectionPO(String theNumber,CustomerPO theCustomer,UserPO theUser,TransferListPO theTfList,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		tfList = theTfList ;
		sum = theSum ;
	}
	public CollectionPO(){
		number = null ;
	    customer = null ;
	    user = null ;
	    tfList = null ;
	    sum = 0 ;
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
	public TransferListPO getTfList() {
		return tfList;
	}
	public void setTfList(TransferListPO tfList) {
		this.tfList = tfList;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}