package PO;

import java.io.Serializable;

/**
 * 
 * @author hutao gaoyang
 *
 */
public class GoodsPO implements Serializable{
	private String serialNumber;
	private String name;
	private String model;
	private double price;
	private double totalPrice;
	private String comment;
	
	
	private String goodsClassName;
	private double salePrice;
	private double latestPrice;
	private double latestSalePrice;
	public int commodityQuantity;
	
	public GoodsPO() {
		this.serialNumber = "/";
		this.name = "/";
		this.model = "/";
		this.price = -1;
		this.totalPrice = -1;
		this.comment = "/";
		this.goodsClassName = "/";
		this.salePrice = -1;
		this.latestPrice = -1;
		this.latestSalePrice = -1;
		this.commodityQuantity = -1;
	}

	//purchase 专用
	public GoodsPO(String serialNumber, String name, String model, 
		 double price, String comment) {
		this();
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.comment = comment;
	};
	/**
	 * 用于创建新的po对象
	 * @param po
	 */
	public GoodsPO(GoodsPO po) {
		this.serialNumber = po.serialNumber;
		this.name = po.name;
		this.model = po.model;
		this.price = po.price;
		this.totalPrice = po.totalPrice;
		this.comment = po.comment;
		this.goodsClassName = po.goodsClassName;
		this.salePrice = po.salePrice;
		this.latestPrice = po.latestPrice;
		this.latestSalePrice = po.latestSalePrice;
		this.commodityQuantity = po.commodityQuantity;
	}
	
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGoodsClassName() {
		return goodsClassName;
	}
	public void setGoodsClassName(String goodsClassName) {
		this.goodsClassName = goodsClassName;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getLatestPrice() {
		return latestPrice;
	}
	public void setLatestPrice(double latestPrice) {
		this.latestPrice = latestPrice;
	}
	public double getLatestSalePrice() {
		return latestSalePrice;
	}
	public void setLatestSalePrice(double latestSalePrice) {
		this.latestSalePrice = latestSalePrice;
	}
	public int getCommodityQuantity() {
		return commodityQuantity;
	}
	public void setCommodityQuantity(int commodityQuantity) {
		this.commodityQuantity = commodityQuantity;
	}
	

}
