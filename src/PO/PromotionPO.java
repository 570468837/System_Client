package PO;

import java.io.Serializable;
import java.util.ArrayList;

import Config.Level;
import Config.PromotionSort;

public class PromotionPO implements Serializable{
	 PromotionSort promotionType;
	 String promotionId;
	 ArrayList<GoodsPO> promotionGoods=new ArrayList<GoodsPO>();
     double leastPrice;
     double offPrice;
	 ArrayList<GoodsPO> presents=new ArrayList<GoodsPO>();
	 int Voucher;
     String startTime;
   	 String endTime;
   	 Level customer;
	
   	 public PromotionPO(PromotionSort promotionType, String promotionId,
			ArrayList<GoodsPO> promotionGoods, double leastPrice,
			double offPrice, ArrayList<GoodsPO> presents, int voucher,
			String startTime, String endTime, Level customer) {
		super();
		this.promotionType = promotionType;
		this.promotionId = promotionId;
		this.promotionGoods = promotionGoods;
		this.leastPrice = leastPrice;
		this.offPrice = offPrice;
		this.presents = presents;
		Voucher = voucher;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customer = customer;
	}

	public PromotionSort getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionSort promotionType) {
		this.promotionType = promotionType;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public ArrayList<GoodsPO> getPromotionGoods() {
		return promotionGoods;
	}

	public void setPromotionGoods(ArrayList<GoodsPO> promotionGoods) {
		this.promotionGoods = promotionGoods;
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

	public ArrayList<GoodsPO> getPresents() {
		return presents;
	}

	public void setPresents(ArrayList<GoodsPO> presents) {
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
