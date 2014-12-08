package PO;

import java.io.Serializable;
import java.util.ArrayList;

import VO.CaseListItemVO;

public class CashPO implements Serializable  {
	String number ; 
	String user ;
	String account ;
	ArrayList<CaseListItemPO> cases = new ArrayList<CaseListItemPO>(); ;
	double sum ;
	public CashPO(String number, String user, String account,
			ArrayList<CaseListItemPO> cases, double sum) {
		super();
		this.number = number;
		this.user = user;
		this.account = account;
		this.cases = cases;
		this.sum = sum;
	}
	public void addCase(CaseListItemPO theCase){
		this.addCase(theCase);
	}
	public ArrayList<CaseListItemPO> getCases() {
		return cases;
	}
	public void setCases(ArrayList<CaseListItemPO> cases) {
		this.cases = cases;
	}
	public double getTotal(){
		double total = 0 ;
		for(CaseListItemPO theCase:cases){
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
