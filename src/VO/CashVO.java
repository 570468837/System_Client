package VO;

public class CashVO extends ReceiptVO {
	String number ; 
	UserVO user ;
	AccountVO account ;
	CasesListVO cases ;//条目清单
	double sum ;//总额
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
	public CasesListVO getCases() {
		return cases;
	}
	public void setCases(CasesListVO cases) {
		this.cases = cases;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
