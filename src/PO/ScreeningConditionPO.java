package PO;

import java.io.Serializable;

public class ScreeningConditionPO implements Serializable {
	String time1 ;
	String time2 ;//时间区间
	String typeOfReceipt ;//单据类型
	String nameOfGood;//商品名
	String customer ;//客户名
	String user ;//业务员
	String repository ;//仓科
	public ScreeningConditionPO(){
	}
	public ScreeningConditionPO(String time1, String time2,String typeOfReceipt , String nameOfGood,
			String customer, String user, String repository) {
		this.time1 = time1;
		this.time2 = time2;
		this.typeOfReceipt = typeOfReceipt ;
		this.nameOfGood = nameOfGood;
		this.customer = customer;
		this.user = user;
		this.repository = repository;
	}
	public String getTypeOfReceipt() {
		return typeOfReceipt;
	}
	public void setTypeOfReceipt(String typeOfReceipt) {
		this.typeOfReceipt = typeOfReceipt;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getNameOfGood() {
		return nameOfGood;
	}
	public void setNameOfGood(String nameOfGood) {
		this.nameOfGood = nameOfGood;
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
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	
}
