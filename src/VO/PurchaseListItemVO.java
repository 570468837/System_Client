package VO;


public class PurchaseListItemVO {

	private int quantity;
	private double totalPrice;
	private GoodsVO goodsVO;
	
	public  PurchaseListItemVO(GoodsVO goodsVO,int quantity){
		this.goodsVO=goodsVO;
		this.quantity=quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		double result=this.quantity*this.goodsVO.price;
		return result;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public GoodsVO getGoodsVO() {
		return goodsVO;
	}
	public void setGoodsVO(GoodsVO goodsVO) {
		this.goodsVO = goodsVO;
	}
}
