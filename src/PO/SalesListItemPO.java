package PO;

import java.io.Serializable;


public class SalesListItemPO implements Serializable{

	private int quantity;
	private double totalPrice;
	private GoodsPO goodsPO;
	
	

	public SalesListItemPO(GoodsPO goodsPO,int quantity){
		this.goodsPO=goodsPO;
		this.quantity=quantity;		
	}
	
	public double getTotalPrice() {
		this.totalPrice=this.quantity*goodsPO.getSalePrice();
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
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
