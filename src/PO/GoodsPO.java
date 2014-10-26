package PO;
/**
 * 
 * @author hutao gaoyang
 *
 */
public class GoodsPO {
	private String serialNumber;
	private String name;
	private String model;
	private int quantity;
	private long price;
	private long totalPrice;
	private String comment;
	
	
	private GoodsClassPO goodsClass;
	private long salePrice;
	private long latestPrice;
	private long latestSalePrice;
	
	public GoodsPO() {}
	public GoodsPO(String serialNumber, String name, String model, 
			int quantity, long price, long totalPrice, String comment) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.comment = comment;
	};
	public GoodsPO(String serialNumber, String name, String model, 
			int quantity, long price, long salePrice, long latestPrice,
			long latestSalePrice, GoodsClassPO goodsClass) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
		this.salePrice = salePrice;
		this.latestPrice = latestPrice;
		this.latestSalePrice = latestSalePrice;
		this.goodsClass = goodsClass;
	};
	
	
	
	public long getTotalPrice() {
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
	public int getQuantity() {
		return quantity;
	}
	public long getPrice() {
		return price;
	}
	public String getComment() {
		return comment;
	}
	public long getSalePrice() {
		return salePrice;
	}
	public long getLatestPrice() {
		return latestPrice;
	}
	public long getLatestSalePrice() {
		return latestSalePrice;
	}
	public GoodsClassPO getGoodsClass() {
		return goodsClass;
	}

}
