package VO;

import java.util.ArrayList;

public class CashVO  {
	String number ; 
	UserVO user ;
	AccountVO account ;
	ArrayList<CaseListItem> cases = new ArrayList<CaseListItem>(); ;
	double sum ;
	public void addCase(CaseListItem theCase){
		this.addCase(theCase);
	}
	public double getTotal(){
		double total = 0 ;
		for(CaseListItem theCase:cases){
			total += theCase.getCaseMoney() ;
		}
		return total ;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public AccountVO getAccount() {
		return account;
	}
	public void setAccount(AccountVO account) {
		this.account = account;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
