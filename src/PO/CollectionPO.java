package PO;

public class CollectionPO {
//�տ
	String number ;
	CustomerPO customer ;//�ͻ�
	UserPO user ;//����Ա
	TransferListPO tfList = null ;//ת���б�
	double sum ;//�ܽ��
	public CollectionPO(String theNumber,CustomerPO theCustomer,UserPO theUser,TransferListPO theTfList,double theSum){
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
