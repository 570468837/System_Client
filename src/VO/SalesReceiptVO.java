package VO;

import java.util.ArrayList;

import PO.SalesListItemPO;
import PO.UserPO;

public class SalesReceiptVO {
	
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserVO userVO;
	private String commodityNum;
	private double priceBefore;
	private double discout;
	private double finalprice;
	private String comment;
	private String time;
	private boolean isApprovedByManager=false;
	private boolean isApprovedByCommodity=false;
	
	public boolean isApprovedByManager() {
		return isApprovedByManager;
	}


	public void setApprovedByManager(boolean isApprovedByManager) {
		this.isApprovedByManager = isApprovedByManager;
	}


	public boolean isApprovedByCommodity() {
		return isApprovedByCommodity;
	}


	public void setApprovedByCommodity(boolean isApprovedByCommodity) {
		this.isApprovedByCommodity = isApprovedByCommodity;
	}


	
	
	//防止调用add方法时指针异常，所以要先初始化
		private ArrayList<SalesListItemVO> salesList=new ArrayList<SalesListItemVO>();
		
		public SalesReceiptVO(){}
		
		public SalesReceiptVO(String serialNumber, String retailer,
				String salesman, ArrayList<SalesListItemVO> salesList,
				UserVO userVO, String commodityNum, double priceBefore,
				double discount, double finalPrice, String time, String comments) {
			this.serialNumber=serialNumber;
			this.retailer=retailer;
			this.salesman=salesman;
			this.userVO=userVO;
			this.commodityNum=commodityNum;
			this.priceBefore=priceBefore;
			this.discout=discount;
			this.finalprice=finalPrice;
			this.comment=comments;
			this.time=time;
			this.salesList=salesList;

		}
		
	public double getPriceBefore() {
		return priceBefore;
	}


	public void setPriceBefore(double priceBefore) {
		this.priceBefore = priceBefore;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public void setDiscout(double discout) {
		this.discout = discout;
	}


	public void setFinalprice(double finalprice) {
		this.finalprice = finalprice;
	}
	
	
	public void addSalesListItem(SalesListItemVO saleListItem){
		this.salesList.add(saleListItem);		
	}
	
	
	public ArrayList<SalesListItemVO> getSalesList() {
		return salesList;
	}
	public void setSalesList(ArrayList<SalesListItemVO> salesList) {
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
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public String getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public double getPriveBefore() {
		//遍历所有商品获得总价
		for(int i=0;i<this.salesList.size();i++){
				this.priceBefore+=this.salesList.get(i).getTotalPrice();				
		}
		return priceBefore;
	}
	public void setPriveBefore(long priveBefore) {
		this.priceBefore = priveBefore;
	}
	public double getDiscout() {
		return discout;
	}
	public void setDiscout(long discout) {
		this.discout = discout;
	}
	public double getFinalprice() {
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

