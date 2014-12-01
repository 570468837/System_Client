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
	
	public GoodsPO() {}


	//purchase 专用
	public GoodsPO(String serialNumber, String name, String model, 
		 double price, String comment) {

		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.comment = comment;
	};
	public GoodsPO(String serialNumber, String name, String model, 
			 double price, double salePrice, double latestPrice,
			double latestSalePrice, String goodsClassName) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.salePrice = salePrice;
		this.latestPrice = latestPrice;
		this.latestSalePrice = latestSalePrice;
		this.goodsClassName = goodsClassName;
	};
	
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
