package PO;

public class CaseListItem {
	
	String casename ;
	double caseMoney ; //金额
	String remark;// 备注
	
	public CaseListItem(String casename,double caseMoney,String remark){
		this.casename=casename;
		this.caseMoney=caseMoney;
		this.remark=remark;
	}
	
	public CaseListItem(String casename, double caseMoney, String remark) {
		this.casename = casename;
		this.caseMoney = caseMoney;
		this.remark = remark;
	}
	public String getCasename() {
		return casename;
	}
	public void setCasename(String casename) {
		this.casename = casename;
	}
	public double getCaseMoney() {
		return caseMoney;
	}
	public void setCaseMoney(double caseMoney) {
		this.caseMoney = caseMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	}
