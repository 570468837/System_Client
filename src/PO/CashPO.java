package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class CashPO implements Serializable  {
	String number ; 
	UserPO user ;
	AccountPO account ;
	ArrayList<CaseListItem> cases ;//条目清单
	double sum ;
    double getTotal(){
    	double total  = 0 ;
    	for(CaseListItem theCase:cases){
    		total += theCase.getCaseMoney() ;
    	}
    	return total ;
    }
    void addCase(CaseListItem theCase){
    	this.cases.add(theCase) ;
    }
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public UserPO getUser() {
		return user;
	}
	public void setUser(UserPO user) {
		this.user = user;
	}
	public AccountPO getAccount() {
		return account;
	}
	public void setAccount(AccountPO account) {
		this.account = account;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
