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
	
	
	private GoodsClassPO goodsClass;
	private double salePrice;
	private double latestPrice;
	private double latestSalePrice;
	public int commodityQuantity;
	
	public GoodsPO() {}
	public GoodsPO(String serialNumber, String name, String model, 
		 double price, double totalPrice, String comment) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.totalPrice = totalPrice;
		this.comment = comment;
	};
	public GoodsPO(String serialNumber, String name, String model, 
			 double price, double salePrice, double latestPrice,
			double latestSalePrice, GoodsClassPO goodsClass) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.salePrice = salePrice;
		this.latestPrice = latestPrice;
		this.latestSalePrice = latestSalePrice;
		this.goodsClass = goodsClass;
	};
	
	
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public String getName() {
		return name;
	}
	public String getModel() {
		return model;
	}
	
	public double getPrice() {
		return price;
	}
	public String getComment() {
		return comment;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public double getLatestPrice() {
		return latestPrice;
	}
	public double getLatestSalePrice() {
		return latestSalePrice;
	}
	public GoodsClassPO getGoodsClass() {
		return goodsClass;
	}

}
