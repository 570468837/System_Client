package VO;

public class PromotionVO {
	String promotionType;
	String promotionId;
    String goodsId;
    double leastPrice;
    double offPrice;
    String presentGoodsId;
    int presentNum;
    String startTime;
  	String endTime;
  	 
    public String getPromotionId() {
		return promotionId;
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
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public void setLeastPrice(double leastPrice) {
		this.leastPrice = leastPrice;
	}
	public void setOffPrice(double offPrice) {
		this.offPrice = offPrice;
	}
	public void setPresentGoodsId(String presentGoodsId) {
		this.presentGoodsId = presentGoodsId;
	}
	public void setPresentNum(int presentNum) {
		this.presentNum = presentNum;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
