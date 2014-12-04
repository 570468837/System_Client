package PO;

import java.io.Serializable;

public class TransferListItemPO implements Serializable{
	String account;
	double transferMoney ;
	String remark ;
	
	public TransferListItemPO(String account, double transferMoney,
			String remark) {
		this.account = account;
		this.transferMoney = transferMoney;
		this.remark = remark;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
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
