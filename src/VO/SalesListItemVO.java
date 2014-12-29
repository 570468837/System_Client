package VO;

import java.io.Serializable;

import PO.SalesListItemPO;


public class SalesListItemVO  {

	
	private int quantity;
	private double totalPrice;
	private GoodsVO goodsVO;
	
	public SalesListItemVO(GoodsVO goodsVO,int quantity){
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
		double result=0;
		result=this.quantity*this.goodsVO.salePrice;
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
	
	public SalesListItemPO toPO(){
		SalesListItemPO result=new SalesListItemPO(goodsVO.toPO(), this.quantity);
		return result;
		
	} 
}
