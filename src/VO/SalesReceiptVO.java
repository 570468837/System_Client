package VO;

public class SalesReceiptVO {
	
	private String serialNumber;
	//销售商
	private String retailer;
	//业务员
	private String salesman;
	private UserVO userVO;
	private CommodityVO commodityVO;
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
	public CommodityVO getCommodityVO() {
		return commodityVO;
	}
	public void setCommodityVO(CommodityVO commodityVO) {
		this.commodityVO = commodityVO;
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
