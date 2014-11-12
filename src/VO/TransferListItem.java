package VO;


public class TransferListItem {
	AccountVO account;
	double transferMoney ;
	String remark ;
	public AccountVO getAccount() {
		return account;
	}
	public void setAccount(AccountVO account) {
		this.account = account;
	}
	public double getTransferMoney() {
		return transferMoney;
	}
	public void setTransferMoney(double transferMoney) {
		this.transferMoney = transferMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
