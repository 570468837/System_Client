package PO;

import java.io.Serializable;

import Config.Level;

public class PromotionPO implements Serializable{
	 String promotionType;
     String goodsId;
     double leastPrice;
     double offPrice;
     String presentGoodsId;
     int presentNum;
     String startTime;
   	 String endTime;
   	 Level customer;
 	
   	 
     public PromotionPO(String promotionType, String goodsId, double leastPrice,
			double offPrice, String presentGoodsId, int presentNum,
			String startTime, String endTime,Level customer) {
		this.promotionType = promotionType;
		this.goodsId = goodsId;
		this.leastPrice = leastPrice;
		this.offPrice = offPrice;
		this.presentGoodsId = presentGoodsId;
		this.presentNum = presentNum;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customer=customer;
	}


	public String getPromotionType() {
		return promotionType;
	}


	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}


	public String getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public double getLeastPrice() {
		return leastPrice;
	}


	public void setLeastPrice(double leastPrice) {
		this.leastPrice = leastPrice;
	}


	public double getOffPrice() {
		return offPrice;
	}


	public void setOffPrice(double offPrice) {
		this.offPrice = offPrice;
	}


	public String getPresentGoodsId() {
		return presentGoodsId;
	}


	public void setPresentGoodsId(String presentGoodsId) {
		this.presentGoodsId = presentGoodsId;
	}


	public int getPresentNum() {
		return presentNum;
	}


	public void setPresentNum(int presentNum) {
		this.presentNum = presentNum;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Level getCustomer() {
		return customer;
	}


	public void setCustomer(Level customer) {
		this.customer = customer;
	}
	
	

}
