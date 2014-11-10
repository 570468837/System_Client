package PO;

public class PurchaseListItemPO {

	private int quantity;
	private long totalPrice;
	private GoodsPO goodsPO;
	
	

	public PurchaseListItemPO(GoodsPO goodsPO,int quantity){
		this.goodsPO=goodsPO;
		this.quantity=quantity;		
	}
	
	public long getTotalPrice() {
		this.totalPrice=this.quantity*goodsPO.getPrice();
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public GoodsPO getGoodsPO() {
		return goodsPO;
	}

	public void setGoodsPO(GoodsPO goodsPO) {
		this.goodsPO = goodsPO;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
