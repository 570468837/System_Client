package VO;
/**
 * 
 * @author hutao gaoyang
 *
 */
public class GoodsVO {
	public String serialNumber;
	public String name;
	public String model;
	public long price;
	public String comment;
	
	
	public String goodsClassName; //商品分类名
	public long salePrice;
	public long latestPrice;
	public long latestSalePrice;
	
	public long id;//商品id
	
	public GoodsVO() {}
	public GoodsVO(String serialNumber, String name, String model, 
		 long price, String comment) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.model = model;
		this.price = price;
		this.comment = comment;
	};
	public GoodsVO(String serialNumber, String name, String model, 
		 long price, long salePrice, long latestPrice,
			long latestSalePrice, String goodsClassName) {
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

