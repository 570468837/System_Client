package PO;

import java.util.ArrayList;


public class SalesReceiptPO {
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserPO userPO;
	private String commodityNum;
	private long priceBefore;
	private long discout;
	private long finalprice;
	private String comment;
	
	private ArrayList<SalesListItemPO> salesList;
	
	
	//添加商品
	public void addSalesListItem(SalesListItemPO  salesListItem){
		this.salesList.add(salesListItem);
		
	}
	
	public ArrayList<SalesListItemPO> getSalesList() {
		return salesList;
	}

	public void setSalesList(ArrayList<SalesListItemPO> salesList) {
		this.salesList = salesList;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public UserPO getUserPO() {
		return userPO;
	}
	public void setUserVO(UserPO userPO) {
		this.userPO = userPO;
	}
	public String getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityVO(String commodityNum) {
		this.commodityNum = commodityNum;
	}
	
	public long getPriveBefore() {
		//遍历所有商品以获得总价
		for(int i=0;i<salesList.size();i++){
			this.priceBefore+=salesList.get(i).getTotalPrice();
		}
		return priceBefore;
	}
	public void setPriveBefore(long priveBefore) {
		this.priceBefore = priveBefore;
	}
	public long getDiscout() {
		return discout;
	}
	public void setDiscout(long discout) {
		this.discout = discout;
	}
	public long getFinalprice() {
		//减去折扣
		this.finalprice=this.priceBefore-this.discout;
		return finalprice;
	}
	public void setFinalprice(long finalprice) {
		this.finalprice = finalprice;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
