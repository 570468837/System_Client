package PO;


public class SalesReceiptPO {
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserPO userPO;
	private CommodityPO commodityPO;
	private GoodsPO goodsPO;
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
	public UserPO getUserPO() {
		return userPO;
	}
	public void setUserVO(UserPO userPO) {
		this.userPO = userPO;
	}
	public CommodityPO getCommodityVO() {
		return commodityPO;
	}
	public void setCommodityVO(CommodityPO commodityVO) {
		this.commodityPO = commodityPO;
	}
	public GoodsPO getGoodsPO() {
		return goodsPO;
	}
	public void setGoodsPO(GoodsPO goodsPO) {
		this.goodsPO = goodsPO;
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
