package PO;

public class ReceiptPO {
//单据最初模板，所有的单据都要继承这个类
	String time = null ;//单据创建时间
	String number = null ;//单据编号，包含单据类型的信息，比如：SKD-xxxxxxxxx代表销售单，FKD-xxxxxxxxxx代表付款单
	CustomerPO customer = null ;
	UserPO user = null ;
	String storage = null ;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	
}
