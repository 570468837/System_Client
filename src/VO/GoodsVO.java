package VO;
/**
 * 
 * @author hutao gaoyang
 *
 */
public class GoodsVO {
	public String serialNumber; //即id
	public String name;
	public String model;
	public double price;
	public String comment;
	
	
	public String goodsClassName; //商品分类名
	public double salePrice;
	public double latestPrice;
	public double latestSalePrice;
	public int commodityQuantity; //库存数量
	
	
	public GoodsVO() {}
	public GoodsVO(String serialNumber, String name, String model, 
			double price, String comment) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.comment = comment;
	};
	public GoodsVO(String serialNumber, String name, String model, 
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
	

}

