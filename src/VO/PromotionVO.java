package VO;

import java.util.ArrayList;

import Config.Level;
import PO.GoodsPO;

public class PromotionVO {
	String promotionType;
	String promotionId;
    String goodsId;
    double leastPrice;
    double offPrice;
    ArrayList<GoodsVO> presents=new ArrayList<GoodsVO>();
    int Voucher;
    String startTime;
  	String endTime;
  	Level customer;
  	 
    public PromotionVO(String promotionType, String promotionId,
			String goodsId, double leastPrice, double offPrice,
			ArrayList<GoodsVO> presents, int voucher, String startTime,
			String endTime,Level customer) {
		super();
		this.promotionType = promotionType;
		this.promotionId = promotionId;
		this.goodsId = goodsId;
		this.leastPrice = leastPrice;
		this.offPrice = offPrice;
		this.presents=presents;
		this.Voucher=voucher;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customer=customer;
	}
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
	public ArrayList<GoodsVO> getPresents() {
		return presents;
	}
	public void setPresents(ArrayList<GoodsVO> presents) {
		this.presents = presents;
	}
	public int getVoucher() {
		return Voucher;
	}
	public void setVoucher(int voucher) {
		Voucher = voucher;
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
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public Level getCustomer() {
		return customer;
	}
	public void setCustomer(Level customer) {
		this.customer = customer;
	}
	
	
}
