package VO;

import java.util.ArrayList;

public class CashVO  {
	String number ; 
	String user ;
	String account ;
	ArrayList<CaseListItemVO> cases = new ArrayList<CaseListItemVO>(); ;
	double sum ;
	public CashVO(String number, String user, String account,
			ArrayList<CaseListItemVO> cases, double sum) {
		super();
		this.number = number;
		this.user = user;
		this.account = account;
		this.cases = cases;
		this.sum = sum;
	}
	public CashVO(CashVO theVO) {
		// TODO Auto-generated constructor stub
		this.number = theVO.getNumber() ;
		this.user = theVO.getUser() ;
		this.account = theVO.getAccount() ;
		for(CaseListItemVO theItem : theVO.getCases()){
			CaseListItemVO item = new CaseListItemVO(theItem.getCasename(), theItem.getCaseMoney(), theItem.getRemark()) ;
			this.cases.add(item) ;
		}
		this.sum = theVO.getSum() ;
	}
	public void addCase(CaseListItemVO theCase){
		this.addCase(theCase);
	}
	public ArrayList<CaseListItemVO> getCases() {
		return cases;
	}
	public void setCases(ArrayList<CaseListItemVO> cases) {
		this.cases = cases;
	}
	public double getTotal(){
		double total = 0 ;
		for(CaseListItemVO theCase:cases){
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
