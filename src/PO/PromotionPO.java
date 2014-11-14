package PO;

import java.io.Serializable;

public class PromotionPO implements Serializable{
	 String promotionType;
     String goodsId;
     double leastPrice;
     double offPrice;
     String presentGoodsId;
     int presentNum;
     String startTime;
   	 String endTime;
 	
   	 
     public PromotionPO(String promotionType, String goodsId, double leastPrice,
			double offPrice, String presentGoodsId, int presentNum,
			String startTime, String endTime) {
		this.promotionType = promotionType;
		this.goodsId = goodsId;
		this.leastPrice = leastPrice;
		this.offPrice = offPrice;
		this.presentGoodsId = presentGoodsId;
		this.presentNum = presentNum;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public double getLeastPrice() {
		return leastPrice;
	}
	public double getOffPrice() {
		return offPrice;
	}
	public String getPresentGoodsId() {
		return presentGoodsId;
	}
	public int getPresentNum() {
		return presentNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}

}
