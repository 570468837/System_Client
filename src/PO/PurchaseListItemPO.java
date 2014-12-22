package PO;

import java.io.Serializable;

public class PurchaseListItemPO implements Serializable{

	private int quantity;
	private double totalPrice;
	private GoodsPO goodsPO;
	
	

	public PurchaseListItemPO(GoodsPO goodsPO,int quantity){
		this.goodsPO=goodsPO;
		this.quantity=quantity;		
	}
	
	public double getTotalPrice() {
		double result=this.quantity*goodsPO.getPrice();
		return result;
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
