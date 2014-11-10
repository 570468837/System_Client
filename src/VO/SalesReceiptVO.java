<<<<<<< HEAD
package VO;

import java.util.ArrayList;

public class SalesReceiptVO {
	
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserVO userVO;
	private String commodityNum;
	private long priceBefore;
	private long discout;
	private long finalprice;
	private String comment;
	
	private ArrayList<SalesListItemVO> salesList;
	
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

	public long getPriveBefore() {
		//遍历所有商品获得总价
		for(int i=0;i<this.salesList.size();i++){
				this.priceBefore+=this.salesList.get(i).getTotalPrice();				
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
=======
package VO;

public class SalesReceiptVO {
	
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserVO userVO;
	private String commodityNum;
	private GoodsVO goodsVO;
	private long priveBefore;
	private long discout;
	private long finalprice;
	private String comment;
	
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
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public void setGoodsVO(GoodsVO goodsVO) {
		this.goodsVO = goodsVO;
	}
	public long getPriveBefore() {
		return priveBefore;
	}
	public void setPriveBefore(long priveBefore) {
		this.priveBefore = priveBefore;
	}
	public long getDiscout() {
		return discout;
	}
	public void setDiscout(long discout) {
		this.discout = discout;
	}
	public long getFinalprice() {
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
>>>>>>> FETCH_HEAD
